package hu.gabornovak.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hu.gabornovak.movieapp.R;
import hu.gabornovak.movieapp.logic.entity.Movie;

public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder> {
    private List<Movie> movies;

    public MoviesRecyclerViewAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.movie = movies.get(position);

        holder.title.setText(movies.get(position).getTitle());
        holder.overview.setText(movies.get(position).getOverview());
        //holder.poster.setImageURI(Logic.getInstance().getPluginFactory().getRestPlugin());

        holder.view.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                           }
                                       }
        );
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Movie movie;

        public final View view;
        public final SimpleDraweeView poster;
        public final TextView title;
        public final TextView overview;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            title = (TextView) view.findViewById(R.id.title);
            overview = (TextView) view.findViewById(R.id.overview);
            poster = (SimpleDraweeView) view.findViewById(R.id.poster);
        }
    }
}
