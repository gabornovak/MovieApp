package hu.gabornovak.movieapp.logic.gateway;


import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.RestPlugin;

/**
 * Created by gnovak on 7/2/2016.
 */
public class DefaultMovieGateway implements MovieGateway {
    private RestPlugin restPlugin;
    private JsonParserPlugin jsonParserPlugin;

    public DefaultMovieGateway(RestPlugin restPlugin, JsonParserPlugin jsonParserPlugin) {
        this.restPlugin = restPlugin;
        this.jsonParserPlugin = jsonParserPlugin;
    }

    @Override
    public void loadPopularMovies(OnMovieLoaded onMovieLoaded) {
        //TODO
    }
}
