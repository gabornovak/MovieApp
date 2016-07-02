package hu.gabornovak.movieapp.logic.interactor;

import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.gateway.MovieGateway;

/**
 * Created by gnovak on 7/2/2016.
 */
public class Movies {
    public interface OnMovieLoaded {
        void onMovieLoaded(Movie movie);

        void onError(String errorMsg);
    }

    private MovieGateway movieGateway;

    public Movies(MovieGateway movieGateway) {
        this.movieGateway = movieGateway;
    }

    public void getPopularMovies(final OnMovieLoaded onMovieLoaded) {
        //TODO fetch from gateway
    }
}
