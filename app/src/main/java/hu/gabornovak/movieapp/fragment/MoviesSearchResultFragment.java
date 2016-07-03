package hu.gabornovak.movieapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.List;

import hu.gabornovak.movieapp.adapter.MoviesRecyclerViewAdapter;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.interactor.MediaInteractor;

public class MoviesSearchResultFragment extends ListFragment {
    private static final String ARG_SEARCH_TEXT = "search text";

    public static Fragment newInstance(String searchText) {
        Fragment fragment = new MoviesSearchResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_SEARCH_TEXT, searchText);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void loadList() {
        String searchText = getArguments().getString(ARG_SEARCH_TEXT);

        Logic.getInstance().getMovies().searchMovies(searchText, new MediaInteractor.OnMoviesLoaded() {
            @Override
            public void onMoviesLoaded(final List<Movie> movies) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setAdapter(new MoviesRecyclerViewAdapter(getActivity(), movies));
                        showList();
                    }
                });
            }

            @Override
            public void onError(final String errorMsg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showErrorMessage(errorMsg);
                    }
                });
            }
        });
    }
}
