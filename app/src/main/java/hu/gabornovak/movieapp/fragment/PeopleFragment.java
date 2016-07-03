package hu.gabornovak.movieapp.fragment;

import java.util.List;

import hu.gabornovak.movieapp.adapter.PeopleRecyclerViewAdapter;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.Person;
import hu.gabornovak.movieapp.logic.interactor.PersonInteractor;
import hu.gabornovak.movieapp.logic.utils.RequestErrorType;

public class PeopleFragment extends ListFragment {
    public static PeopleFragment newInstance() {
        return new PeopleFragment();
    }

    @Override
    protected void loadList() {
        Logic.getInstance().getPeople().getPopularPeople(new PersonInteractor.OnPeopleLoaded() {
            @Override
            public void onPeopleLoaded(final List<Person> people) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setAdapter(new PeopleRecyclerViewAdapter(getActivity(), people));
                        showList();
                    }
                });
            }

            @Override
            public void onError(final RequestErrorType errorType) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showErrorMessage(errorType);
                    }
                });
            }
        });
    }
}
