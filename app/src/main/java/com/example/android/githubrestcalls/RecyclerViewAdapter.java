package com.example.android.githubrestcalls;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private static final String TAG = "";
    List<Projects> projectList = new ArrayList<>();

    public RecyclerViewAdapter(List<Projects> project) {
        this.projectList = project;
    }






    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Projects p = projectList.get(position);
        holder.name.setText("Name: "+p.getName());
        holder.commits.setText("Commits: " +p.getCommits());
        holder.updated.setText("Updated: " +p.getUpdated());
        holder.id.setText("ID: " +p.getID());
        Log.d(TAG, "onBindViewHolder: " + p.getName() + p.getCommits() + p.getID() + p.getUpdated());


    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG ="testing" ;

        TextView name;
        TextView id;
        TextView updated;
        TextView commits;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvRName);
            id = (TextView) itemView.findViewById(R.id.tvRID);
            updated = (TextView) itemView.findViewById(R.id.tvRUpdate);
            commits = (TextView) itemView.findViewById(R.id.tvRCommits);

        }
    }
}
