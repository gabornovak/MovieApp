package hu.gabornovak.movieapp.logic.gateway;


import java.util.ArrayList;
import java.util.List;

import hu.gabornovak.movieapp.logic.entity.DetailedMovie;
import hu.gabornovak.movieapp.logic.entity.DetailedTVShow;
import hu.gabornovak.movieapp.logic.entity.Media;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.entity.TVShow;
import hu.gabornovak.movieapp.logic.plugin.ConnectionPlugin;
import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.MovieDbRestPlugin;
import hu.gabornovak.movieapp.logic.utils.Pair;
import hu.gabornovak.movieapp.logic.utils.RequestErrorType;

/**
 * Created by gnovak on 7/2/2016.
 */
public class DefaultMediaGateway implements MediaGateway {
    private MovieDbRestPlugin restPlugin;
    private ConnectionPlugin connectionPlugin;
    private JsonParserPlugin jsonParserPlugin;

    public DefaultMediaGateway(MovieDbRestPlugin restPlugin, ConnectionPlugin connectionPlugin, JsonParserPlugin jsonParserPlugin) {
        this.restPlugin = restPlugin;
        this.connectionPlugin = connectionPlugin;
        this.jsonParserPlugin = jsonParserPlugin;
    }

    @Override
    public void loadPopularMovies(final OnMoviesLoaded onMediaLoaded) {
        if (connectionPlugin.hasConnection()) {
            restPlugin.get("movie/popular", new MovieDbRestPlugin.OnComplete() {
                @Override
                public void onSuccess(String data) {
                    List<Movie> movies = jsonParserPlugin.parseMovies(data);
                    if (movies != null) {
                        onMediaLoaded.onSuccess(movies);
                    } else {
                        onMediaLoaded.onError(RequestErrorType.PARSE_ERROR);
                    }
                }

                @Override
                public void onError(RequestErrorType errorType) {
                    onMediaLoaded.onError(errorType);
                }
            });
        } else {
            onMediaLoaded.onError(RequestErrorType.NO_INTERNET_ERROR);
        }
    }

    @Override
    public void loadPopularTVShows(final OnTVShowsLoaded onTVShowsLoaded) {
        if (connectionPlugin.hasConnection()) {
            restPlugin.get("tv/popular", new MovieDbRestPlugin.OnComplete() {
                @Override
                public void onSuccess(String data) {
                    List<TVShow> tvShows = jsonParserPlugin.parseTVShows(data);
                    if (tvShows != null) {
                        onTVShowsLoaded.onSuccess(tvShows);
                    } else {
                        onTVShowsLoaded.onError(RequestErrorType.PARSE_ERROR);
                    }
                }

                @Override
                public void onError(RequestErrorType errorType) {
                    onTVShowsLoaded.onError(errorType);
                }
            });
        } else {
            onTVShowsLoaded.onError(RequestErrorType.NO_INTERNET_ERROR);
        }
    }

    @Override
    public void searchMedia(String searchText, final OnMediaLoaded onMediaLoaded) {
        if (connectionPlugin.hasConnection()) {
            List<Pair<String, String>> params = createParamsForSearch(searchText);
            restPlugin.get("search/multi", params, new MovieDbRestPlugin.OnComplete() {
                @Override
                public void onSuccess(String data) {
                    List<Media> media = jsonParserPlugin.parseMedia(data);
                    if (media != null) {
                        onMediaLoaded.onSuccess(media);
                    } else {
                        onMediaLoaded.onError(RequestErrorType.PARSE_ERROR);
                    }
                }

                @Override
                public void onError(RequestErrorType errorType) {
                    onMediaLoaded.onError(errorType);
                }
            });
        } else {
            onMediaLoaded.onError(RequestErrorType.NO_INTERNET_ERROR);
        }
    }

    @Override
    public void loadDetailedMovie(final Media media, final OnDetailedMovieLoaded onDetailedMovieLoaded) {
        if (connectionPlugin.hasConnection()) {
            restPlugin.get("movie/" + media.getId(), new MovieDbRestPlugin.OnComplete() {
                @Override
                public void onSuccess(String data) {
                    DetailedMovie movie = jsonParserPlugin.parseDetailedMovie(data);
                    if (movie != null) {
                        movie.setMovie((Movie) media);
                        onDetailedMovieLoaded.onSuccess(movie);
                    } else {
                        onDetailedMovieLoaded.onError(RequestErrorType.PARSE_ERROR);
                    }
                }

                @Override
                public void onError(RequestErrorType errorType) {
                    onDetailedMovieLoaded.onError(errorType);
                }
            });
        } else {
            onDetailedMovieLoaded.onError(RequestErrorType.NO_INTERNET_ERROR);
        }
    }

    @Override
    public void loadDetailedTVShow(final Media media, final OnDetailedTVShowLoaded onDetailedTVShowLoaded) {
        if (connectionPlugin.hasConnection()) {
            restPlugin.get("tv/" + media.getId(), new MovieDbRestPlugin.OnComplete() {
                @Override
                public void onSuccess(String data) {
                    DetailedTVShow tvShow = jsonParserPlugin.parseDetailedTVShow(data);
                    if (tvShow != null) {
                        tvShow.setTvShow((TVShow) media);
                        onDetailedTVShowLoaded.onSuccess(tvShow);
                    } else {
                        onDetailedTVShowLoaded.onError(RequestErrorType.PARSE_ERROR);
                    }
                }

                @Override
                public void onError(RequestErrorType errorType) {
                    onDetailedTVShowLoaded.onError(errorType);
                }
            });
        } else {
            onDetailedTVShowLoaded.onError(RequestErrorType.NO_INTERNET_ERROR);
        }
    }

    private List<Pair<String, String>> createParamsForSearch(String searchText) {
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<>("query", searchText));
        return params;
    }
}
