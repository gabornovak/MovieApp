package hu.gabornovak.movieapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.List;

import hu.gabornovak.movieapp.adapter.MediaRecyclerViewAdapter;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.Media;
import hu.gabornovak.movieapp.logic.interactor.MediaInteractor;
import hu.gabornovak.movieapp.logic.utils.RequestErrorType;

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

        Logic.getInstance().getMedia().searchForMedia(searchText, new MediaInteractor.OnMediaLoaded() {
            @Override
            public void onMediaLoaded(final List<Media> media) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setAdapter(new MediaRecyclerViewAdapter(getActivity(), media));
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
