package hu.gabornovak.movieapp.logic.gateway;

import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Genre;
import hu.gabornovak.movieapp.logic.entity.Media;

/**
 * Created by gnovak on 7/2/2016.
 */

public interface GenreGateway {
    interface OnGenresLoaded {
        void onSuccess(Media media, List<Genre> genres);
        void onError(String errorMsg);
    }

    void loadGenres(Media media, OnGenresLoaded onGenresLoaded);
}
