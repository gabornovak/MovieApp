package hu.gabornovak.movieapp.logic.interactor;

import java.util.List;

import hu.gabornovak.movieapp.logic.entity.DetailedMovie;
import hu.gabornovak.movieapp.logic.entity.Media;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.entity.TVShow;
import hu.gabornovak.movieapp.logic.gateway.MediaGateway;
import hu.gabornovak.movieapp.logic.utils.RequestErrorType;

/**
 * Created by gnovak on 7/2/2016.
 */
public class MediaInteractor {
    public interface OnMoviesLoaded {
        void onMoviesLoaded(List<Movie> movies);

        void onError(RequestErrorType errorType);
    }

    public interface OnTVShowsLoaded {
        void onTVShowsLoaded(List<TVShow> tvShows);

        void onError(RequestErrorType errorType);
    }

    public interface OnMediaLoaded {
        void onMediaLoaded(List<Media> media);

        void onError(RequestErrorType errorType);
    }

    public interface OnDetailedMovieLoaded {
        void onDetailedMovieLoaded(DetailedMovie movie);

        void onError(RequestErrorType errorType);
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
            public void onError(RequestErrorType errorType) {
                onMoviesLoaded.onError(errorType);
            }
        });
    }

    public void searchForMedia(String searchText, final OnMediaLoaded onMediaLoaded) {
        mediaGateway.searchMedia(searchText, new MediaGateway.OnMediaLoaded() {
            @Override
            public void onSuccess(List<Media> media) {
                onMediaLoaded.onMediaLoaded(media);
            }

            @Override
            public void onError(RequestErrorType errorType) {
                onMediaLoaded.onError(errorType);
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
            public void onError(RequestErrorType errorType) {
                onTVShowsLoaded.onError(errorType);
            }
        });
    }

    public void getMovieDetails(Media media, final OnDetailedMovieLoaded onDetailedMovieLoaded) {
        mediaGateway.loadDetailedMovie(media, new MediaGateway.OnDetailedMovieLoaded() {
            @Override
            public void onSuccess(DetailedMovie movie) {
                onDetailedMovieLoaded.onDetailedMovieLoaded(movie);
            }

            @Override
            public void onError(RequestErrorType errorType) {
                onDetailedMovieLoaded.onError(errorType);
            }
        });
    }
}
