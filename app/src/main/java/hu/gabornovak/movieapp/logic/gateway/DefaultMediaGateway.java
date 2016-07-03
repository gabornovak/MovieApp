package hu.gabornovak.movieapp.logic.gateway;


import java.util.ArrayList;
import java.util.List;

import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.MovieDbRestPlugin;
import hu.gabornovak.movieapp.logic.utils.Pair;

/**
 * Created by gnovak on 7/2/2016.
 */
public class DefaultMediaGateway implements MediaGateway {
    private MovieDbRestPlugin restPlugin;
    private JsonParserPlugin jsonParserPlugin;

    public DefaultMediaGateway(MovieDbRestPlugin restPlugin, JsonParserPlugin jsonParserPlugin) {
        this.restPlugin = restPlugin;
        this.jsonParserPlugin = jsonParserPlugin;
    }

    @Override
    public void loadPopularMovies(final OnMoviesLoaded onMediaLoaded) {
        restPlugin.get("movie/popular", new MovieDbRestPlugin.OnComplete() {
            @Override
            public void onSuccess(String data) {
                onMediaLoaded.onSuccess(jsonParserPlugin.parseMovies(data));
            }

            @Override
            public void onError() {
                onMediaLoaded.onError("An error occurred during download...");
            }
        });
    }

    @Override
    public void searchMovies(String searchText, final OnMoviesLoaded onMediaLoaded) {
        List<Pair<String, String>> params = createParamsForSearch(searchText);
        restPlugin.get("search/movie", params, new MovieDbRestPlugin.OnComplete() {
            @Override
            public void onSuccess(String data) {
                onMediaLoaded.onSuccess(jsonParserPlugin.parseMovies(data));
            }

            @Override
            public void onError() {
                onMediaLoaded.onError("An error occurred during download...");
            }
        });
    }

    private List<Pair<String, String>> createParamsForSearch(String searchText) {
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<>("query", searchText));
        return params;
    }
}
