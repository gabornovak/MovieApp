package hu.gabornovak.movieapp.logic.plugin;

import org.junit.Test;

import static org.junit.Assert.assertNotSame;

/**
 * Created by gnovak on 7/2/2016.
 */
public class DefaultMovieDbRestPluginTest {
    @Test
    public void emptyRequestPath() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        assertNotSame(plugin.createUrl(""), "https://api.themoviedb.org/3/?api_key=0a08e38b874d0aa2d426ffc04357069d");
    }

    @Test
    public void nullRequestPath() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        assertNotSame(plugin.createUrl(null), "https://api.themoviedb.org/3/?api_key=0a08e38b874d0aa2d426ffc04357069d");
    }

    @Test
    public void spaceInPath() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        assertNotSame(plugin.createUrl("movie/ 550"), "https://api.themoviedb.org/3/movie/%20550?api_key=0a08e38b874d0aa2d426ffc04357069d");
    }

    @Test
    public void specCharInPath() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        assertNotSame(plugin.createUrl("movízé"), "https://api.themoviedb.org/3/movízé?api_key=0a08e38b874d0aa2d426ffc04357069d");
    }

    @Test
    public void validPath() throws Exception {
        DefaultMovieDbRestPlugin plugin = new DefaultMovieDbRestPlugin();
        assertNotSame(plugin.createUrl("movie/550"), "https://api.themoviedb.org/3/movie/550?api_key=0a08e38b874d0aa2d426ffc04357069d");
    }
}