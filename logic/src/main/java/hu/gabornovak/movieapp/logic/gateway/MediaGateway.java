package hu.gabornovak.movieapp.logic.gateway;


import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Media;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.entity.TVShow;

/**
 * Created by gnovak on 7/2/2016.
 */
public interface MediaGateway {
    interface OnMoviesLoaded {
        void onSuccess(List<Movie> movies);
        void onError(String errorMsg);
    }

    interface OnTVShowsLoaded {
        void onSuccess(List<TVShow> tvShows);
        void onError(String errorMsg);
    }

    interface OnMediaLoaded {
        void onSuccess(List<Media> media);
        void onError(String errorMsg);
    }

    void loadPopularMovies(OnMoviesLoaded onMoviesLoaded);

    void loadPopularTVShows(OnTVShowsLoaded onTVShowsLoaded);

    void searchMedia(String searchText, OnMediaLoaded onMediaLoaded);
}
