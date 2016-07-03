package hu.gabornovak.movieapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hu.gabornovak.movieapp.R;
import hu.gabornovak.movieapp.activity.MovieDetailActivity;
import hu.gabornovak.movieapp.logic.Logic;
import hu.gabornovak.movieapp.logic.entity.Person;

public class PeopleRecyclerViewAdapter extends RecyclerView.Adapter<PeopleRecyclerViewAdapter.ViewHolder> {
    private Activity activity;
    private List<Person> people;

    public PeopleRecyclerViewAdapter(Activity activity, List<Person> people) {
        this.activity = activity;
        this.people = people;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Person person = this.people.get(position);

        holder.name.setText(person.getName());
        holder.profile.setImageURI(Logic.getInstance().getPluginFactory().getImagePathResolverPlugin().getProfileUrl(person));
        holder.knownFor.setText(createKnownForText(person));

        holder.view.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent intent = new Intent(activity, MovieDetailActivity.class);
                                               //MovieDetailActivity.setExtras(intent, person);

                                               //TODO Fix shared transition. It has issues with Fresco (maybe replace it to something else).
/*                                               ActivityOptionsCompat options = ActivityOptionsCompat.
                                                       makeSceneTransitionAnimation(activity, holder.profile, "profile");
                                               activity.startActivity(intent, options.toBundle());*/
                                               activity.startActivity(intent);
                                           }
                                       }
        );
    }

    private String createKnownForText(Person person) {
        StringBuilder knowFor = new StringBuilder(activity.getString(R.string.label_known_for));
        knowFor.append(" ");
        for (int i = 0; i < person.getKnownFor().size(); i++) {
            knowFor.append(person.getKnownFor().get(i).getTitle());
            if (i != person.getKnownFor().size() - 1) {
                knowFor.append(", ");
            }
        }
        return knowFor.toString();
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        final SimpleDraweeView profile;
        final TextView name;
        final TextView knownFor;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            name = (TextView) view.findViewById(R.id.name);
            knownFor = (TextView) view.findViewById(R.id.knownfor);
            profile = (SimpleDraweeView) view.findViewById(R.id.profile);
        }
    }
}
