package hu.gabornovak.movieapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Locale;

import hu.gabornovak.movieapp.R;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.DetailedMovie;
import hu.gabornovak.movieapp.logic.entity.Genre;
import hu.gabornovak.movieapp.logic.entity.Media;
import hu.gabornovak.movieapp.logic.interactor.MediaInteractor;
import hu.gabornovak.movieapp.logic.utils.RequestErrorType;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String EXTRA_MEDIA = "Extra media";

    private SimpleDraweeView posterView;
    private TextView title;
    private TextView tagline;
    private TextView genres;
    private TextView releaseDate;
    private TextView rating;
    private TextView overview;
    private TextView playTime;
    private TextView imdb;
    private TextView website;
    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        loadViews();

        setupTitleBar();

        Media media = (Media) getIntent().getExtras().getSerializable(EXTRA_MEDIA);

        if (media != null) {
            String url = Logic.getInstance().getPluginFactory().getImagePathResolverPlugin().getMediaPosterUrl(media);
            posterView.setImageURI(url);
            loadMediaInfoIntoViews(media);
            loadDetailsAsync(media);
        } else {
            //TODO Add some toast
            finish();
        }
    }


    private void loadDetailsAsync(final Media media) {
        hideDetailViews();
        Logic.getInstance().getMedia().getMovieDetails(media, new MediaInteractor.OnDetailedMovieLoaded() {
            @Override
            public void onDetailedMovieLoaded(final DetailedMovie movie) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Check if the views are still there
                        if (tagline != null) {
                            loadDetailsIntoViews(movie);
                        }
                    }
                });
            }

            @Override
            public void onError(RequestErrorType errorType) {
                Snackbar snackBar = Snackbar.make(tagline, getString(R.string.error_in_detail_reqest_message), Snackbar.LENGTH_LONG);
                snackBar.setActionTextColor(ActivityCompat.getColor(MovieDetailActivity.this, R.color.main_background));
                snackBar.setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadDetailsAsync(media);
                    }
                });
                snackBar.show();
            }
        });
    }

    private void hideDetailViews() {
        tagline.setVisibility(View.GONE);
        playTime.setVisibility(View.GONE);
        genres.setVisibility(View.GONE);
        imdb.setVisibility(View.GONE);
        website.setVisibility(View.GONE);
    }

    private void showDetailViews() {
        tagline.setVisibility(View.VISIBLE);
        playTime.setVisibility(View.VISIBLE);
        genres.setVisibility(View.VISIBLE);
        imdb.setVisibility(View.VISIBLE);
        website.setVisibility(View.VISIBLE);
    }

    private void loadDetailsIntoViews(DetailedMovie movie) {
        showDetailViews();
        tagline.setText(movie.getTagline());
        playTime.setText(movie.getRuntime() + " mins");
        genres.setText(createGenresText(movie.getGenres()));
        handleImdb(movie);
        handleWebsite(movie);
    }

    private void handleImdb(final DetailedMovie movie) {
        if (movie.getImdb_id() != null && !movie.getImdb_id().isEmpty()) {
            setTextViewOnClick(imdb, "http://www.imdb.com/title/" + movie.getImdb_id());
        } else {
            imdb.setVisibility(View.GONE);
        }
    }

    private void handleWebsite(final DetailedMovie movie) {
        if (movie.getHomepage() != null && !movie.getHomepage().isEmpty()) {
            setTextViewOnClick(website, movie.getHomepage());
        } else {
            website.setVisibility(View.GONE);
        }
    }

    private void setTextViewOnClick(TextView textView, final String url) {
        textView.setVisibility(View.VISIBLE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(url);
            }
        });
    }


    private void openUrl(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    private String createGenresText(List<Genre> genres) {
        StringBuilder genresString = new StringBuilder();
        for (int i = 0; i < genres.size(); i++) {
            genresString.append(genres.get(i).getName());
            if (i != genres.size() - 1) {
                genresString.append(", ");
            }
        }
        return genresString.toString();
    }

    private void loadMediaInfoIntoViews(Media media) {
        collapsingToolbar.setTitle(media.getTitle());
        title.setText(media.getTitle());
        rating.setText(String.format(Locale.getDefault(), "%.1f / 10", media.getRating()));
        overview.setText(media.getOverview());
        releaseDate.setText(media.getDate());
    }

    private void setupTitleBar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setCollapsedTitleTextColor(ActivityCompat.getColor(this, android.R.color.white));
        collapsingToolbar.setExpandedTitleColor(ActivityCompat.getColor(this, android.R.color.white));
        collapsingToolbar.setContentScrimColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        collapsingToolbar.setStatusBarScrimColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark));
    }

    private void loadViews() {
        posterView = (SimpleDraweeView) findViewById(R.id.poster);
        tagline = (TextView) findViewById(R.id.tagline);
        imdb = (TextView) findViewById(R.id.imdb);
        website = (TextView) findViewById(R.id.website);
        title = (TextView) findViewById(R.id.title);
        playTime = (TextView) findViewById(R.id.playTime);
        genres = (TextView) findViewById(R.id.genres);
        rating = (TextView) findViewById(R.id.rating);
        overview = (TextView) findViewById(R.id.overview);
        releaseDate = (TextView) findViewById(R.id.releaseDate);
    }

    public static void setExtras(Intent intent, Media media) {
        intent.putExtra(EXTRA_MEDIA, media);
    }
}
