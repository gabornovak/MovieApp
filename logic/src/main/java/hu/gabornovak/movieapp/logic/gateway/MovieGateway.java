package hu.gabornovak.movieapp.logic.gateway;


import hu.gabornovak.movieapp.logic.entity.Movie;

/**
 * Created by gnovak on 7/2/2016.
 */
public interface MovieGateway {
    interface OnMovieLoaded {
        void onSuccess(Movie movie);

        void onError(String errorMsg);
    }

    void loadPopularMovies(OnMovieLoaded onMovieLoaded);
}
