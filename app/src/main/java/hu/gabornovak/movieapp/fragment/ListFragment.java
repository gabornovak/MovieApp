package hu.gabornovak.movieapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import hu.gabornovak.movieapp.R;

public abstract class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView messageText;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        messageText = (TextView) view.findViewById(R.id.message);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadList();
            }
        });

        showProgress();
        loadList();

        return view;
    }

    protected abstract void loadList();

    protected void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    protected void runOnUiThread(Runnable runnable) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(runnable);
        }
    }

    protected void showProgress() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        messageText.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    protected void showErrorMessage(String msg) {
        messageText.setText(msg);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        messageText.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    protected void showList() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        messageText.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }
}
