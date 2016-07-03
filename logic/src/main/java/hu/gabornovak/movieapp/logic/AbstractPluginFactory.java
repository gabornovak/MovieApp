package hu.gabornovak.movieapp.logic;


import hu.gabornovak.movieapp.logic.plugin.ConnectionPlugin;
import hu.gabornovak.movieapp.logic.plugin.ImagePathResolverPlugin;
import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.PreferenceStorePlugin;
import hu.gabornovak.movieapp.logic.plugin.MovieDbRestPlugin;

public abstract class AbstractPluginFactory implements PluginFactory {

    //region Instances
    private LazyInstance<MovieDbRestPlugin> restPlugin = new LazyInstance<MovieDbRestPlugin>() {
        @Override
        protected MovieDbRestPlugin createInstance() {
            return createRestPlugin();
        }
    };

    private LazyInstance<ConnectionPlugin> connectionPlugin= new LazyInstance<ConnectionPlugin>() {
        @Override
        protected ConnectionPlugin createInstance() {
            return createConnectionPlugin();
        }
    };

    private LazyInstance<PreferenceStorePlugin> preferenceStorePlugin = new LazyInstance<PreferenceStorePlugin>() {
        @Override
        protected PreferenceStorePlugin createInstance() {
            return createPreferenceStorePlugin();
        }
    };

    private LazyInstance<JsonParserPlugin> jsonParserPlugin = new LazyInstance<JsonParserPlugin>() {
        @Override
        protected JsonParserPlugin createInstance() {
            return createJsonParserPlugin();
        }
    };

    private LazyInstance<ImagePathResolverPlugin> imagePathResolverPlugin = new LazyInstance<ImagePathResolverPlugin>() {
        @Override
        protected ImagePathResolverPlugin createInstance() {
            return createImagePathResolverPlugin();
        }
    };

    //endregion

    @Override
    public MovieDbRestPlugin getRestPlugin() {
        return restPlugin.getInstance();
    }

    protected abstract MovieDbRestPlugin createRestPlugin();

    @Override
    public PreferenceStorePlugin getPreferenceStorePlugin() {
        return preferenceStorePlugin.getInstance();
    }

    protected abstract PreferenceStorePlugin createPreferenceStorePlugin();

    public JsonParserPlugin getJsonParserPlugin() {
        return jsonParserPlugin.getInstance();
    }

    protected abstract JsonParserPlugin createJsonParserPlugin();

    @Override
    public ImagePathResolverPlugin getImagePathResolverPlugin() {
        return imagePathResolverPlugin.getInstance();
    }

    protected abstract ImagePathResolverPlugin createImagePathResolverPlugin();

    @Override
    public ConnectionPlugin getConnectionPlugin() {
        return connectionPlugin.getInstance();
    }

    protected abstract ConnectionPlugin createConnectionPlugin();


}
