package hu.gabornovak.movieapp.logic.plugin;

/**
 *
 * Specific interface for the Movie Database API requests (https://api.themoviedb.org)
 */
public interface MovieDbRestPlugin {
    interface OnComplete {
        void onSuccess(String data);
        void onError();
    }

    /**
     * get from the server.
     *
     * @param requestPath Only the path, not the whole url! E.g.: movie/id
     * @param onComplete onSuccess called if it was finished, onError otherwise
     */
    void get(String requestPath, OnComplete onComplete);
}
