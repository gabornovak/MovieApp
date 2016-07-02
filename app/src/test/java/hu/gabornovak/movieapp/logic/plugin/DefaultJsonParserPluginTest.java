package hu.gabornovak.movieapp.logic.plugin;

import org.junit.Test;

import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Genre;
import hu.gabornovak.movieapp.logic.entity.Movie;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by gnovak on 7/2/2016.
 */
public class DefaultJsonParserPluginTest {
    private static final String VALID_MOVIE_JSON = "{\n" +
            "  \"page\": 1,\n" +
            "  \"results\": [{\n" +
            "         \"poster_path\": \"/9KQX22BeFzuNM66pBA6JbiaJ7Mi.jpg\",\n" +
            "         \"adult\": false,\n" +
            "         \"overview\": \"We always knew they were coming back. Using recovered alien technology, the nations of Earth have collaborated on an immense defense program to protect the planet. But nothing can prepare us for the aliens’ advanced and unprecedented force. Only the ingenuity of a few brave men and women can bring our world back from the brink of extinction.\",\n" +
            "         \"release_date\": \"2016-06-22\",\n" +
            "         \"genre_ids\": [\n" +
            "            28,\n" +
            "            12,\n" +
            "            878\n" +
            "         ],\n" +
            "         \"id\": 47933,\n" +
            "         \"original_title\": \"Independence Day: Resurgence\",\n" +
            "         \"original_language\": \"en\",\n" +
            "         \"title\": \"Independence Day: Resurgence\",\n" +
            "         \"backdrop_path\": \"/8SqBiesvo1rh9P1hbJTmnVum6jv.jpg\",\n" +
            "         \"popularity\": 35.359988,\n" +
            "         \"vote_count\": 302,\n" +
            "         \"video\": false,\n" +
            "         \"vote_average\": 4.51\n" +
            "       }" +
            "]" +
            "}";


    private static final String VALID_GENRES_JSON = "{\n" +
            "  \"genres\": [\n" +
            "    {\n" +
            "      \"id\": 28,\n" +
            "      \"name\": \"Action\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 12,\n" +
            "      \"name\": \"Adventure\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 16,\n" +
            "      \"name\": \"Animation\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 35,\n" +
            "      \"name\": \"Comedy\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 80,\n" +
            "      \"name\": \"Crime\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 99,\n" +
            "      \"name\": \"Documentary\"\n" +
            "    }" +
            "]" +
            "}";

    @Test
    public void parseValidMovie() throws Exception {
        DefaultJsonParserPlugin plugin = new DefaultJsonParserPlugin();
        assertNotNull(plugin.parseMovies(VALID_MOVIE_JSON));
    }

    @Test
    public void parseValidMovieValidFields() throws Exception {
        DefaultJsonParserPlugin plugin = new DefaultJsonParserPlugin();
        List<Movie> movies = plugin.parseMovies(VALID_MOVIE_JSON);
        assertTrue(movies.size() == 1);
        Movie movie = movies.get(0);
        assertEquals(movie.getId(), 47933);
        assertEquals(movie.getOverview(), "We always knew they were coming back. Using recovered alien technology, the nations of Earth have collaborated on an immense defense program to protect the planet. But nothing can prepare us for the aliens’ advanced and unprecedented force. Only the ingenuity of a few brave men and women can bring our world back from the brink of extinction.");
        assertEquals(movie.getPoster_path(), "/9KQX22BeFzuNM66pBA6JbiaJ7Mi.jpg");
        assertEquals(movie.getTitle(), "Independence Day: Resurgence");
    }

    @Test
    public void parseInvalidMovie() throws Exception {
        DefaultJsonParserPlugin plugin = new DefaultJsonParserPlugin();
        assertNull(plugin.parseMovies("It is an invalid json"));
    }

    @Test
    public void parseNull() throws Exception {
        DefaultJsonParserPlugin plugin = new DefaultJsonParserPlugin();
        assertNull(plugin.parseMovies(null));
    }

    @Test
    public void parseValidGenre() throws Exception {
        DefaultJsonParserPlugin plugin = new DefaultJsonParserPlugin();
        assertNotNull(plugin.parseGenres(VALID_GENRES_JSON));
    }

    @Test
    public void parseValiGenreValidFields() throws Exception {
        DefaultJsonParserPlugin plugin = new DefaultJsonParserPlugin();
        List<Genre> genres = plugin.parseGenres(VALID_GENRES_JSON);
        assertTrue(genres.size() == 6);
        Genre genre = genres.get(0);
        assertEquals(genre.getId(), 28);
        assertEquals(genre.getName(), "Action");
    }

    @Test
    public void parseInvalidGenre() throws Exception {
        DefaultJsonParserPlugin plugin = new DefaultJsonParserPlugin();
        assertNull(plugin.parseGenres("It is an invalid json"));
    }

    @Test
    public void parseNullGenre() throws Exception {
        DefaultJsonParserPlugin plugin = new DefaultJsonParserPlugin();
        assertNull(plugin.parseGenres(null));
    }
}