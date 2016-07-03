package hu.gabornovak.movieapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Locale;

import hu.gabornovak.movieapp.R;
import hu.gabornovak.movieapp.activity.MovieDetailActivity;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.Genre;
import hu.gabornovak.movieapp.logic.entity.Movie;
import hu.gabornovak.movieapp.logic.gateway.GenreGateway;

public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder> {
    private Activity activity;
    private List<Movie> movies;

    public MoviesRecyclerViewAdapter(Activity activity, List<Movie> movies) {
        this.activity = activity;
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Movie movie = movies.get(position);

        holder.title.setText(movie.getTitle());
        holder.overview.setText(movie.getOverview());
        holder.releaseDate.setText(movie.getDate());

        holder.rating.setText(String.format(Locale.getDefault(), "%.1f", movie.getRating()));
        holder.ratingProgress.setPivotX(0);
        holder.ratingProgress.setScaleX(movie.getRating() / 10f);

        holder.poster.setImageURI(Logic.getInstance().getPluginFactory().getImagePathResolverPlugin().getMediaPosterUrl(movie));

        holder.view.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent intent = new Intent(activity, MovieDetailActivity.class);
                                               MovieDetailActivity.setExtras(intent, movie);

                                               //TODO Fix shared transition. It has issues with Fresco (maybe replace it to something else).
/*                                               ActivityOptionsCompat options = ActivityOptionsCompat.
                                                       makeSceneTransitionAnimation(activity, holder.poster, "poster");
                                               activity.startActivity(intent, options.toBundle());*/
                                               activity.startActivity(intent);
                                           }
                                       }
        );

        Logic.getInstance().getGatewayFactory().getGenreGateway().loadGenresForMovie(movie, new GenreGateway.OnGenresLoaded() {
            @Override
            public void onSuccess(Movie movie, final List<Genre> genres) {
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            holder.genres.setText(buildGenresString(genres));
                        }
                    });
                }
            }

            @Override
            public void onError(String errorMsg) {
                //TODO Handle error
                System.out.println("Error occuredd");
            }
        });
    }

    @NonNull
    private String buildGenresString(List<Genre> genres) {
        StringBuilder genresString = new StringBuilder();
        for (int i = 0; i < genres.size(); i++) {
            genresString.append(genres.get(i).getName());
            if (i != genres.size() - 1) {
                genresString.append(", ");
            }
        }
        return genresString.toString();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        final SimpleDraweeView poster;
        final TextView title;
        final TextView overview;
        final TextView rating;
        final View ratingProgress;
        final TextView releaseDate;
        final TextView genres;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            title = (TextView) view.findViewById(R.id.title);
            genres = (TextView) view.findViewById(R.id.genres);
            releaseDate = (TextView) view.findViewById(R.id.releaseDate);
            overview = (TextView) view.findViewById(R.id.overview);
            rating = (TextView) view.findViewById(R.id.rating);
            poster = (SimpleDraweeView) view.findViewById(R.id.poster);
            ratingProgress = view.findViewById(R.id.ratingProgress);
        }
    }
}
