package hu.gabornovak.movieapp.fragment;

import java.util.List;

import hu.gabornovak.movieapp.adapter.MediaRecyclerViewAdapter;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.interactor.MediaInteractor;
import hu.gabornovak.movieapp.logic.utils.RequestErrorType;

public class MoviesFragment extends ListFragment {
    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    protected void loadList() {
        Logic.getInstance().getMedia().getPopularMovies(new MediaInteractor.OnMoviesLoaded() {
            @Override
            public void onMoviesLoaded(final List<Movie> movies) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setAdapter(new MediaRecyclerViewAdapter(getActivity(), movies));
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
