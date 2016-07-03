package hu.gabornovak.movieapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import hu.gabornovak.movieapp.R;
import hu.gabornovak.movieapp.fragment.MoviesFragment;
import hu.gabornovak.movieapp.fragment.PeopleFragment;
import hu.gabornovak.movieapp.fragment.TVShowsFragment;

/**
 * Contains the tree required tabs (MediaInteractor, TV Shows, People).
 * <p>
 * Created by gnovak on 7/2/2016.
 */

public class MainAdapter extends FragmentStatePagerAdapter {
    private Context context;

    public MainAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MoviesFragment.newInstance();
            case 1:
                return TVShowsFragment.newInstance();
            case 2:
                return PeopleFragment.newInstance();
            default:
                return MoviesFragment.newInstance();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.movies_tab_title);
            case 1:
                return context.getString(R.string.tv_shows_tab_title);
            case 2:
                return context.getString(R.string.people_tab_title);
            default:
                return context.getString(R.string.unkown_tab_title);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
