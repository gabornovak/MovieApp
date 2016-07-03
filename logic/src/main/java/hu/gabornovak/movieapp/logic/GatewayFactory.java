package hu.gabornovak.movieapp.logic;

import hu.gabornovak.movieapp.logic.gateway.GenreGateway;
import hu.gabornovak.movieapp.logic.gateway.MovieGateway;
import hu.gabornovak.movieapp.logic.gateway.TVShowGateway;

public interface GatewayFactory {
    MovieGateway getMovieGateway();

    TVShowGateway getTVShowGateway();

    GenreGateway getGenreGateway();
}
