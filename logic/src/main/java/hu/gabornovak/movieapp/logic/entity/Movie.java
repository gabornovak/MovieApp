package hu.gabornovak.movieapp.logic.entity;

import java.io.Serializable;

public class Movie extends Media implements Serializable {
    private String title;
    private String releaseDate;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDate() {
        return releaseDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
