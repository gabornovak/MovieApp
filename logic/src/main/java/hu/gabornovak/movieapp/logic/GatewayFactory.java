package hu.gabornovak.movieapp.logic;

import hu.gabornovak.movieapp.logic.gateway.GenreGateway;
import hu.gabornovak.movieapp.logic.gateway.MediaGateway;

public interface GatewayFactory {
    MediaGateway getMediaGateway();

    GenreGateway getGenreGateway();
}
