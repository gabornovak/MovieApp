package hu.gabornovak.movieapp.logic;

import hu.gabornovak.movieapp.logic.interactor.MediaInteractor;
import hu.gabornovak.movieapp.logic.interactor.PersonInteractor;

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
            return createMedia();
        }
    };

    protected abstract MediaInteractor createMedia();

    public MediaInteractor getMedia() {
        return movies.getInstance();
    }

    private LazyInstance<PersonInteractor> people = new LazyInstance<PersonInteractor>() {
        @Override
        protected PersonInteractor createInstance() {
            return createPersonInteractor();
        }
    };

    protected abstract PersonInteractor createPersonInteractor();

    public PersonInteractor getPeople() {
        return people.getInstance();
    }
}
