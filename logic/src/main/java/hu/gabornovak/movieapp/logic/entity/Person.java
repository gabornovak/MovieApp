package hu.gabornovak.movieapp.logic.entity;

import java.util.List;

/**
 * Created by gnovak on 7/3/2016.
 */

public class Person {
    private String name;
    private String profilePath;
    private int id;
    private List<Media> knownFor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Media> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<Media> knownFor) {
        this.knownFor = knownFor;
    }
}
