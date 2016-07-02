package hu.gabornovak.movieapp.logic;

import hu.gabornovak.movieapp.logic.gateway.GenreGateway;
import hu.gabornovak.movieapp.logic.gateway.MovieGateway;

public interface GatewayFactory {
    MovieGateway getMovieGateway();

    GenreGateway getGenreGateway();
}
