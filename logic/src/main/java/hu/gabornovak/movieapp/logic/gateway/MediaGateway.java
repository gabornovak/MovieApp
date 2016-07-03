package hu.gabornovak.movieapp.logic.gateway;


import java.util.List;

import hu.gabornovak.movieapp.logic.entity.DetailedMovie;
import hu.gabornovak.movieapp.logic.entity.DetailedTVShow;
import hu.gabornovak.movieapp.logic.entity.Media;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.entity.TVShow;
import hu.gabornovak.movieapp.logic.utils.RequestErrorType;

/**
 * Created by gnovak on 7/2/2016.
 */
public interface MediaGateway {
    interface OnMoviesLoaded {
        void onSuccess(List<Movie> movies);
        void onError(RequestErrorType errorType);
    }

    interface OnTVShowsLoaded {
        void onSuccess(List<TVShow> tvShows);
        void onError(RequestErrorType errorType);
    }

    interface OnDetailedMovieLoaded {
        void onSuccess(DetailedMovie movie);
        void onError(RequestErrorType errorType);
    }

    interface OnDetailedTVShowLoaded {
        void onSuccess(DetailedTVShow tvShow);
        void onError(RequestErrorType errorType);
    }

    interface OnMediaLoaded {
        void onSuccess(List<Media> media);
        void onError(RequestErrorType errorType);
    }

    void loadPopularMovies(OnMoviesLoaded onMoviesLoaded);

    void loadPopularTVShows(OnTVShowsLoaded onTVShowsLoaded);

    void searchMedia(String searchText, OnMediaLoaded onMediaLoaded);

    void loadDetailedMovie(Media media, OnDetailedMovieLoaded onDetailedMovieLoaded);

    void loadDetailedTVShow(Media media, OnDetailedTVShowLoaded onDetailedTVShowLoaded);
}
