package hu.gabornovak.movieapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Locale;

import hu.gabornovak.movieapp.R;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.DetailedMovie;
import hu.gabornovak.movieapp.logic.entity.DetailedTVShow;
import hu.gabornovak.movieapp.logic.entity.Genre;
import hu.gabornovak.movieapp.logic.entity.Media;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.entity.Season;
import hu.gabornovak.movieapp.logic.entity.TVShow;
import hu.gabornovak.movieapp.logic.interactor.MediaInteractor;
import hu.gabornovak.movieapp.logic.utils.RequestErrorType;
import hu.gabornovak.movieapp.utils.DraweeTransform;

public class MediaDetailActivity extends AppCompatActivity {
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
    private TextView seasons;
    private View tvShowDetailsLayout;

    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_detail);

        loadViews();

        setupSharedTransition();
        setupTitleBar();

        Media media = (Media) getIntent().getExtras().getSerializable(EXTRA_MEDIA);

        if (media != null) {
            String url = Logic.getInstance().getPluginFactory().getImagePathResolverPlugin().getMediaPosterUrl(media);
            posterView.setImageURI(url);

            loadMedia(media);
        } else {
            //TODO Add some toast
            finish();
        }
    }

    private void loadMedia(Media media) {
        loadMediaInfoIntoViews(media);
        loadDetailsAsync(media);
    }

    private void setupSharedTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionSet transitionSet = new TransitionSet();
            transitionSet.addTransition(new ChangeBounds());
            transitionSet.addTransition(new DraweeTransform(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.CENTER_CROP));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setSharedElementEnterTransition(transitionSet);
            }
        }
    }

    private void loadDetailsAsync(final Media media) {
        if (media instanceof Movie) {
            Logic.getInstance().getMedia().getMovieDetails(media, new MediaInteractor.OnDetailedMovieLoaded() {
                @Override
                public void onDetailedMovieLoaded(final DetailedMovie movie) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Check if the views are still there
                            if (tagline != null) {
                                loadMovieDetailsIntoViews(movie);
                            }
                        }
                    });
                }

                @Override
                public void onError(RequestErrorType errorType) {
                    showErrorSnackBar(media);
                }
            });
        } else if (media instanceof TVShow) {
            Logic.getInstance().getMedia().getTVShowDetails(media, new MediaInteractor.OnDetailedTVShowLoaded() {
                @Override
                public void onDetailedTVShowLoaded(final DetailedTVShow tvShow) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Check if the views are still there
                            if (tagline != null) {
                                loadTVShowDetailsIntoViews(tvShow);
                            }
                        }
                    });
                }

                @Override
                public void onError(RequestErrorType errorType) {
                    showErrorSnackBar(media);
                }
            });
        }
    }

    private void showErrorSnackBar(final Media media) {
        Snackbar snackBar = Snackbar.make(tagline, getString(R.string.error_in_detail_reqest_message), Snackbar.LENGTH_LONG);
        snackBar.setActionTextColor(ActivityCompat.getColor(MediaDetailActivity.this, R.color.main_background));
        snackBar.setAction(R.string.retry_action_text, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDetailsAsync(media);
            }
        });
        snackBar.show();
    }

    private void loadTVShowDetailsIntoViews(DetailedTVShow tvShow) {
        tagline.setVisibility(View.GONE);
        playTime.setVisibility(View.GONE);
        imdb.setVisibility(View.GONE);

        genres.setVisibility(View.VISIBLE);
        genres.setText(createGenresText(tvShow.getGenres()));
        releaseDate.setText(tvShow.getFirstAirDate() + " - " + tvShow.getLastAirDate());

        handleWebsite(tvShow);
        showSeasonInfo(tvShow);
    }

    private void showSeasonInfo(DetailedTVShow tvShow) {
        tvShowDetailsLayout.setVisibility(View.VISIBLE);
        StringBuilder seasonString = new StringBuilder();

        seasonString.append(String.format(Locale.getDefault(), getString(R.string.in_total_season_text), tvShow.getNumberOfSeasons(), tvShow.getNumberOfEpisodes()));
        seasonString.append("\n\n");
        for (Season season : tvShow.getSeasons()) {
            seasonString.append(String.format(Locale.getDefault(), getString(R.string.season_episode_text), season.getSeasonNumber(), season.getEpisodeCount()));
            seasonString.append("\n");
        }
        seasons.setText(seasonString.toString());
    }

    private void loadMovieDetailsIntoViews(DetailedMovie movie) {
        tagline.setVisibility(View.VISIBLE);
        tagline.setText(movie.getTagline());

        playTime.setVisibility(View.VISIBLE);

        playTime.setText(String.format(Locale.getDefault(), getString(R.string.runtime_text), movie.getRuntime()));

        genres.setVisibility(View.VISIBLE);
        genres.setText(createGenresText(movie.getGenres()));
        tvShowDetailsLayout.setVisibility(View.GONE);

        handleImdb(movie);
        handleWebsite(movie);
    }

    private void handleImdb(final DetailedMovie movie) {
        if (movie.getImdb_id() != null && !movie.getImdb_id().isEmpty()) {
            imdb.setVisibility(View.VISIBLE);
            setTextViewOnClick(imdb, "http://www.imdb.com/title/" + movie.getImdb_id());
        } else {
            imdb.setVisibility(View.GONE);
        }
    }

    private void handleWebsite(DetailedTVShow tvShow) {
        if (tvShow.getHomepage() != null && !tvShow.getHomepage().isEmpty()) {
            website.setVisibility(View.VISIBLE);
            setTextViewOnClick(website, tvShow.getHomepage());
        } else {
            website.setVisibility(View.GONE);
        }
    }

    private void handleWebsite(final DetailedMovie movie) {
        if (movie.getHomepage() != null && !movie.getHomepage().isEmpty()) {
            website.setVisibility(View.VISIBLE);
            setTextViewOnClick(website, movie.getHomepage());
        } else {
            website.setVisibility(View.GONE);
        }
    }

    private void setTextViewOnClick(TextView textView, final String url) {
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
        //Set title
        collapsingToolbar.setTitle(media.getTitle());

        //Hide every detail (not yet downloaded) info
        tagline.setVisibility(View.GONE);
        playTime.setVisibility(View.GONE);
        genres.setVisibility(View.GONE);
        imdb.setVisibility(View.GONE);
        website.setVisibility(View.GONE);
        tvShowDetailsLayout.setVisibility(View.GONE);

        //Set init information
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
        seasons = (TextView) findViewById(R.id.seasons);
        tvShowDetailsLayout = findViewById(R.id.show_details);
    }

    public static void setExtras(Intent intent, Media media) {
        intent.putExtra(EXTRA_MEDIA, media);
    }
}
