package hu.gabornovak.movieapp.logic;

import hu.gabornovak.movieapp.logic.gateway.DefaultGenreGateway;
import hu.gabornovak.movieapp.logic.gateway.DefaultMediaGateway;
import hu.gabornovak.movieapp.logic.gateway.DefaultPersonGateway;
import hu.gabornovak.movieapp.logic.gateway.GenreGateway;
import hu.gabornovak.movieapp.logic.gateway.MediaGateway;
import hu.gabornovak.movieapp.logic.gateway.PersonGateway;


public class DefaultGatewayFactory extends AbstractGatewayFactory {
    DefaultGatewayFactory() {
    }

    @Override
    public MediaGateway createMovieGateway() {
        return new DefaultMediaGateway(Logic.getInstance().getPluginFactory().getRestPlugin(),Logic.getInstance().getPluginFactory().getConnectionPlugin(), Logic.getInstance().getPluginFactory().getJsonParserPlugin());
    }

    @Override
    public GenreGateway createGenreGateway() {
        return new DefaultGenreGateway(Logic.getInstance().getPluginFactory().getRestPlugin(),Logic.getInstance().getPluginFactory().getConnectionPlugin(), Logic.getInstance().getPluginFactory().getJsonParserPlugin());
    }

    @Override
    public PersonGateway createPersonGateway() {
        return new DefaultPersonGateway(Logic.getInstance().getPluginFactory().getRestPlugin(),Logic.getInstance().getPluginFactory().getConnectionPlugin(), Logic.getInstance().getPluginFactory().getJsonParserPlugin());
    }
}
