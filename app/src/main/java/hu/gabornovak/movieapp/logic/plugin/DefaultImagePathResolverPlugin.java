package hu.gabornovak.movieapp.logic.plugin;

import hu.gabornovak.movieapp.logic.entity.Movie;

/**
 * Created by gnovak on 7/2/2016.
 */

public class DefaultImagePathResolverPlugin implements ImagePathResolverPlugin {
    private static final String BASE_URL = "http://image.tmdb.org/t/p/";
    //TODO add size as a parameter
    private static final String SIZE = "w500";

    @Override
    public String getMoviePosterUrl(Movie movie) {
        if (movie == null) {
            return null;
        }
        String baseUrl = createBaseUrl();
        if (movie.getPoster_path() == null) {
            return baseUrl;
        }
        return baseUrl + movie.getPoster_path();
    }

    String createBaseUrl() {
        StringBuilder url = new StringBuilder();
        url.append(BASE_URL);
        url.append(SIZE);

        return url.toString();
    }
}
