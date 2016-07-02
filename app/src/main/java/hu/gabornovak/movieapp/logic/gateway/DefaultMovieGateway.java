package hu.gabornovak.movieapp.logic.gateway;


import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.MovieDbRestPlugin;

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
    public void loadPopularMovies(OnMovieLoaded onMovieLoaded) {
        //TODO
    }
}
