package hu.gabornovak.movieapp.logic.gateway;

import java.util.List;

import hu.gabornovak.movieapp.logic.entity.Person;

/**
 * Created by gnovak on 7/2/2016.
 */

public interface PersonGateway {
    interface OnPeopleLoaded {
        void onSuccess(List<Person> people);
        void onError(String errorMsg);
    }

    void loadPopularPeople(OnPeopleLoaded onPeopleLoaded);
}
