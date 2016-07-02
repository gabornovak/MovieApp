package hu.gabornovak.movieapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import hu.gabornovak.movieapp.adapter.MainAdapter;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.interactor.Movies;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logic.getInstance().getMovies().getPopularMovies(new Movies.OnMoviesLoaded() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                System.out.println(movies);
            }

            @Override
            public void onError(String errorMsg) {
                System.err.println(errorMsg);
            }
        });
    }
}
