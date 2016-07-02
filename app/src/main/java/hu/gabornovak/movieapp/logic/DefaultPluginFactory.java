package hu.gabornovak.movieapp.logic;

import android.content.Context;

import hu.gabornovak.movieapp.logic.plugin.DefaultJsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.DefaultPreferenceStorePlugin;
import hu.gabornovak.movieapp.logic.plugin.DefaultRestPlugin;
import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.PreferenceStorePlugin;
import hu.gabornovak.movieapp.logic.plugin.RestPlugin;

public class DefaultPluginFactory extends AbstractPluginFactory {
    private Context context;

    public DefaultPluginFactory(Context context) {
        this.context = context;
    }

    @Override
    protected RestPlugin createRestPlugin() {
        return new DefaultRestPlugin();
    }

    @Override
    protected PreferenceStorePlugin createPreferenceStorePlugin() {
        return new DefaultPreferenceStorePlugin(context);
    }

    @Override
    protected JsonParserPlugin createJsonParserPlugin() {
        return new DefaultJsonParserPlugin();
    }
}
