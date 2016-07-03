package hu.gabornovak.movieapp.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hu.gabornovak.movieapp.R;
import hu.gabornovak.movieapp.logic.utils.RequestErrorType;

import static android.view.View.GONE;

abstract class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView messageText;
    private View progressLayout;
    private View errorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        loadViews(view);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadList();
            }
        });

        showProgress();
        loadList();

        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress();
                loadList();
            }
        });

        return view;
    }

    private void loadViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.grid);
        progressLayout = view.findViewById(R.id.loading_layout);
        messageText = (TextView) view.findViewById(R.id.message);
        errorLayout = view.findViewById(R.id.error_layout);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
    }

    protected abstract void loadList();

    void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    void runOnUiThread(Runnable runnable) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(runnable);
        }
    }

    private void fadeInViews(View... views) {
        for (final View view : views) {
            view.animate().cancel();
            view.animate().alpha(1f).setDuration(200).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    view.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    private void fadeOutViews(View... views) {
        for (final View view : views) {
            view.animate().cancel();
            view.animate().alpha(0f).setDuration(200).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    view.setVisibility(GONE);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    private void showProgress() {
        fadeOutViews(recyclerView, errorLayout);
        fadeInViews(progressLayout);
        swipeRefreshLayout.setRefreshing(false);
    }

    void showErrorMessage(String message) {
        messageText.setText(message);
        fadeOutViews(recyclerView, progressLayout);
        fadeInViews(errorLayout);
        swipeRefreshLayout.setRefreshing(false);
    }

    void showErrorMessage(RequestErrorType errorType) {
        switch (errorType) {
            case NO_INTERNET_ERROR:
                showErrorMessage(getString(R.string.no_internet_error_message));
                break;
            case PARSE_ERROR:
                showErrorMessage(getString(R.string.parse_error_message));
                break;
            default:
                showErrorMessage(getString(R.string.general_error_message));
                break;
        }
    }

    void showList() {
        fadeOutViews(errorLayout, progressLayout);
        fadeInViews(recyclerView);
        swipeRefreshLayout.setRefreshing(false);
    }
}
