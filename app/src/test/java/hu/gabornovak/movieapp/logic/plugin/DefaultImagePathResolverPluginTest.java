package hu.gabornovak.movieapp.logic.plugin;

import org.junit.Test;

import hu.gabornovak.movieapp.logic.entity.Movie;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Created by gnovak on 7/2/2016.
 */
public class DefaultImagePathResolverPluginTest {
    @Test
    public void getValidMoviePosterUrl() throws Exception {
        Movie movie = new Movie();
        movie.setPoster_path("/file.jpg");

        DefaultImagePathResolverPlugin plugin = new DefaultImagePathResolverPlugin();

        assertEquals("http://image.tmdb.org/t/p/w500/file.jpg", plugin.getMoviePosterUrl(movie));
    }

    @Test
    public void getNullMoviePosterUrl() throws Exception {
        Movie movie = null;
        DefaultImagePathResolverPlugin plugin = new DefaultImagePathResolverPlugin();

        assertNull(plugin.getMoviePosterUrl(movie));
    }

    @Test
    public void getMissingMoviePosterUrl() throws Exception {
        Movie movie = new Movie();
        DefaultImagePathResolverPlugin plugin = new DefaultImagePathResolverPlugin();

        assertEquals("http://image.tmdb.org/t/p/w500", plugin.getMoviePosterUrl(movie));
    }

    @Test
    public void createBaseUrl() throws Exception {
        DefaultImagePathResolverPlugin plugin = new DefaultImagePathResolverPlugin();

        assertEquals("http://image.tmdb.org/t/p/w500", plugin.createBaseUrl());
    }

}