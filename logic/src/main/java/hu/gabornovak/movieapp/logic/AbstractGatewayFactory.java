package hu.gabornovak.movieapp.logic;

import hu.gabornovak.movieapp.logic.gateway.GenreGateway;
import hu.gabornovak.movieapp.logic.gateway.MovieGateway;

public abstract class AbstractGatewayFactory implements GatewayFactory {
    private LazyInstance<MovieGateway> movieGateway = new LazyInstance<MovieGateway>() {
        @Override
        protected MovieGateway createInstance() {
            return createMovieGateway();
        }
    };

    public abstract MovieGateway createMovieGateway();

    public MovieGateway getMovieGateway() {
        return movieGateway.getInstance();
    }

    private LazyInstance<GenreGateway> genreGateway = new LazyInstance<GenreGateway>() {
        @Override
        protected GenreGateway createInstance() {
            return createGenreGateway();
        }
    };

    public abstract GenreGateway createGenreGateway();

    public GenreGateway getGenreGateway() {
        return genreGateway.getInstance();
    }
}
