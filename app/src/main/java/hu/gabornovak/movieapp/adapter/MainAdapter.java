package hu.gabornovak.movieapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import hu.gabornovak.movieapp.fragment.MoviesFragment;
import hu.gabornovak.movieapp.fragment.TVShowsFragment;

/**
 * Contains the tree required tabs (MediaInteractor, TV Shows, People).
 * <p>
 * Created by gnovak on 7/2/2016.
 */

public class MainAdapter extends FragmentStatePagerAdapter {
    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MoviesFragment.newInstance();
            case 1:
                return TVShowsFragment.newInstance();
            case 2:
                return MoviesFragment.newInstance();
            default:
                return MoviesFragment.newInstance();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Movies";
            case 1:
                return "TV Shows";
            case 2:
                return "People";
            default:
                return "Unknown";
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
