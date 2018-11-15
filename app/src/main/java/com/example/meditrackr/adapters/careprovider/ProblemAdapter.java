/*
 *           Apache 2.0 License Notice
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
import com.example.meditrackr.models.ProblemList;
import com.example.meditrackr.ui.careprovider.ProblemsFragment;

/**
 * Team Name CMPUT301F18T15
 * Author Skyrt
 * Created on Nov 10, 2018
 * Version 1.0
 * careprovider\ProblemAdapter.java creates the RecyclerView Adapter for the Problem list
 * RecyclerView for careprovider accounts
 */

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ViewHolder> {
    private FragmentActivity activity;
    private ProblemList problems;

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

        //gets the corresponding data for each view
        public ViewHolder(View itemView, final ProblemAdapter adapter){
            super(itemView);
            title = itemView.findViewById(R.id.problem_title);
            date = itemView.findViewById(R.id.problem_date);
            description = itemView.findViewById(R.id.problem_description);
            itemView.setOnClickListener(this);
            this.adapter = adapter;
        }

        // set onClick listener for each problem, so the problem can be viewed
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FragmentManager manager = adapter.activity.getSupportFragmentManager(); //access Fragment class features
            FragmentTransaction transaction =  manager.beginTransaction(); //allow editing on manager fragment
            ProblemsFragment fragment = ProblemsFragment.newInstance(position); //instantiate new Fragment
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit(); //make permanent all changes performed in the transaction
        }
    }
}