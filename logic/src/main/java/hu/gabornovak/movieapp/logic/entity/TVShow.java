package hu.gabornovak.movieapp.logic.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Simple data class for a movie
 *
 * I usually don't like the "_" char in a variable or method name, but I didn't wanted
 * to include the GSON to redefine the field name. In the future I'll need two data class
 * and this should be only used in the json parsing method.
 *
 * JSON example:
 *
    {
         "poster_path": "/jIhL6mlT7AblhbHJgEoiBIOUVl1.jpg",
         "popularity": 41.377024,
         "id": 1399,
         "backdrop_path": "/mUkuc2wyV9dHLG0D0Loaw5pO2s8.jpg",
         "vote_average": 7.94,
         "overview": "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
         "first_air_date": "2011-04-17",
         "origin_country": [
             "US"
         ],
         "genre_ids": [
             10765,
             10759,
             18
         ],
         "original_language": "en",
         "vote_count": 984,
         "name": "Game of Thrones",
         "original_name": "Game of Thrones"
     }
 *
 */
public class TVShow implements Serializable {
    private int id;
    private String name;
    private String overview;
    private String poster_path;
    private float vote_average;
    private String first_air_date;
    private List<Integer> genre_ids;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }
}