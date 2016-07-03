package hu.gabornovak.movieapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Locale;

import hu.gabornovak.movieapp.R;
import hu.gabornovak.movieapp.activity.MediaDetailActivity;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.Media;

public class MediaRecyclerViewAdapter extends RecyclerView.Adapter<MediaRecyclerViewAdapter.ViewHolder> {
    private Activity activity;
    private List<? extends Media> media;

    public MediaRecyclerViewAdapter(Activity activity, List<? extends Media> media) {
        this.activity = activity;
        this.media = media;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Media media = this.media.get(position);

        holder.title.setText(media.getTitle());

        //TODO Add proper date parsing
        holder.releaseDate.setText(media.getDate().substring(0, 4));

        holder.rating.setText(String.format(Locale.getDefault(), "%.1f", media.getRating()));

        holder.poster.setImageURI(Logic.getInstance().getPluginFactory().getImagePathResolverPlugin().getMediaPosterUrl(media));

        holder.view.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent intent = new Intent(activity, MediaDetailActivity.class);
                                               MediaDetailActivity.setExtras(intent, media);

                                               //TODO Fix shared transition. It has issues with Fresco (maybe replace it to something else).
                                               ActivityOptionsCompat options = ActivityOptionsCompat.
                                                       makeSceneTransitionAnimation(activity, holder.poster, "poster");
                                               activity.startActivity(intent, options.toBundle());
                                               //activity.startActivity(intent);
                                           }
                                       }
        );

        if (position > 1) {
            holder.topBackground.setVisibility(View.GONE);
        } else {
            holder.topBackground.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return media.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        final View topBackground;
        final SimpleDraweeView poster;
        final TextView title;
        final TextView rating;
        final TextView releaseDate;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            title = (TextView) view.findViewById(R.id.title);
            topBackground = view.findViewById(R.id.top_background);
            releaseDate = (TextView) view.findViewById(R.id.releaseDate);
            rating = (TextView) view.findViewById(R.id.rating);
            poster = (SimpleDraweeView) view.findViewById(R.id.poster);
        }
    }
}
