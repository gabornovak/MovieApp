package hu.gabornovak.movieapp.logic;

import android.content.Context;

import hu.gabornovak.movieapp.logic.plugin.ConnectionPlugin;
import hu.gabornovak.movieapp.logic.plugin.DefaultConnectionPlugin;
import hu.gabornovak.movieapp.logic.plugin.DefaultImagePathResolverPlugin;
import hu.gabornovak.movieapp.logic.plugin.DefaultJsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.DefaultMovieDbRestPlugin;
import hu.gabornovak.movieapp.logic.plugin.DefaultPreferenceStorePlugin;
import hu.gabornovak.movieapp.logic.plugin.ImagePathResolverPlugin;
import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.MovieDbRestPlugin;
import hu.gabornovak.movieapp.logic.plugin.PreferenceStorePlugin;

public class DefaultPluginFactory extends AbstractPluginFactory {
    private Context context;

    public DefaultPluginFactory(Context context) {
        this.context = context;
    }

    @Override
    protected MovieDbRestPlugin createRestPlugin() {
        return new DefaultMovieDbRestPlugin();
    }

    @Override
    protected PreferenceStorePlugin createPreferenceStorePlugin() {
        return new DefaultPreferenceStorePlugin(context);
    }

    @Override
    protected JsonParserPlugin createJsonParserPlugin() {
        return new DefaultJsonParserPlugin();
    }

    @Override
    protected ImagePathResolverPlugin createImagePathResolverPlugin() {
        return new DefaultImagePathResolverPlugin();
    }

    @Override
    protected ConnectionPlugin createConnectionPlugin() {
        return new DefaultConnectionPlugin(context);
    }
}
