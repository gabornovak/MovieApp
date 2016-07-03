package hu.gabornovak.movieapp.logic;


import hu.gabornovak.movieapp.logic.plugin.ConnectionPlugin;
import hu.gabornovak.movieapp.logic.plugin.ImagePathResolverPlugin;
import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.PreferenceStorePlugin;
import hu.gabornovak.movieapp.logic.plugin.MovieDbRestPlugin;

public interface PluginFactory {
    MovieDbRestPlugin getRestPlugin();

    PreferenceStorePlugin getPreferenceStorePlugin();

    ConnectionPlugin getConnectionPlugin();

    JsonParserPlugin getJsonParserPlugin();

    ImagePathResolverPlugin getImagePathResolverPlugin();
}
