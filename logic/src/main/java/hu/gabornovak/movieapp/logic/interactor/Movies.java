package hu.gabornovak.movieapp.logic.interactor;

import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.gateway.MovieGateway;

/**
 * Created by gnovak on 7/2/2016.
 */
public class Movies {
    public interface OnMoviesLoaded {
        void onMoviesLoaded(List<Movie> movies);

        void onError(String errorMsg);
    }

    private MovieGateway movieGateway;

    public Movies(MovieGateway movieGateway) {
        this.movieGateway = movieGateway;
    }

    public void getPopularMovies(final OnMoviesLoaded onMoviesLoaded) {
        movieGateway.loadPopularMovies(new MovieGateway.OnMoviesLoaded() {
            @Override
            public void onSuccess(List<Movie> movies) {
                onMoviesLoaded.onMoviesLoaded(movies);
            }

            @Override
            public void onError(String errorMsg) {
                onMoviesLoaded.onError(errorMsg);
            }
        });
    }
}
