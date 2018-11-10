package com.example.meditrackr.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.meditrackr.R;
import com.example.meditrackr.models.DataManager;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.ProblemList;
import com.example.meditrackr.ui.PatientRecordsFragment;


public class PatientProblemAdapter extends RecyclerView.Adapter<PatientProblemAdapter.ViewHolder>{
    private FragmentActivity activity;
    private Patient patient = DataManager.getPatient();
    private ProblemList problems = patient.getProblems();

    // constructor
    public PatientProblemAdapter(FragmentActivity activity) {
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



    // set the data into each viewHolder (ie. place what each emotion has into the view)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(problems.getProblem(position).getTitle());
        holder.date.setText(problems.getProblem(position).getDate());
        holder.description.setText(problems.getProblem(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return problems.getSize();
    }


    // place each problem into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private PatientProblemAdapter adapter;
        public TextView title;
        public TextView date;
        public TextView description;

        public ViewHolder(View itemView, final PatientProblemAdapter adapter){
            super(itemView);
            title = itemView.findViewById(R.id.problem_title);
            date = itemView.findViewById(R.id.problem_date);
            description = itemView.findViewById(R.id.problem_description);
            itemView.setOnClickListener(this);
            this.adapter = adapter;

        }

        // set onClick listener for each problem, so they can be edited
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction =  manager.beginTransaction();
            PatientRecordsFragment fragment = PatientRecordsFragment.newInstance(adapter.problems.getProblem(position).getRecords(), position);
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }
}

