package hu.gabornovak.movieapp.logic.plugin;

import java.util.List;

import hu.gabornovak.movieapp.logic.entity.DetailedMovie;
import hu.gabornovak.movieapp.logic.entity.DetailedTVShow;
import hu.gabornovak.movieapp.logic.entity.Genre;
import hu.gabornovak.movieapp.logic.entity.Media;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.entity.Person;
import hu.gabornovak.movieapp.logic.entity.TVShow;

public interface JsonParserPlugin {
    List<Movie> parseMovies(String jsonString);

    List<TVShow> parseTVShows(String jsonString);

    List<Genre> parseGenres(String jsonString);

    List<Person> parsePeople(String jsonString);

    List<Media> parseMedia(String jsonString);

    DetailedMovie parseDetailedMovie(String jsonString);

    DetailedTVShow parseDetailedTVShow(String jsonString);
}
