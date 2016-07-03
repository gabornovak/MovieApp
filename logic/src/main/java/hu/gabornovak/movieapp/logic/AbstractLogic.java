package hu.gabornovak.movieapp.logic;

import hu.gabornovak.movieapp.logic.interactor.MediaInteractor;

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

    private LazyInstance<MediaInteractor> movies = new LazyInstance<MediaInteractor>() {
        @Override
        protected MediaInteractor createInstance() {
            return createMovies();
        }
    };

    protected abstract MediaInteractor createMovies();

    public MediaInteractor getMedia() {
        return movies.getInstance();
    }
}
