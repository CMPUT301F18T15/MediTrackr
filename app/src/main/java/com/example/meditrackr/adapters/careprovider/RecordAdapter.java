package com.example.meditrackr.adapters.careprovider;

/**
 * Created by Skryt on Nov 15, 2018
 */

package com.example.meditrackr.adapters.careprovider;

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
import com.example.meditrackr.models.ProblemList;
import com.example.meditrackr.models.record.RecordList;
import com.example.meditrackr.ui.careprovider.ProblemsFragment;
import com.example.meditrackr.ui.patient.RecordFragment;


/**
 * Created by Skryt on Nov 10, 2018
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    private FragmentActivity activity;
    private ProblemList problems;

    // constructor
    public RecordAdapter(FragmentActivity activity, RecordList records) {
        this.activity = activity;
        this.problems = problems;
    }

    // display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View recordView = inflater.inflate(R.layout.record_entry, parent, false);
        return new ViewHolder(recordView, this);
    }

    // set the data into each viewHolder (ie. place place the patients info into the view)
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
        private RecordAdapter adapter;
        public TextView date;
        public TextView description;

        public ViewHolder(View itemView, final RecordAdapter adapter){
            super(itemView);
            date = itemView.findViewById(R.id.problem_date);
            description = itemView.findViewById(R.id.problem_description);
            itemView.setOnClickListener(this);
            this.adapter = adapter;

        }

        // set onClick listener for each problem, so the problem can be viewed
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction =  manager.beginTransaction();
            RecordFragment fragment = RecordFragment.newInstance(position);
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }
}