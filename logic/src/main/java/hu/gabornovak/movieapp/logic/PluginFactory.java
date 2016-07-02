package hu.gabornovak.movieapp.logic;


import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.PreferenceStorePlugin;
import hu.gabornovak.movieapp.logic.plugin.RestPlugin;

public interface PluginFactory {
    RestPlugin getRestPlugin();

    PreferenceStorePlugin getPreferenceStorePlugin();

    JsonParserPlugin getJsonParserPlugin();
}
