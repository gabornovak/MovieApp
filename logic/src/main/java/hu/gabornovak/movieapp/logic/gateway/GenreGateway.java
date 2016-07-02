package hu.gabornovak.movieapp.logic.gateway;

import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Genre;
import hu.gabornovak.movieapp.logic.entity.Movie;

/**
 * Created by gnovak on 7/2/2016.
 */

public interface GenreGateway {
    interface OnGenresLoaded {
        void onSuccess(Movie movie, List<Genre> genres);
        void onError(String errorMsg);
    }

    void loadGenresForMovie(Movie movie, GenreGateway.OnGenresLoaded onGenresLoaded);
}
