package hu.gabornovak.movieapp.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hu.gabornovak.movieapp.R;
import hu.gabornovak.movieapp.fragment.MoviesSearchResultFragment;

public class SearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            setTitle("Search \"" + query + "\"");

            getSupportFragmentManager().beginTransaction().add(R.id.container, MoviesSearchResultFragment.newInstance(query)).commit();
        }
    }
}
