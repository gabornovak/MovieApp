package hu.gabornovak.movieapp.logic;


import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.PreferenceStorePlugin;
import hu.gabornovak.movieapp.logic.plugin.RestPlugin;

public abstract class AbstractPluginFactory implements PluginFactory {

    //region Instances
    private LazyInstance<RestPlugin> restPlugin = new LazyInstance<RestPlugin>() {
        @Override
        protected RestPlugin createInstance() {
            return createRestPlugin();
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

    //endregion

    @Override
    public RestPlugin getRestPlugin() {
        return restPlugin.getInstance();
    }

    protected abstract RestPlugin createRestPlugin();

    @Override
    public PreferenceStorePlugin getPreferenceStorePlugin() {
        return preferenceStorePlugin.getInstance();
    }

    protected abstract PreferenceStorePlugin createPreferenceStorePlugin();

    public JsonParserPlugin getJsonParserPlugin() {
        return jsonParserPlugin.getInstance();
    }

    protected abstract JsonParserPlugin createJsonParserPlugin();
}
