package hu.gabornovak.movieapp.logic.interactor;

import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.entity.TVShow;
import hu.gabornovak.movieapp.logic.gateway.MediaGateway;

/**
 * Created by gnovak on 7/2/2016.
 */
public class MediaInteractor {
    public interface OnMoviesLoaded {
        void onMoviesLoaded(List<Movie> movies);

        void onError(String errorMsg);
    }

    public interface OnTVShowsLoaded {
        void onTVShowsLoaded(List<TVShow> tvShows);

        void onError(String errorMsg);
    }

    private MediaGateway mediaGateway;

    public MediaInteractor(MediaGateway mediaGateway) {
        this.mediaGateway = mediaGateway;
    }

    public void getPopularMovies(final OnMoviesLoaded onMoviesLoaded) {
        mediaGateway.loadPopularMovies(new MediaGateway.OnMoviesLoaded() {
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

    public void searchMovies(String searchText, final OnMoviesLoaded onMoviesLoaded) {
        mediaGateway.searchMovies(searchText, new MediaGateway.OnMoviesLoaded() {
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

    public void getPopularTVShows(final OnTVShowsLoaded onTVShowsLoaded) {
        mediaGateway.loadPopularTVShows(new MediaGateway.OnTVShowsLoaded() {
            @Override
            public void onSuccess(List<TVShow> tvShows) {
                onTVShowsLoaded.onTVShowsLoaded(tvShows);
            }

            @Override
            public void onError(String errorMsg) {
                onTVShowsLoaded.onError(errorMsg);
            }
        });
    }
}
