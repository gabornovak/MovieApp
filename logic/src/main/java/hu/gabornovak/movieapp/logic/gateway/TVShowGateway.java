package hu.gabornovak.movieapp.logic.gateway;


import java.util.List;

import hu.gabornovak.movieapp.logic.entity.TVShow;

/**
 * Created by gnovak on 7/2/2016.
 */
public interface TVShowGateway {
    interface OnTVShowsLoaded {
        void onSuccess(List<TVShow> tvShows);
        void onError(String errorMsg);
    }

    void loadPopularTVShows(OnTVShowsLoaded onTVShowsLoaded);

    void searchTVShows(String searchText, OnTVShowsLoaded onTVShowsLoaded);
}
