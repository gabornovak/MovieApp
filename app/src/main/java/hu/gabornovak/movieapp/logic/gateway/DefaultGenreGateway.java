package hu.gabornovak.movieapp.logic.gateway;


import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Genre;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.MovieDbRestPlugin;

/**
 * Created by gnovak on 7/2/2016.
 */
public class DefaultGenreGateway implements GenreGateway {
    private MovieDbRestPlugin restPlugin;
    private JsonParserPlugin jsonParserPlugin;

    /**
     * In memory cache
     */
    private SparseArray<Genre> movieGenresCache;

    public DefaultGenreGateway(MovieDbRestPlugin restPlugin, JsonParserPlugin jsonParserPlugin) {
        this.restPlugin = restPlugin;
        this.jsonParserPlugin = jsonParserPlugin;
    }

    @Override
    public void loadGenresForMovie(final Movie movie, final OnGenresLoaded onGenresLoaded) {
        if (movieGenresCache != null) {
            loadGenresFromCache(movie, onGenresLoaded);
        } else {
            restPlugin.get("genre/movie/list", new MovieDbRestPlugin.OnComplete() {
                @Override
                public void onSuccess(String data) {
                    movieGenresCache = new SparseArray<>();
                    for (Genre genre : jsonParserPlugin.parseGenres(data)) {
                        movieGenresCache.append(genre.getId(), genre);
                    }
                    loadGenresFromCache(movie, onGenresLoaded);
                }

                @Override
                public void onError() {
                    //TODO fix error message
                    onGenresLoaded.onError(null);
                }
            });
        }
    }

    private void loadGenresFromCache(Movie movie, OnGenresLoaded onGenresLoaded) {
        List<Genre> genres = new ArrayList<>();
        for (int genreId : movie.getGenreIds()) {
            Genre genre = movieGenresCache.get(genreId);
            if (genre != null) {
                genres.add(genre);
            }
        }
        onGenresLoaded.onSuccess(movie, genres);
    }
}