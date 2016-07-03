package hu.gabornovak.movieapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Locale;

import hu.gabornovak.movieapp.R;
import hu.gabornovak.movieapp.activity.MediaDetailActivity;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.Media;
import hu.gabornovak.movieapp.logic.entity.Person;

public class KnownForRecyclerViewAdapter extends RecyclerView.Adapter<KnownForRecyclerViewAdapter.ViewHolder> {
    private Activity activity;
    private Person person;

    public KnownForRecyclerViewAdapter(Activity activity, Person person) {
        this.activity = activity;
        this.person = person;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_known_for, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Media media = this.person.getKnownFor().get(position);

        holder.name.setText(media.getTitle());
        holder.rating.setText(String.format(Locale.getDefault(), "%.1f / 10", media.getRating()));
        holder.poster.setImageURI(Logic.getInstance().getPluginFactory().getImagePathResolverPlugin().getMediaPosterUrl(media));

        holder.view.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent intent = new Intent(activity, MediaDetailActivity.class);
                                               MediaDetailActivity.setExtras(intent, media);
                                               activity.startActivity(intent);
                                           }
                                       }
        );
    }

    @Override
    public int getItemCount() {
        return person.getKnownFor().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        final SimpleDraweeView poster;
        final TextView name;
        final TextView rating;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            name = (TextView) view.findViewById(R.id.title);
            rating = (TextView) view.findViewById(R.id.rating);
            poster = (SimpleDraweeView) view.findViewById(R.id.poster);
        }
    }
}
