package hu.gabornovak.movieapp.logic.plugin;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Movie;

public class DefaultJsonParserPlugin implements JsonParserPlugin {
    /**
     * Helper class for json parsing
     */
    private static class MovieResult {
        List<Movie> results;
    }

    @Override
    public <T> T parseJson(String data, Class<T> dataClass) {
        return new Gson().fromJson(data, dataClass);
    }

    @Override
    public <T> String toJson(T object) {
        return new Gson().toJson(object);
    }

    private <T> T parseJson(String data, Type dataClass) {
        return new Gson().fromJson(data, dataClass);
    }

    @Override
    public List<Movie> parseMovies(String jsonString) {
        try {
            MovieResult result = parseJson(jsonString, MovieResult.class);
            if (result != null) {
                return result.results;
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
