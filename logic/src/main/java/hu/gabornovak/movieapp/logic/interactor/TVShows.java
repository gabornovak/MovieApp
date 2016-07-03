package hu.gabornovak.movieapp.logic.interactor;

import java.util.List;

import hu.gabornovak.movieapp.logic.entity.TVShow;
import hu.gabornovak.movieapp.logic.gateway.TVShowGateway;

/**
 * Created by gnovak on 7/2/2016.
 */
public class TVShows {
    public interface OnTVShowsLoaded {
        void onMoviesLoaded(List<TVShow> tvShows);

        void onError(String errorMsg);
    }

    private TVShowGateway tvShowGateway;

    public TVShows(TVShowGateway tvShowGateway) {
        this.tvShowGateway = tvShowGateway;
    }

    public void getPopularTVShows(final OnTVShowsLoaded onTVShowsLoaded) {
        tvShowGateway.loadPopularTVShows(new TVShowGateway.OnTVShowsLoaded() {
            @Override
            public void onSuccess(List<TVShow> tvShows) {
                onTVShowsLoaded.onMoviesLoaded(tvShows);
            }

            @Override
            public void onError(String errorMsg) {
                onTVShowsLoaded.onError(errorMsg);
            }
        });
    }

    public void searchTVShows(String searchText, final OnTVShowsLoaded onTVShowsLoaded) {
        tvShowGateway.searchTVShows(searchText, new TVShowGateway.OnTVShowsLoaded() {
            @Override
            public void onSuccess(List<TVShow> tvShows) {
                onTVShowsLoaded.onMoviesLoaded(tvShows);
            }

            @Override
            public void onError(String errorMsg) {
                onTVShowsLoaded.onError(errorMsg);
            }
        });
    }
}
