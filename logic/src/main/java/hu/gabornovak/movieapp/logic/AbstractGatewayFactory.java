package hu.gabornovak.movieapp.logic;

import hu.gabornovak.movieapp.logic.gateway.GenreGateway;
import hu.gabornovak.movieapp.logic.gateway.MediaGateway;
import hu.gabornovak.movieapp.logic.gateway.PersonGateway;

public abstract class AbstractGatewayFactory implements GatewayFactory {
    private LazyInstance<MediaGateway> movieGateway = new LazyInstance<MediaGateway>() {
        @Override
        protected MediaGateway createInstance() {
            return createMovieGateway();
        }
    };

    public abstract MediaGateway createMovieGateway();

    public MediaGateway getMediaGateway() {
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

    private LazyInstance<PersonGateway> personGateway = new LazyInstance<PersonGateway>() {
        @Override
        protected PersonGateway createInstance() {
            return createPersonGateway();
        }
    };

    public abstract PersonGateway createPersonGateway();

    public PersonGateway getPersonGateway() {
        return personGateway.getInstance();
    }
}
