package hu.gabornovak.movieapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.view.SimpleDraweeView;

import hu.gabornovak.movieapp.R;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.Movie;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String EXTRA_MOVIE = "Extra movie";

    private SimpleDraweeView posterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        posterView = (SimpleDraweeView) findViewById(R.id.poster);

        Movie movie = (Movie) getIntent().getExtras().getSerializable(EXTRA_MOVIE);
        if (movie != null) {
            String url = Logic.getInstance().getPluginFactory().getImagePathResolverPlugin().getMediaPosterUrl(movie);
            posterView.setImageURI(url);
        }
    }

    public static void setExtras(Intent intent, Movie movie) {
        //intent.putExtra(EXTRA_MOVIE, movie);
    }
}
