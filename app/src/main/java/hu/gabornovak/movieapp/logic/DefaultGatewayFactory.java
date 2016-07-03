package hu.gabornovak.movieapp.logic;

import android.content.Context;

import hu.gabornovak.movieapp.logic.gateway.DefaultGenreGateway;
import hu.gabornovak.movieapp.logic.gateway.DefaultMovieGateway;
import hu.gabornovak.movieapp.logic.gateway.DefaultTVShowGateway;
import hu.gabornovak.movieapp.logic.gateway.GenreGateway;
import hu.gabornovak.movieapp.logic.gateway.MovieGateway;
import hu.gabornovak.movieapp.logic.gateway.TVShowGateway;


public class DefaultGatewayFactory extends AbstractGatewayFactory {
    private Context context;

    public DefaultGatewayFactory(Context context) {
        this.context = context;
    }

    @Override
    public MovieGateway createMovieGateway() {
        return new DefaultMovieGateway(Logic.getInstance().getPluginFactory().getRestPlugin(), Logic.getInstance().getPluginFactory().getJsonParserPlugin());
    }

    @Override
    public TVShowGateway createTVShowGateway() {
        return new DefaultTVShowGateway(Logic.getInstance().getPluginFactory().getRestPlugin(), Logic.getInstance().getPluginFactory().getJsonParserPlugin());
    }


    @Override
    public GenreGateway createGenreGateway() {
        return new DefaultGenreGateway(Logic.getInstance().getPluginFactory().getRestPlugin(), Logic.getInstance().getPluginFactory().getJsonParserPlugin());
    }
}
