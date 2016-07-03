package hu.gabornovak.movieapp.logic.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gnovak on 7/3/2016.
 */

public abstract class Media implements Serializable {
    private int id;
    private String posterPath;
    private List<Integer> genreIds;
    private float rating;
    private String overview;

    public abstract String getTitle();

    public abstract String getDate();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
