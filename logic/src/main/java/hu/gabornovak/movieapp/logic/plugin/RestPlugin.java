package hu.gabornovak.movieapp.logic.plugin;

public interface RestPlugin {
    interface OnComplete {
        void onSuccess(String data);
        void onError();
    }

    void get(String url, OnComplete onComplete);
    void post(String url, String requestBody, OnComplete onComplete);
}
