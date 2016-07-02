package hu.gabornovak.movieapp.logic.plugin;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DefaultMovieDbRestPlugin implements MovieDbRestPlugin {
    private static final boolean USE_SSL = true;
    private static final String BASE_URL = "api.themoviedb.org";
    private static final String VERSION = "3";
    private static final String API_KEY = "0a08e38b874d0aa2d426ffc04357069d";

    private OkHttpClient client;

    public DefaultMovieDbRestPlugin() {
        client = new OkHttpClient();
    }

    @Override
    public void get(@NonNull String requestPath, @NonNull OnComplete onComplete) {
        String url = createUrl(requestPath);
        callGet(url, onComplete);
    }

    String createUrl(String requestPath) {
        //Create url like this: https://api.themoviedb.org/3/person/popular?api_key=0a08e38b874d0aa2d426ffc04357069d
        StringBuilder url = new StringBuilder();
        if (USE_SSL) {
            url.append("https://");
        } else {
            url.append("http://");
        }
        url.append(BASE_URL);
        url.append("/");
        url.append(VERSION);
        url.append("/");
        if (requestPath != null && !requestPath.isEmpty()) {
            //Replace only the spaces, because the other characters are already valid
            url.append(requestPath.replaceAll(" ", "%20"));
        }
        url.append("?api_key=");
        url.append(API_KEY);

        return url.toString();
    }

    private void callGet(String url, final OnComplete onComplete) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onComplete.onError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    onComplete.onSuccess(response.body().string());
                } else {
                    //TODO add proper error management (error codes and messages)
                    onComplete.onError();
                }
            }
        });
    }
}