package hu.gabornovak.movieapp.logic.plugin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import hu.gabornovak.movieapp.logic.utils.Pair;

import static junit.framework.Assert.assertEquals;

/**
 * Created by gnovak on 7/2/2016.
 */
public class DefaultMovieDbRestPluginTest {

    @Test
    public void emptyRequestPath() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        assertEquals(plugin.createUrl(""), "https://api.themoviedb.org/3/?api_key=0a08e38b874d0aa2d426ffc04357069d");
    }

    @Test
    public void nullRequestPath() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        assertEquals(plugin.createUrl(null), "https://api.themoviedb.org/3/?api_key=0a08e38b874d0aa2d426ffc04357069d");
    }

    @Test
    public void spaceInPath() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        assertEquals(plugin.createUrl("movie/ 550"), "https://api.themoviedb.org/3/movie/%20550?api_key=0a08e38b874d0aa2d426ffc04357069d");
    }

    @Test
    public void specCharInPath() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        assertEquals(plugin.createUrl("movízé"), "https://api.themoviedb.org/3/movízé?api_key=0a08e38b874d0aa2d426ffc04357069d");
    }

    @Test
    public void validPath() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        assertEquals(plugin.createUrl("movie/550"), "https://api.themoviedb.org/3/movie/550?api_key=0a08e38b874d0aa2d426ffc04357069d");
    }

    @Test
    public void validParams() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<>("param", "value"));
        assertEquals(plugin.createUrlWithParams("movie/550", params), "https://api.themoviedb.org/3/movie/550?api_key=0a08e38b874d0aa2d426ffc04357069d&param=value");
    }

    @Test
    public void validParams2() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<>("param", "value"));
        params.add(new Pair<>("param2", "value2"));
        assertEquals(plugin.createUrlWithParams("movie/550", params), "https://api.themoviedb.org/3/movie/550?api_key=0a08e38b874d0aa2d426ffc04357069d&param=value&param2=value2");
    }

    @Test
    public void emptyParams() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        List<Pair<String, String>> params = new ArrayList<>();
        assertEquals(plugin.createUrlWithParams("movie/550", params), "https://api.themoviedb.org/3/movie/550?api_key=0a08e38b874d0aa2d426ffc04357069d");
    }

    @Test
    public void spaceInParams() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<>("param", "value has space"));
        assertEquals(plugin.createUrlWithParams("movie/550", params), "https://api.themoviedb.org/3/movie/550?api_key=0a08e38b874d0aa2d426ffc04357069d&param=value%20has%20space");
    }

    @Test
    public void specCharInParams() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<>("param", "véljú"));
        assertEquals(plugin.createUrlWithParams("movie/550", params), "https://api.themoviedb.org/3/movie/550?api_key=0a08e38b874d0aa2d426ffc04357069d&param=véljú");
    }
}