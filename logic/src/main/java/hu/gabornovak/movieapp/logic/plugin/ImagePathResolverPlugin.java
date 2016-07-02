package hu.gabornovak.movieapp.logic.plugin;

import hu.gabornovak.movieapp.logic.entity.Movie;

/**
 * Created by gnovak on 7/2/2016.
 */

public interface ImagePathResolverPlugin {
    String getMoviePosterUrl(Movie movie);
}
