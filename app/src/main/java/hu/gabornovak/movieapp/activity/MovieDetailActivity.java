package hu.gabornovak.movieapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.view.SimpleDraweeView;

import hu.gabornovak.movieapp.R;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.Media;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String EXTRA_MEDIA = "Extra media";

    private SimpleDraweeView posterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        posterView = (SimpleDraweeView) findViewById(R.id.poster);

        Media media = (Media) getIntent().getExtras().getSerializable(EXTRA_MEDIA);
        if (media != null) {
            String url = Logic.getInstance().getPluginFactory().getImagePathResolverPlugin().getMediaPosterUrl(media);
            posterView.setImageURI(url);
        }
    }

    public static void setExtras(Intent intent, Media media) {
        intent.putExtra(EXTRA_MEDIA, media);
    }
}
