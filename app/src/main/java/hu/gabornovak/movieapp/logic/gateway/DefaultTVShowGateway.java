package hu.gabornovak.movieapp.logic.gateway;


import java.util.ArrayList;
import java.util.List;

import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.MovieDbRestPlugin;
import hu.gabornovak.movieapp.logic.utils.Pair;

/**
 * Created by gnovak on 7/2/2016.
 */
public class DefaultTVShowGateway implements TVShowGateway {
    private MovieDbRestPlugin restPlugin;
    private JsonParserPlugin jsonParserPlugin;

    public DefaultTVShowGateway(MovieDbRestPlugin restPlugin, JsonParserPlugin jsonParserPlugin) {
        this.restPlugin = restPlugin;
        this.jsonParserPlugin = jsonParserPlugin;
    }

    private List<Pair<String, String>> createParamsForSearch(String searchText) {
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<>("query", searchText));
        return params;
    }

    @Override
    public void loadPopularTVShows(final OnTVShowsLoaded onTVShowsLoaded) {
        restPlugin.get("tv/popular", new MovieDbRestPlugin.OnComplete() {
            @Override
            public void onSuccess(String data) {
                onTVShowsLoaded.onSuccess(jsonParserPlugin.parseTVShows(data));
            }

            @Override
            public void onError() {
                onTVShowsLoaded.onError("An error occurred during download...");
            }
        });
    }

    @Override
    public void searchTVShows(String searchText, final OnTVShowsLoaded onTVShowsLoaded) {
        List<Pair<String, String>> params = createParamsForSearch(searchText);
        restPlugin.get("search/tv", params, new MovieDbRestPlugin.OnComplete() {
            @Override
            public void onSuccess(String data) {
                onTVShowsLoaded.onSuccess(jsonParserPlugin.parseTVShows(data));
            }

            @Override
            public void onError() {
                onTVShowsLoaded.onError("An error occurred during download...");
            }
        });
    }
}
