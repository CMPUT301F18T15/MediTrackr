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
package com.example.meditrackr.adapters.patient;

//imports
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.ProblemList;
import com.example.meditrackr.models.record.ImageSave;
import com.example.meditrackr.ui.FullScreenViewActivity;
import com.example.meditrackr.ui.patient.EditProblemFragment;
import com.example.meditrackr.ui.patient.RecordsFragment;
import com.example.meditrackr.utils.ConvertImage;

import net.steamcrafted.materialiconlib.MaterialIconView;

/**
 * This class displays all of the problems of a user (patient) in a recycler view.
 *
 * There is also an onclick listener which when a problem is clicked will take the patient to
 * a page with more detailed information about that problem and allow them to edit that problem.
 *
 * It uses onCreateView to create the recycler view and uses onBindViewHolder to put each problem
 * into the recycler view.
 *
 * This class can use getItemCount to display the number of items (problems) in the recycler view.
 *
 * This class uses viewHolder to put information about each problem into its own view so we won't
 * display information from one problem as another. This function mainly serves an organizational
 * purpose but can also be used to delete or edit a problem using the corresponding buttons.
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 10, 2018
 * @see
 *
 */
public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ViewHolder>{
    private FragmentActivity activity;
    private Context context;
    private Patient patient = LazyLoadingManager.getPatient();
    private ProblemList problems = patient.getProblems();

    // constructor
    public ProblemAdapter(FragmentActivity activity, Context context) {

        this.activity = activity;
        this.context = context;
    }


    // display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //creates view objects based on layouts in XML
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
        holder.totalRecords.setText("Number of records: " +
                problems.getProblem(position).getRecords().getSize());
        if(problems.getProblem(position).getImageAll().getSize() == 0){
            holder.problemImage.setImageBitmap(null);
            Log.d("ImageTest", "New profile this should be shown!");
        }else {
            holder.problemImage.setImageBitmap(ConvertImage.base64Decode(
                    problems.getProblem(position).getImageAll().getImage(0)));
        }
    }


    // get the number of problems in RecyclerView
    @Override
    public int getItemCount() {
        return problems.getSize();
    }


    // place each problem into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ProblemAdapter adapter;
        public ImageView problemImage;
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
            problemImage = itemView.findViewById(R.id.problem_image);
            itemView.setOnClickListener(this);
            this.adapter = adapter;



            // onclick listener for delete problem
            deleteProblem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // print a confirmation message
                    // to ensure that the user will not accidentally delete the problem
                    final int position = getAdapterPosition();
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(adapter.activity,
                            R.style.AlertDialogStyle);
                    builder1.setMessage("Are you sure you want to delete the problem?");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // only delete the problem if the answer was yes
                                    adapter.problems.removeProblem(position);
                                    adapter.notifyItemRemoved(position);
                                    adapter.notifyItemRangeChanged(position,
                                            adapter.problems.getSize());
                                    Log.d("DeleteProblem", "Position: " + position);
                                    SaveLoadController.saveProfile(adapter.context, LazyLoadingManager.getPatient());
                                    ElasticSearchController.updateUser(LazyLoadingManager.getPatient());
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
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
                    transaction.addToBackStack(null);
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();

                }
            });


            // onclick listener for problem image
            problemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageSave images = LazyLoadingManager.getImages();
                    if(images.getSize() == 0){
                        problemImage.setClickable(false);
                        problemImage.setVisibility(View.INVISIBLE);
                        Log.d("ImageTest", "we should be getting here");
                    }
                    else {
                        Intent intent = new Intent(adapter.activity, FullScreenViewActivity.class);
                        adapter.activity.startActivity(intent);
                    }
                }
            });

        }


        // set onClick listener for each problem to be viewed
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            LazyLoadingManager.setImages(adapter.problems.getProblem(position).getImageAll());
            FragmentTransaction transaction =  manager.beginTransaction();
            LazyLoadingManager.setProblemIndex(position);
            RecordsFragment fragment = RecordsFragment.newInstance(position);
            //allows user to bring back previous fragment when back button is pressed
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }
}

