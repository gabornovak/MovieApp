package hu.gabornovak.movieapp.logic.gateway;

import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Person;
import hu.gabornovak.movieapp.logic.utils.RequestErrorType;

/**
 * Created by gnovak on 7/2/2016.
 */

public interface PersonGateway {
    interface OnPeopleLoaded {
        void onSuccess(List<Person> people);
        void onError(RequestErrorType errorType);
    }

    void loadPopularPeople(OnPeopleLoaded onPeopleLoaded);
}
