package hu.gabornovak.movieapp.logic.plugin;

import hu.gabornovak.movieapp.logic.entity.Movie;

public interface JsonParserPlugin {
    <T> T parseJson(String data, Class<T> dataClass);

    <T> String toJson(T object);

    Movie parseMovie(String jsonString);
}
