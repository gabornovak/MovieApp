package hu.gabornovak.movieapp.logic.plugin;

import android.content.Context;

import hu.gabornovak.movieapp.utils.Prefs;


public class DefaultPreferenceStorePlugin implements PreferenceStorePlugin {

    private Prefs prefs;

    public DefaultPreferenceStorePlugin(Context context) {
        this.prefs = new Prefs(context);
    }

    @Override
    public void save(String key, String value) {
        prefs.save(key, value);
    }

    @Override
    public void save(String key, long value) {
        prefs.save(key, value);
    }

    @Override
    public void save(String key, boolean value) {
        prefs.save(key, value);
    }

    @Override
    public String loadString(String key) {
        return prefs.getString(key, null);
    }

    @Override
    public long loadLong(String key) {
        return prefs.getLong(key, 0);
    }

    @Override
    public boolean loadBoolean(String key) {
        return prefs.getBoolean(key, false);
    }

    @Override
    public void remove(String key) {
        prefs.remove(key);
    }
}
