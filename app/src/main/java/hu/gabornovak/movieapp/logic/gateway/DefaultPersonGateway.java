package hu.gabornovak.movieapp.logic.gateway;


import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Person;
import hu.gabornovak.movieapp.logic.plugin.ConnectionPlugin;
import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.MovieDbRestPlugin;
import hu.gabornovak.movieapp.logic.utils.RequestErrorType;

/**
 * Created by gnovak on 7/2/2016.
 */
public class DefaultPersonGateway implements PersonGateway {
    private MovieDbRestPlugin restPlugin;
    private ConnectionPlugin connectionPlugin;
    private JsonParserPlugin jsonParserPlugin;

    public DefaultPersonGateway(MovieDbRestPlugin restPlugin, ConnectionPlugin connectionPlugin, JsonParserPlugin jsonParserPlugin) {
        this.restPlugin = restPlugin;
        this.connectionPlugin = connectionPlugin;
        this.jsonParserPlugin = jsonParserPlugin;
    }

    @Override
    public void loadPopularPeople(final OnPeopleLoaded onPeopleLoaded) {
        if (connectionPlugin.hasConnection()) {
            restPlugin.get("person/popular", new MovieDbRestPlugin.OnComplete() {
                @Override
                public void onSuccess(String data) {
                    List<Person> people = jsonParserPlugin.parsePeople(data);
                    if (people == null) {
                        onPeopleLoaded.onError(RequestErrorType.PARSE_ERROR);
                    } else {
                        onPeopleLoaded.onSuccess(people);
                    }
                }

                @Override
                public void onError(RequestErrorType errorType) {
                    onPeopleLoaded.onError(errorType);
                }
            });
        } else {
            onPeopleLoaded.onError(RequestErrorType.NO_INTERNET_ERROR);
        }
    }
}
