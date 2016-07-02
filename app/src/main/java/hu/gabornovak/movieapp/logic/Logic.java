package hu.gabornovak.movieapp.logic;

import android.content.Context;

import hu.gabornovak.movieapp.logic.interactor.Movies;

public class Logic extends AbstractLogic {
    private Context context;
    private static Logic instance = new Logic();

    private Logic() {
    }

    public static Logic getInstance() {
        return instance;
    }

    public void init(Context context) {
        this.context = context;
    }

    @Override
    protected PluginFactory createPluginFactory() {
        return new DefaultPluginFactory(context);
    }

    @Override
    protected GatewayFactory createGatewayFactory() {
        return new DefaultGatewayFactory(context);
    }

    @Override
    protected Movies createMovies() {
        return new Movies(getGatewayFactory().getMovieGateway());
    }

}
