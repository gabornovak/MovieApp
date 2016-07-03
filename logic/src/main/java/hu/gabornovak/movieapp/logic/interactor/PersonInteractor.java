package hu.gabornovak.movieapp.logic.interactor;

import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Person;
import hu.gabornovak.movieapp.logic.gateway.PersonGateway;
import hu.gabornovak.movieapp.logic.utils.RequestErrorType;

/**
 * Created by gnovak on 7/2/2016.
 */
public class PersonInteractor {
    public interface OnPeopleLoaded {
        void onPeopleLoaded(List<Person> people);
        void onError(RequestErrorType errorType);
    }

    private PersonGateway personGateway;

    public PersonInteractor(PersonGateway personGateway) {
        this.personGateway = personGateway;
    }

    public void getPopularPeople(final OnPeopleLoaded onMoviesLoaded) {
        personGateway.loadPopularPeople(new PersonGateway.OnPeopleLoaded() {
            @Override
            public void onSuccess(List<Person> people) {
                onMoviesLoaded.onPeopleLoaded(people);
            }

            @Override
            public void onError(RequestErrorType errorType) {
                onMoviesLoaded.onError(errorType);
            }
        });
    }
}
