package hu.gabornovak.movieapp.logic.plugin;

import hu.gabornovak.movieapp.logic.entity.Media;
import hu.gabornovak.movieapp.logic.entity.Person;

/**
 * Created by gnovak on 7/2/2016.
 */

public class DefaultImagePathResolverPlugin implements ImagePathResolverPlugin {
    private static final String BASE_URL = "http://image.tmdb.org/t/p/";

    //TODO add size as a parameter
    private static final String SIZE = "w300";

    @Override
    public String getMediaPosterUrl(Media media) {
        if (media == null) {
            return null;
        }
        String baseUrl = createBaseUrl();
        if (media.getPosterPath() == null) {
            return baseUrl;
        }
        return baseUrl + media.getPosterPath();
    }

    @Override
    public String getProfileUrl(Person person) {
        if (person == null) {
            return null;
        }
        String baseUrl = createBaseUrl();
        if (person.getProfilePath() == null) {
            return baseUrl;
        }
        return baseUrl + person.getProfilePath();
    }

    String createBaseUrl() {
        return BASE_URL + SIZE;
    }
}
