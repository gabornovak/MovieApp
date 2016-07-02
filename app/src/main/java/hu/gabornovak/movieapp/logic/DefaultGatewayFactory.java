package hu.gabornovak.movieapp.logic;

import android.content.Context;

import hu.gabornovak.movieapp.logic.gateway.DefaultMovieGateway;
import hu.gabornovak.movieapp.logic.gateway.MovieGateway;


public class DefaultGatewayFactory extends AbstractGatewayFactory {
    private Context context;

    public DefaultGatewayFactory(Context context) {
        this.context = context;
    }

    @Override
    public MovieGateway createMovieGateway() {
        return new DefaultMovieGateway(Logic.getInstance().getPluginFactory().getRestPlugin(), Logic.getInstance().getPluginFactory().getJsonParserPlugin());
    }
}
