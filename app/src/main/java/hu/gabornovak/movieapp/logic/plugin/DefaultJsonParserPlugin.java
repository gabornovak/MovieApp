package hu.gabornovak.movieapp.logic.plugin;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Genre;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.entity.TVShow;

public class DefaultJsonParserPlugin implements JsonParserPlugin {
    private <T> T parseJson(String data, Class<T> dataClass) {
        return new Gson().fromJson(data, dataClass);
    }

    @Override
    public List<Movie> parseMovies(String jsonString) {
        try {
            MediaResult result = parseJson(jsonString, MediaResult.class);
            if (result != null) {
                List<Movie> movies = new ArrayList<>();
                for (JsonMedia media : result.results) {
                    movies.add(createMovieFromJsonMedia(media));
                }
                return movies;
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private Movie createMovieFromJsonMedia(JsonMedia media) {
        Movie movie = new Movie();
        movie.setTitle(media.title);
        movie.setReleaseDate(media.releaseDate);
        movie.setGenreIds(media.genreIds);
        movie.setId(media.id);
        movie.setOverview(media.overview);
        movie.setPosterPath(media.posterPath);
        movie.setRating(media.rating);
        return movie;
    }

    @Override
    public List<Genre> parseGenres(String jsonString) {
        try {
            GenresResult result = parseJson(jsonString, GenresResult.class);
            if (result != null) {
                return result.genres;
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<TVShow> parseTVShows(String jsonString) {
        //FIXME
        return null;
    }

    /**
     * Helper class for json parsing
     */
    private static class MediaResult {
        List<JsonMedia> results;
    }

    private static class JsonMedia {
        public int id;

        @SerializedName("poster_path")
        public String posterPath;

        @SerializedName("genre_ids")
        public List<Integer> genreIds;

        @SerializedName("vote_average")
        public float rating;

        public String overview;

        //Movie fields
        public String title;
        @SerializedName("release_date")
        public String releaseDate;

        //TV Show fields
        public String name;
        @SerializedName("first_air_date")
        public String firstAirDate;
    }

    /**
     * Helper class for json parsing
     */
    private static class GenresResult {
        List<Genre> genres;
    }
}
