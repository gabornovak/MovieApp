package hu.gabornovak.movieapp.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.TransitionSet;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import hu.gabornovak.movieapp.R;
import hu.gabornovak.movieapp.adapter.KnownForRecyclerViewAdapter;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.Person;
import hu.gabornovak.movieapp.utils.DraweeTransform;

public class PersonDetailActivity extends AppCompatActivity {
    private static final String EXTRA_PERSON = "Extra person";

    private SimpleDraweeView posterView;
    private TextView name;
    private RecyclerView knownFor;

    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        loadViews();

        setupSharedTransition();
        setupTitleBar();

        Person person = (Person) getIntent().getExtras().getSerializable(EXTRA_PERSON);

        if (person != null) {
            String url = Logic.getInstance().getPluginFactory().getImagePathResolverPlugin().getProfileUrl(person);
            posterView.setImageURI(url);

            loadPerson(person);
        } else {
            //TODO Add some toast
            finish();
        }
    }

    private void loadPerson(Person person) {
        collapsingToolbar.setTitle(person.getName());
        name.setText(person.getName());
        knownFor.setAdapter(new KnownForRecyclerViewAdapter(this, person));
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
        name = (TextView) findViewById(R.id.title);
        knownFor = (RecyclerView) findViewById(R.id.knownFor);
    }

    public static void setExtras(Intent intent, Person person) {
        intent.putExtra(EXTRA_PERSON, person);
    }
}
