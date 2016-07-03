package hu.gabornovak.movieapp.logic.plugin;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Genre;
import hu.gabornovak.movieapp.logic.entity.Media;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.entity.Person;
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
    public List<Person> parsePeople(String jsonString) {
        try {
            PersonResult result = parseJson(jsonString, PersonResult.class);
            if (result != null) {
                List<Person> people = new ArrayList<>();
                for (JsonPerson person : result.results) {
                    people.add(createPersonFromJsonPerson(person));
                }
                return people;
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<Media> parseMedia(String jsonString) {
        try {
            MediaResult result = parseJson(jsonString, MediaResult.class);
            if (result != null) {
                List<Media> media = new ArrayList<>();
                for (JsonMedia jsonMedia : result.results) {
                    //TODO Add person to response
                    switch (jsonMedia.mediaType) {
                        case "movie":
                            media.add(createMovieFromJsonMedia(jsonMedia));
                            break;
                        case "tv":
                            media.add(createTVShowFromJsonMedia(jsonMedia));
                            break;
                        default:
                            Log.w(getClass().getSimpleName(), "Unknown media type in response! " + jsonMedia.mediaType);
                            break;
                    }
                }
                return media;
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<TVShow> parseTVShows(String jsonString) {
        try {
            MediaResult result = parseJson(jsonString, MediaResult.class);
            if (result != null) {
                List<TVShow> tvShows = new ArrayList<>();
                for (JsonMedia media : result.results) {
                    tvShows.add(createTVShowFromJsonMedia(media));
                }
                return tvShows;
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private Person createPersonFromJsonPerson(JsonPerson jsonPerson) {
        Person person = new Person();
        person.setName(jsonPerson.name);
        person.setId(jsonPerson.id);
        person.setProfilePath(jsonPerson.profilePath);

        List<Media> knownFor = new ArrayList<>();
        for (JsonMedia media : jsonPerson.knownFor) {
            switch (media.mediaType) {
                case "movie":
                    knownFor.add(createMovieFromJsonMedia(media));
                    break;
                case "tv":
                    knownFor.add(createTVShowFromJsonMedia(media));
                    break;
                default:
                    Log.w(getClass().getSimpleName(), "Unknown media type in response! " + media.mediaType);
                    break;
            }
        }
        person.setKnownFor(knownFor);
        return person;
    }

    private TVShow createTVShowFromJsonMedia(JsonMedia media) {
        TVShow tvShow = new TVShow();
        tvShow.setName(media.name);
        tvShow.setFirstAirDate(media.firstAirDate);
        tvShow.setGenreIds(media.genreIds);
        tvShow.setId(media.id);
        tvShow.setOverview(media.overview);
        tvShow.setPosterPath(media.posterPath);
        tvShow.setRating(media.rating);
        return tvShow;
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

    /**
     * Helper classes for json parsing
     */
    private static class MediaResult {
        List<JsonMedia> results;
    }

    private static class GenresResult {
        List<Genre> genres;
    }

    private static class PersonResult {
        List<JsonPerson> results;
    }

    private static class JsonPerson {
        int id;
        @SerializedName("profile_path")
        String profilePath;
        String name;

        @SerializedName("known_for")
        List<JsonMedia> knownFor;
    }

    private static class JsonMedia {
        int id;
        @SerializedName("poster_path")
        String posterPath;
        @SerializedName("genre_ids")
        List<Integer> genreIds;
        @SerializedName("vote_average")
        float rating;
        String overview;

        @SerializedName("media_type")
        String mediaType;

        //Movie fields
        String title;
        @SerializedName("release_date")
        String releaseDate;

        //TV Show fields
        String name;
        @SerializedName("first_air_date")
        String firstAirDate;
    }
}
