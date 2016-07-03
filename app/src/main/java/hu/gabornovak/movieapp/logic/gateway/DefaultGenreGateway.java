package hu.gabornovak.movieapp.logic.gateway;


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
import hu.gabornovak.movieapp.logic.plugin.ConnectionPlugin;
import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.MovieDbRestPlugin;
import hu.gabornovak.movieapp.logic.utils.RequestErrorType;

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
    private ConnectionPlugin connectionPlugin;
    private JsonParserPlugin jsonParserPlugin;

    /**
     * In memory cache
     */
    private Map<MediaType, SparseArray<Genre>> genresCache;

    public DefaultGenreGateway(MovieDbRestPlugin restPlugin, ConnectionPlugin connectionPlugin, JsonParserPlugin jsonParserPlugin) {
        this.restPlugin = restPlugin;
        this.connectionPlugin = connectionPlugin;
        this.jsonParserPlugin = jsonParserPlugin;

        genresCache = new HashMap<>();
    }

    @Override
    public void loadGenres(final Media media, final OnGenresLoaded onGenresLoaded) {
        if (connectionPlugin.hasConnection()) {
            final MediaType type = getMediaType(media);
            if (type != MediaType.UNKNOWN) {
                if (genresCache.containsKey(type)) {
                    loadGenresFromCache(media, genresCache.get(type), onGenresLoaded);
                } else {
                    getGenresFromServer(media, onGenresLoaded, type);
                }
            } else {
                Log.w(getClass().getSimpleName(), "Unknown media for genre request!");
                onGenresLoaded.onError(RequestErrorType.UNKNOWN_TYPE_ERROR);
            }
        } else {
            onGenresLoaded.onError(RequestErrorType.NO_INTERNET_ERROR);
        }
    }

    private void getGenresFromServer(final Media media, final OnGenresLoaded onGenresLoaded, final MediaType type) {
        restPlugin.get("genre/" + type.toString() + "/list", new MovieDbRestPlugin.OnComplete() {
            @Override
            public void onSuccess(String data) {
                SparseArray<Genre> loadedGenresCache = createCacheFromResponse(data);
                if (loadedGenresCache != null) {
                    loadGenresFromCache(media, loadedGenresCache, onGenresLoaded);
                    genresCache.put(type, loadedGenresCache);
                } else {
                    onGenresLoaded.onError(RequestErrorType.PARSE_ERROR);
                }
            }

            @Override
            public void onError(RequestErrorType errorType) {
                onGenresLoaded.onError(errorType);
            }
        });
    }

    private SparseArray<Genre> createCacheFromResponse(String data) {
        SparseArray<Genre> loadedGenresCache = new SparseArray<>();
        List<Genre> genres = jsonParserPlugin.parseGenres(data);
        if (genres != null) {
            for (Genre genre : genres) {
                loadedGenresCache.append(genre.getId(), genre);
            }
            return loadedGenresCache;
        }
        return null;
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