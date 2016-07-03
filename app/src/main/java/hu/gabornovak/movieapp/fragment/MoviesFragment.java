package hu.gabornovak.movieapp.fragment;

import java.util.List;

import hu.gabornovak.movieapp.adapter.MoviesRecyclerViewAdapter;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.interactor.MediaInteractor;

public class MoviesFragment extends ListFragment {
    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    protected void loadList() {
        Logic.getInstance().getMovies().getPopularMovies(new MediaInteractor.OnMoviesLoaded() {
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
