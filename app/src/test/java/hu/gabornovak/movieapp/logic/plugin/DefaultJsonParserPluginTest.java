package hu.gabornovak.movieapp.logic.plugin;

import org.junit.Test;

import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Genre;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.entity.TVShow;

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

    private static final String VALID_TV_SHOW_JSON = "{\n" +
            "  \"page\": 1,\n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"poster_path\": \"/jIhL6mlT7AblhbHJgEoiBIOUVl1.jpg\",\n" +
            "      \"popularity\": 41.377024,\n" +
            "      \"id\": 1399,\n" +
            "      \"backdrop_path\": \"/mUkuc2wyV9dHLG0D0Loaw5pO2s8.jpg\",\n" +
            "      \"vote_average\": 7.94,\n" +
            "      \"overview\": \"Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.\",\n" +
            "      \"first_air_date\": \"2011-04-17\",\n" +
            "      \"origin_country\": [\n" +
            "        \"US\"\n" +
            "      ],\n" +
            "      \"genre_ids\": [\n" +
            "        10765,\n" +
            "        10759,\n" +
            "        18\n" +
            "      ],\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"vote_count\": 984,\n" +
            "      \"name\": \"Game of Thrones\",\n" +
            "      \"original_name\": \"Game of Thrones\"\n" +
            "    }" +
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
        assertEquals(movie.getPosterPath(), "/9KQX22BeFzuNM66pBA6JbiaJ7Mi.jpg");
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
    public void parseValidTVShow() throws Exception {
        DefaultJsonParserPlugin plugin = new DefaultJsonParserPlugin();
        assertNotNull(plugin.parseTVShows(VALID_TV_SHOW_JSON));
    }

    @Test
    public void parseValidTVShowValidFields() throws Exception {
        DefaultJsonParserPlugin plugin = new DefaultJsonParserPlugin();
        List<TVShow> tvShows = plugin.parseTVShows(VALID_TV_SHOW_JSON);
        assertTrue(tvShows.size() == 1);
        TVShow tvShow = tvShows.get(0);
        assertEquals(tvShow.getId(), 1399);
        assertEquals(tvShow.getOverview(), "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.");
        assertEquals(tvShow.getPosterPath(), "/jIhL6mlT7AblhbHJgEoiBIOUVl1.jpg");
        assertEquals(tvShow.getName(), "Game of Thrones");
    }

    @Test
    public void parseInvalidTVShow() throws Exception {
        DefaultJsonParserPlugin plugin = new DefaultJsonParserPlugin();
        assertNull(plugin.parseTVShows("It is an invalid json"));
    }

    @Test
    public void parseNullTVShow() throws Exception {
        DefaultJsonParserPlugin plugin = new DefaultJsonParserPlugin();
        assertNull(plugin.parseTVShows(null));
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