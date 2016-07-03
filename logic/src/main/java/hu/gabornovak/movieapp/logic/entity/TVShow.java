package hu.gabornovak.movieapp.logic.entity;

public class TVShow extends Media {
    private String name;
    private String firstAirDate;

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getDate() {
        return firstAirDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }
}