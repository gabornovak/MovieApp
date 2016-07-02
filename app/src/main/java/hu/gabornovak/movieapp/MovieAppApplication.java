package hu.gabornovak.movieapp;

import android.app.Application;

import hu.gabornovak.movieapp.logic.Logic;

/**
 * Created by gnovak on 7/2/2016.
 */
public class MovieAppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logic.getInstance().init(getApplicationContext());
    }
}
