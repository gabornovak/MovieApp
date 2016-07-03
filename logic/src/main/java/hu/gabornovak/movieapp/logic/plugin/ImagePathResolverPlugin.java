package hu.gabornovak.movieapp.logic.plugin;

import hu.gabornovak.movieapp.logic.entity.Media;
import hu.gabornovak.movieapp.logic.entity.Person;

/**
 * Created by gnovak on 7/2/2016.
 */

public interface ImagePathResolverPlugin {
    String getMediaPosterUrl(Media media);

    String getProfileUrl(Person person);
}
