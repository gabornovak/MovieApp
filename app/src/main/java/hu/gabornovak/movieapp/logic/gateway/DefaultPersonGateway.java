package hu.gabornovak.movieapp.logic.gateway;


import hu.gabornovak.movieapp.logic.plugin.JsonParserPlugin;
import hu.gabornovak.movieapp.logic.plugin.MovieDbRestPlugin;

/**
 * Created by gnovak on 7/2/2016.
 */
public class DefaultPersonGateway implements PersonGateway {
    private MovieDbRestPlugin restPlugin;
    private JsonParserPlugin jsonParserPlugin;

    public DefaultPersonGateway(MovieDbRestPlugin restPlugin, JsonParserPlugin jsonParserPlugin) {
        this.restPlugin = restPlugin;
        this.jsonParserPlugin = jsonParserPlugin;
    }

    @Override
    public void loadPopularPeople(final OnPeopleLoaded onPeopleLoaded) {
        restPlugin.get("person/popular", new MovieDbRestPlugin.OnComplete() {
            @Override
            public void onSuccess(String data) {
                onPeopleLoaded.onSuccess(jsonParserPlugin.parsePeople(data));
            }

            @Override
            public void onError() {
                onPeopleLoaded.onError("Error happened...");
            }
        });
    }
}
