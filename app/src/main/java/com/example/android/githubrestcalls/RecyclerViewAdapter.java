package com.example.android.githubrestcalls;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 7/10/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    List<Projects> project = new ArrayList<>();

    public RecyclerViewAdapter(List<Projects> project) {
        this.project = project;
    }

    TextView name;
    TextView id;
    TextView updated;
    TextView commits;




    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Projects p = project.get(position);
        name.setText(p.getName());
        commits.setText(p.getCommits());
        updated.setText(p.getUpdated());
        id.setText(p.getID());


    }

    @Override
    public int getItemCount() {
        return project.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG ="testing" ;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvRName);
            id = (TextView) itemView.findViewById(R.id.tvRID);
            updated = (TextView) itemView.findViewById(R.id.tvRUpdate);
            commits = (TextView) itemView.findViewById(R.id.tvRCommits);

        }
    }
}
