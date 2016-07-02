package hu.gabornovak.movieapp.logic.plugin;

public interface PreferenceStorePlugin {
    void save(String key, String value);
    void save(String key, long value);
    void save(String key, boolean value);

    String loadString(String key);
    long loadLong(String key);
    boolean loadBoolean(String key);

    void remove(String key);
}
