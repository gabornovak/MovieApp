package hu.gabornovak.movieapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hu.gabornovak.movieapp.R;
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

        if (position > 1) {
            holder.topBackground.setVisibility(View.GONE);
        } else {
            holder.topBackground.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        final SimpleDraweeView profile;
        final TextView name;
        final View topBackground;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            topBackground = view.findViewById(R.id.top_background);
            name = (TextView) view.findViewById(R.id.name);
            profile = (SimpleDraweeView) view.findViewById(R.id.profile);
        }
    }
}
