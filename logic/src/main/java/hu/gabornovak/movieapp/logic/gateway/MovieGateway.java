package hu.gabornovak.movieapp.logic.gateway;


import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Movie;

/**
 * Created by gnovak on 7/2/2016.
 */
public interface MovieGateway {
    interface OnMoviesLoaded {
        void onSuccess(List<Movie> movies);
        void onError(String errorMsg);
    }

    void loadPopularMovies(OnMoviesLoaded onMoviesLoaded);

    void searchMovies(String searchText, OnMoviesLoaded onMoviesLoaded);
}
