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
        movie.setPosterPath("/file.jpg");

        DefaultImagePathResolverPlugin plugin = new DefaultImagePathResolverPlugin();

        assertEquals("http://image.tmdb.org/t/p/w300/file.jpg", plugin.getMediaPosterUrl(movie));
    }

    @Test
    public void getNullMoviePosterUrl() throws Exception {
        Movie movie = null;
        DefaultImagePathResolverPlugin plugin = new DefaultImagePathResolverPlugin();

        assertNull(plugin.getMediaPosterUrl(movie));
    }

    @Test
    public void getMissingMoviePosterUrl() throws Exception {
        Movie movie = new Movie();
        DefaultImagePathResolverPlugin plugin = new DefaultImagePathResolverPlugin();

        assertEquals("http://image.tmdb.org/t/p/w300", plugin.getMediaPosterUrl(movie));
    }

    @Test
    public void createBaseUrl() throws Exception {
        DefaultImagePathResolverPlugin plugin = new DefaultImagePathResolverPlugin();

        assertEquals("http://image.tmdb.org/t/p/w300", plugin.createBaseUrl());
    }

}