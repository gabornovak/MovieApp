package hu.gabornovak.movieapp.logic.entity;

/**
 * Simple data class for a movie
 *
 * JSON example:
 *     {
         "poster_path": "/9KQX22BeFzuNM66pBA6JbiaJ7Mi.jpg",
         "adult": false,
         "overview": "We always knew they were coming back. Using recovered alien technology, the nations of Earth have collaborated on an immense defense program to protect the planet. But nothing can prepare us for the aliens’ advanced and unprecedented force. Only the ingenuity of a few brave men and women can bring our world back from the brink of extinction.",
         "release_date": "2016-06-22",
         "genre_ids": [
            28,
            12,
            878
         ],
         "id": 47933,
         "original_title": "Independence Day: Resurgence",
         "original_language": "en",
         "title": "Independence Day: Resurgence",
         "backdrop_path": "/8SqBiesvo1rh9P1hbJTmnVum6jv.jpg",
         "popularity": 35.359988,
         "vote_count": 302,
         "video": false,
         "vote_average": 4.51
       }
 *
 */
public class Movie {
    private int id;
    private String title;
    private String overview;
    private String poster_path;
    private float vote_average;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}
