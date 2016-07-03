package hu.gabornovak.movieapp.fragment;

import java.util.List;

import hu.gabornovak.movieapp.adapter.MediaRecyclerViewAdapter;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.TVShow;
import hu.gabornovak.movieapp.logic.interactor.MediaInteractor;

public class TVShowsFragment extends ListFragment {
    public static TVShowsFragment newInstance() {
        return new TVShowsFragment();
    }

    @Override
    protected void loadList() {
        Logic.getInstance().getMedia().getPopularTVShows(new MediaInteractor.OnTVShowsLoaded() {
            @Override
            public void onTVShowsLoaded(final List<TVShow> tvShows) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setAdapter(new MediaRecyclerViewAdapter(getActivity(), tvShows));
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
