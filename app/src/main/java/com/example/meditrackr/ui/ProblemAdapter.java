package com.example.meditrackr.ui;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meditrackr.R;


public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ViewHolder>{
    private FragmentActivity activity;

    // constructor
    public ProblemAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    // display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View problemView = inflater.inflate(R.layout.problems_entry, parent, false);
        return new ViewHolder(problemView, this);
    }



    // set the data into each viewHolder (ie. place what each emotion has into the view)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    // place each emotion into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ProblemAdapter adapter;

        public ViewHolder(View itemView, final ProblemAdapter adapter){
            super(itemView);


        }

        // set onClick listener for each emotion, so they can be edited
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction =  manager.beginTransaction();
            RecordsFragment fragment = RecordsFragment.newInstance();
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }
}

