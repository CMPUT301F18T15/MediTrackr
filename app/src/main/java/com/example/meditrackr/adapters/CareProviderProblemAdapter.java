package com.example.meditrackr.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.meditrackr.R;
import com.example.meditrackr.models.DataManager;
import com.example.meditrackr.models.PatientList;
import com.example.meditrackr.ui.CareProviderProblemFragment;

/**
 * Created by Skryt on Nov 10, 2018
 */

public class CareProviderProblemAdapter extends RecyclerView.Adapter<CareProviderProblemAdapter.ViewHolder>{
    private FragmentActivity activity;
    private PatientList patients = DataManager.getCareProviderPatients();

    // constructor
    public CareProviderProblemAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    // display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View problemView = inflater.inflate(R.layout.problem_entry, parent, false);
        return new ViewHolder(problemView, this);
    }

    // set the data into each viewHolder (ie. place place the patients info into the view)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(patients.getPatient(position).getProblems().getProblem(position).getTitle());
        holder.date.setText(patients.getPatient(position).getProblems().getProblem(position).getDate());
        holder.description.setText(patients.getPatient(position).getProblems().getProblem(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return patients.getSize();
    }


    // place each problem into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CareProviderProblemAdapter adapter;
        public TextView title;
        public TextView date;
        public TextView description;

        public ViewHolder(View itemView, final CareProviderProblemAdapter adapter){
            super(itemView);
            title = itemView.findViewById(R.id.problem_title);
            date = itemView.findViewById(R.id.problem_date);
            description = itemView.findViewById(R.id.problem_description);
            itemView.setOnClickListener(this);
            this.adapter = adapter;

        }

        // set onClick listener for each patient, so they can be viewed
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction =  manager.beginTransaction();
            CareProviderProblemFragment fragment = CareProviderProblemFragment.newInstance(position, adapter.patients.getPatient(position).getProblems());
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }
}