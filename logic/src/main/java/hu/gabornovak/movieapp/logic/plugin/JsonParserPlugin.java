package hu.gabornovak.movieapp.logic.plugin;

import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Movie;

public interface JsonParserPlugin {
    <T> T parseJson(String data, Class<T> dataClass);

    <T> String toJson(T object);

    List<Movie> parseMovies(String jsonString);
}
