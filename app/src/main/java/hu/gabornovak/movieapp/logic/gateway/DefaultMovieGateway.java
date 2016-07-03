package hu.gabornovak.movieapp.logic.gateway;


import java.util.ArrayList;
import java.util.List;

import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.MovieDbRestPlugin;
import hu.gabornovak.movieapp.logic.utils.Pair;

/**
 * Created by gnovak on 7/2/2016.
 */
public class DefaultMovieGateway implements MovieGateway {
    private MovieDbRestPlugin restPlugin;
    private JsonParserPlugin jsonParserPlugin;

    public DefaultMovieGateway(MovieDbRestPlugin restPlugin, JsonParserPlugin jsonParserPlugin) {
        this.restPlugin = restPlugin;
        this.jsonParserPlugin = jsonParserPlugin;
    }

    @Override
    public void loadPopularMovies(final OnMoviesLoaded onMoviesLoaded) {
        restPlugin.get("movie/popular", new MovieDbRestPlugin.OnComplete() {
            @Override
            public void onSuccess(String data) {
                onMoviesLoaded.onSuccess(jsonParserPlugin.parseMovies(data));
            }

            @Override
            public void onError() {
                onMoviesLoaded.onError("An error occurred during download...");
            }
        });
    }

    @Override
    public void searchMovies(String searchText, final OnMoviesLoaded onMoviesLoaded) {
        List<Pair<String, String>> params = createParamsForSearch(searchText);
        restPlugin.get("search/movie", params, new MovieDbRestPlugin.OnComplete() {
            @Override
            public void onSuccess(String data) {
                onMoviesLoaded.onSuccess(jsonParserPlugin.parseMovies(data));
            }

            @Override
            public void onError() {
                onMoviesLoaded.onError("An error occurred during download...");
            }
        });
    }

    private List<Pair<String, String>> createParamsForSearch(String searchText) {
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<>("query", searchText));
        return params;
    }
}
