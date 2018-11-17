/*
 *Apache 2.0 License Notice
 *
 *Copyright 2018 CMPUT301F18T15
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 */
package com.example.meditrackr.adapters.careprovider;

//imports
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
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.models.ProblemList;
import com.example.meditrackr.models.record.RecordList;
import com.example.meditrackr.ui.careprovider.RecordsFragment;

import net.steamcrafted.materialiconlib.MaterialIconView;

/**
 * this class will display all of the problems  in a recycler view for the patient that was clicked
 * on from the previous page (PatientAdapter).
 *
 * there is also a onclick listener which when clicked will take the care provider to a page with
 * more detailed information about that problem.
 *
 * it uses onCreateView to create the recycler view and uses onBindViewHolder to put the problem
 * into the recycler view.
 *
 * this class can use getItemCount to display the number of items (problems) in the recycler view
 *
 * this class uses viewHolder to put information to each problem into its own view so we wont display
 * information from one problem as another. this function servers mainly as an organization purpose
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 10, 2018
 * @see PatientAdapter
 *
 */

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ViewHolder> {
    private FragmentActivity activity;
    private static ProblemList problems;

    // constructor
    public ProblemAdapter(FragmentActivity activity, ProblemList problems) {
        this.activity = activity;
        this.problems = problems;
    }


    // display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View problemView = inflater.inflate(R.layout.problem_entry, parent, false);
        return new ViewHolder(problemView, this);
    }


    // set the data into each viewHolder (ie. place the problem info into the view)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(problems.getProblem(position).getTitle());
        holder.date.setText(problems.getProblem(position).getDate());
        holder.description.setText(problems.getProblem(position).getDescription());
        holder.totalRecords.setText("Number of records: "+problems.getProblem(position).getRecords().getSize());
    }

    // get the number of problems in RecyclerView
    @Override
    public int getItemCount() {
        return problems.getSize();
    }


    // place each problem into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ProblemAdapter adapter;
        public TextView title;
        public TextView date;
        public TextView description;
        public TextView totalRecords;
        public MaterialIconView deleteProblem;
        public MaterialIconView editProblem;


        //gets the corresponding data for each view
        public ViewHolder(View itemView, final ProblemAdapter adapter){
            super(itemView);
            title = itemView.findViewById(R.id.problem_title);
            date = itemView.findViewById(R.id.problem_date);
            description = itemView.findViewById(R.id.problem_description);
            totalRecords = itemView.findViewById(R.id.number_records_title);
            deleteProblem = itemView.findViewById(R.id.problem_delete_button);
            editProblem = itemView.findViewById(R.id.problem_edit_button);
            itemView.setOnClickListener(this);
            // hide stuff from doctor
            deleteProblem.setVisibility(View.INVISIBLE);
            deleteProblem.setClickable(false);
            editProblem.setVisibility(View.INVISIBLE);
            editProblem.setClickable(false);
            this.adapter = adapter;
        }

        // set onClick listener for each problem, so the problem can be viewed
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            RecordList records = problems.getProblem(position).getRecords();
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction =  manager.beginTransaction();
            ProfileManager.setProblemIndex(position);
            RecordsFragment fragment = RecordsFragment.newInstance(records, problems.getProblem(position).getComments());
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit(); //make permanent all changes performed in the transaction
        }
    }
}