package hu.gabornovak.movieapp.logic.plugin;

import com.google.gson.Gson;

import hu.gabornovak.movieapp.logic.entity.Movie;

public class DefaultJsonParserPlugin implements JsonParserPlugin {

    @Override
    public <T> T parseJson(String data, Class<T> dataClass) {
        return new Gson().fromJson(data, dataClass);
    }

    @Override
    public <T> String toJson(T object) {
        return new Gson().toJson(object);
    }

    @Override
    public Movie parseMovie(String jsonString) {
        Movie movie = new Movie();
        //TODO set fields
        return movie;
    }

}
