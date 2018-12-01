/*
 *   Apache 2.0 License Notice
 *
 *   Copyright 2018 CMPUT301F18T15
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
import android.content.Intent;
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
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.ProblemList;
import com.example.meditrackr.models.record.PhotoList;
import com.example.meditrackr.models.record.RecordList;
import com.example.meditrackr.ui.FullScreenViewActivity;
import com.example.meditrackr.ui.careprovider.RecordsFragment;
import com.example.meditrackr.utils.ConvertImage;

import net.steamcrafted.materialiconlib.MaterialIconView;

/**
 * This class displays all of the problems of a patient selected from a previous page (using the
 * PatientAdapter) in a recycler view.
 *
 * There is also an onclick listener which when a problem is clicked will take the Care Provider to
 * a page with more detailed information about that problem.
 *
 * It uses onCreateView to create the recycler view and uses onBindViewHolder to put each problem
 * into the recycler view.
 *
 * This class can use getItemCount to display the number of items (problems) in the recycler view.
 *
 * This class uses viewHolder to put information about each problem into its own view so we won't
 * display information from one problem as another. This function mainly serves an organizational
 * purpose.
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 10, 2018
 * @see PatientAdapter
 */
public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ViewHolder> {
    // Class objects
    private FragmentActivity activity;
    private static ProblemList problems;

    /**
     * Constructor: Initialize the ProblemAdapter class by setting the activity
     * to the appropriate fragment and setting the list of problems.
     *
     * @author  Orest Cokan
     * @param activity the fragment we are currently on (activity to get data from)
     * @param problemList a list of the problems of a given patient
     */
    public ProblemAdapter(FragmentActivity activity, ProblemList problemList) {
        this.activity = activity;
        problems = problemList;
    }


    // Display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Instantiates layout XML into its proper view object
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View problemView = inflater.inflate(R.layout.problem_entry, parent, false);
        return new ViewHolder(problemView, this);
    }


    // Set the data into each viewHolder (ie. place the problem info into the view)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Display each problem's title, date, description, number of records, and image in each viewHolder
        holder.title.setText(problems.getProblem(position).getTitle());
        holder.date.setText(problems.getProblem(position).getDate());
        holder.description.setText(problems.getProblem(position).getDescription());
        holder.totalRecords.setText("Number of records: " +
                problems.getProblem(position).getRecords().getSize());
        if(problems.getProblem(position).getImageAll().getSize() == 0){
            holder.problemImage.setImageBitmap(null); // If the problem does not have any images set image to null
            Log.d("ImageTest", "New profile this should be shown!");
        }else { // Else show image pertaining to the problem
            holder.problemImage.setImageBitmap(ConvertImage.convertByteToBitmap(
                    problems.getProblem(position).getImageAll().getImage(0)));
        }
    }


    // Return the number of problems currently in RecyclerView
    @Override
    public int getItemCount() {
        return problems.getSize();
    }


    /**
     * This class puts information about each problem into its own view so we won't
     * display information from one problem as another. This function mainly serves an organizational
     * purpose.
     *
     * @author  Orest Cokan
     * @version 1.0 Nov 10, 2018
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Class objects
        private ProblemAdapter adapter;
        private ImageView problemImage;
        public TextView title;
        public TextView date;
        public TextView description;
        private TextView totalRecords;
        private MaterialIconView deleteProblem;
        private MaterialIconView editProblem;


        // Constructor and gets the corresponding data for each view
        private ViewHolder(View itemView, final ProblemAdapter adapter){
            super(itemView);
            title = itemView.findViewById(R.id.problem_title);
            date = itemView.findViewById(R.id.problem_date);
            description = itemView.findViewById(R.id.problem_description);
            totalRecords = itemView.findViewById(R.id.number_records_title);
            deleteProblem = itemView.findViewById(R.id.problem_delete_button);
            editProblem = itemView.findViewById(R.id.problem_edit_button);
            problemImage = itemView.findViewById(R.id.problem_image);
            itemView.setOnClickListener(this);

            // Hides certain functionalities from care provider
            // Care provider cannot delete or edit a patient's problem
            deleteProblem.setVisibility(View.INVISIBLE);
            deleteProblem.setClickable(false);
            editProblem.setVisibility(View.INVISIBLE);
            editProblem.setClickable(false);
            this.adapter = adapter;


            // Onclick listener for problem image
            problemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhotoList images = problems.getProblem(getAdapterPosition()).getImageAll();
                    LazyLoadingManager.setImages(images);

                    if(images.getSize() == 0){ // Make not clickable if there is no image for the problem
                        problemImage.setClickable(false);
                        problemImage.setVisibility(View.INVISIBLE);
                    }
                    else { // Make clickable and switch to FullScreenViewActivity on click
                        Intent intent = new Intent(adapter.activity, FullScreenViewActivity.class);
                        adapter.activity.startActivity(intent);
                    }
                }
            });

        }


        // Set onClick listener for each problem, so the problem can be viewed
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // Return position of click in the recycler view
            RecordList records = problems.getProblem(position).getRecords();
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction =  manager.beginTransaction();
            LazyLoadingManager.setProblemIndex(position); // Load the problem clicked
            RecordsFragment fragment = RecordsFragment.newInstance(records,
                    problems.getProblem(position).getComments()); // Transition to RecordsFragment
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }
}
