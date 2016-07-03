package hu.gabornovak.movieapp.logic.gateway;


import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.gabornovak.movieapp.logic.entity.Genre;
import hu.gabornovak.movieapp.logic.entity.Media;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.entity.TVShow;
import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.MovieDbRestPlugin;

/**
 * Created by gnovak on 7/2/2016.
 */
public class DefaultGenreGateway implements GenreGateway {
    private enum MediaType {
        MOVIE("movie"), TV_SHOW("tv"), UNKNOWN("unknown");

        private String name;

        MediaType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private MovieDbRestPlugin restPlugin;
    private JsonParserPlugin jsonParserPlugin;

    /**
     * In memory cache
     */
    private Map<MediaType, SparseArray<Genre>> genresCache;

    public DefaultGenreGateway(MovieDbRestPlugin restPlugin, JsonParserPlugin jsonParserPlugin) {
        this.restPlugin = restPlugin;
        this.jsonParserPlugin = jsonParserPlugin;

        genresCache = new HashMap<>();
    }

    @Override
    public void loadGenres(final Media media, final OnGenresLoaded onGenresLoaded) {
        final MediaType type = getMediaType(media);

        if (type != MediaType.UNKNOWN) {
            if (genresCache.containsKey(type)) {
                loadGenresFromCache(media, genresCache.get(type), onGenresLoaded);
            } else {
                getGenresFromServer(media, onGenresLoaded, type);
            }
        } else {
            Log.w(getClass().getSimpleName(), "Unknown media for genre request!");
            onGenresLoaded.onError("Unknown media for genre request!");
        }
    }

    private void getGenresFromServer(final Media media, final OnGenresLoaded onGenresLoaded, final MediaType type) {
        restPlugin.get("genre/" + type.toString() + "/list", new MovieDbRestPlugin.OnComplete() {
            @Override
            public void onSuccess(String data) {
                SparseArray<Genre> loadedGenresCache = createCacheFromResponse(data);
                loadGenresFromCache(media, loadedGenresCache, onGenresLoaded);
                genresCache.put(type, loadedGenresCache);
            }

            @Override
            public void onError() {
                //TODO fix error message
                onGenresLoaded.onError(null);
            }
        });
    }

    @NonNull
    private SparseArray<Genre> createCacheFromResponse(String data) {
        SparseArray<Genre> loadedGenresCache = new SparseArray<>();
        for (Genre genre : jsonParserPlugin.parseGenres(data)) {
            loadedGenresCache.append(genre.getId(), genre);
        }
        return loadedGenresCache;
    }

    private MediaType getMediaType(Media media) {
        if (media instanceof Movie) {
            return MediaType.MOVIE;
        } else if (media instanceof TVShow) {
            return MediaType.TV_SHOW;
        }
        return MediaType.UNKNOWN;
    }

    private void loadGenresFromCache(Media media, SparseArray<Genre> cache, OnGenresLoaded onGenresLoaded) {
        List<Genre> genres = new ArrayList<>();
        for (int genreId : media.getGenreIds()) {
            Genre genre = cache.get(genreId);
            if (genre != null) {
                genres.add(genre);
            }
        }
        onGenresLoaded.onSuccess(media, genres);
    }
}