/*
 *                     Apache 2.0 License Notice
 *
 *Licensed to the Apache Software Foundation (ASF) under one
 *or more contributor license agreements.  See the NOTICE file
 *distributed with this work for additional information
 *regarding copyright ownership.  The ASF licenses this file
 *to you under the Apache License, Version 2.0 (the
 *"License"); you may not use this file except in compliance
 *with the License.  You may obtain a copy of the License at

 *  http://www.apache.org/licenses/LICENSE-2.0

 *Unless required by applicable law or agreed to in writing,
 *software distributed under the License is distributed on an
 *"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *KIND, either express or implied.  See the License for the
 *specific language governing permissions and limitations
 *under the License.
 *
 */
package com.example.meditrackr.adapters.patient;

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
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.ProblemList;
import com.example.meditrackr.ui.patient.EditProblemFragment;
import com.example.meditrackr.ui.patient.RecordsFragment;

import net.steamcrafted.materialiconlib.MaterialIconView;

/**
 * Classname CMPUT301F18T15
 * Author Skyrt
 * Created on Nov 10, 2018
 * Version 1.0
 * patient\ProblemAdapter.java creates the RecyclerView Adapter for the Problem list
 * RecyclerView for patient accounts
 */

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ViewHolder>{
    private FragmentActivity activity;
    private Patient patient = ProfileManager.getPatient();
    private ProblemList problems = patient.getProblems();

    // constructor
    public ProblemAdapter(FragmentActivity activity) {
        this.activity = activity;
    }


    // display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE); //creates view objects based on layouts in XML
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
            this.adapter = adapter;
<<<<<<< HEAD


            // onclick listener for delete problem
            deleteProblem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    adapter.problems.removeProblem(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position,adapter.problems.getSize());
                    Log.d("DeleteProblem", "Position: " + position);
                }
            });

            // onclick listener for edit a problem
            editProblem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    FragmentManager manager = adapter.activity.getSupportFragmentManager();
                    FragmentTransaction transaction =  manager.beginTransaction();
                    EditProblemFragment fragment = EditProblemFragment.newInstance(position);
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();

                }
            });
=======
>>>>>>> patient/ProblemAdapter documentation-commit
        }



        // set onClick listener for each problem, so they can be edited
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction =  manager.beginTransaction();
            Log.d("ProblemAdapter", "we are on index: " + position);
            RecordsFragment fragment = RecordsFragment.newInstance(position);
            transaction.addToBackStack(null); //allows user to bring back previous fragment when back button is pressed
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }
}

