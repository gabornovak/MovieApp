package hu.gabornovak.movieapp.logic;

import hu.gabornovak.movieapp.logic.interactor.Movies;
import hu.gabornovak.movieapp.logic.interactor.TVShows;

public abstract class AbstractLogic {
    //region Factories
    private LazyInstance<PluginFactory> pluginFactory = new LazyInstance<PluginFactory>() {
        @Override
        protected PluginFactory createInstance() {
            return createPluginFactory();
        }
    };

    protected abstract PluginFactory createPluginFactory();

    public PluginFactory getPluginFactory() {
        return pluginFactory.getInstance();
    }

    private LazyInstance<GatewayFactory> gatewayFactory = new LazyInstance<GatewayFactory>() {
        @Override
        protected GatewayFactory createInstance() {
            return createGatewayFactory();
        }
    };

    protected abstract GatewayFactory createGatewayFactory();

    public GatewayFactory getGatewayFactory() {
        return gatewayFactory.getInstance();
    }
    //endregion

    private LazyInstance<Movies> movies = new LazyInstance<Movies>() {
        @Override
        protected Movies createInstance() {
            return createMovies();
        }
    };

    protected abstract Movies createMovies();

    public Movies getMovies() {
        return movies.getInstance();
    }

    private LazyInstance<TVShows> tvShows = new LazyInstance<TVShows>() {
        @Override
        protected TVShows createInstance() {
            return createTVShows();
        }
    };

    protected abstract TVShows createTVShows();

    public TVShows getTVShows() {
        return tvShows.getInstance();
    }
}
