package com.example.meditrackr.adapters.patient;

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
import com.example.meditrackr.controllers.ThreadSaveController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.ProblemList;
import com.example.meditrackr.models.record.PhotoList;
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
 *
 */

// Class shows a patient's problem list and info for patients in a recycler view
public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ViewHolder>{
    // Class objects
    private FragmentActivity activity;
    private Context context;
    private Patient patient = LazyLoadingManager.getPatient();
    private ProblemList problems = patient.getProblems();

    /**
     * creating variables activity for the other functions to use
     *
     * @author  Orest Cokan
     * @param activity this is the activity to pass the data
     */

    // Constructor
    public ProblemAdapter(FragmentActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }


    // Display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Creates view objects based on layouts in XML
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


    // Class places each problem into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Class objects
        private ProblemAdapter adapter;
        public ImageView problemImage;
        public TextView title;
        public TextView date;
        public TextView description;
        public TextView totalRecords;
        public MaterialIconView deleteProblem;
        public MaterialIconView editProblem;

        /**
         * This function puts information about each problem into its own view so we won't
         * display information from one problem as another. This function mainly serves an organizational
         * purpose but can also be used to delete or edit a problem using the corresponding buttons.
         *
         * @param itemView
         * @param adapter
         */


        // Constructor and gets the corresponding data for each view
        public ViewHolder(View itemView, final ProblemAdapter adapter){
            super(itemView);
            title = itemView.findViewById(R.id.problem_title);
            date = itemView.findViewById(R.id.problem_date);
            description = itemView.findViewById(R.id.problem_description);
            totalRecords = itemView.findViewById(R.id.number_records_title);
            deleteProblem = itemView.findViewById(R.id.problem_delete_button);
            editProblem = itemView.findViewById(R.id.problem_edit_button);
            problemImage = itemView.findViewById(R.id.problem_image);
            itemView.setOnClickListener(this); // Sets onClickListener on view
            this.adapter = adapter;


            // Onclick listener for delete problem
            deleteProblem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Creates an alert dialog box to confirm and
                    // To ensure that the user will not accidentally delete the problem
                    final int position = getAdapterPosition(); // Returns position that was clicked
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(adapter.activity,
                            R.style.AlertDialogStyle);
                    builder1.setMessage("Are you sure you want to delete the problem?");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Only delete the problem if the answer was yes
                                    adapter.problems.removeProblem(position);
                                    adapter.notifyItemRemoved(position);
                                    adapter.notifyItemRangeChanged(position,
                                            adapter.problems.getSize());
                                    Log.d("DeleteProblem", "Position: " + position);
                                    // Save changes to memory and ES
                                    ThreadSaveController.save(adapter.context, LazyLoadingManager.getPatient());
                                    //SaveLoadController.saveProfile(adapter.context, LazyLoadingManager.getPatient());
                                    //ElasticSearchController.updateUser(LazyLoadingManager.getPatient());
                                    dialog.cancel(); // Close alert dialog box
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Do not delete the problem and close
                                    // Alert Dialog box
                                    dialog.cancel();
                                }
                            });

                    // Create and show alert dialog box
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });



            // Onclick listener for edit a problem
            editProblem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition(); // Returns position that was clicked
                    // Prepare for fragment transaction
                    FragmentManager manager = adapter.activity.getSupportFragmentManager();
                    FragmentTransaction transaction =  manager.beginTransaction();
                    // Transition to EditProblemFragment page
                    EditProblemFragment fragment = EditProblemFragment.newInstance(position);
                    // Allow user to bring back previous fragment when back button is pressed
                    transaction.addToBackStack(null);
                    transaction.replace(R.id.content, fragment);
                    transaction.commit(); // Make permanent all changes performed in the transaction
                }
            });


            // onclick listener for problem image
            problemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    PhotoList images = adapter.problems.getProblem(position).getImageAll();
                    if(images.getSize() == 0){
                        problemImage.setClickable(false);
                        problemImage.setVisibility(View.INVISIBLE);
                        Log.d("ImageTest", "we should be getting here");
                    }
                    else {
                        LazyLoadingManager.setImages(images);
                        Intent intent = new Intent(adapter.activity, FullScreenViewActivity.class);
                        adapter.activity.startActivity(intent);
                    }
                }
            });

        }


        // Set onClick listener for each problem to be viewed
        @Override
        public void onClick(View v) {
            // Return the position of the click in the recycler view
            int position = getAdapterPosition();
            // Prepare for fragment transaction
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            // Load all the problems images
            LazyLoadingManager.setImages(adapter.problems.getProblem(position).getImageAll());
            FragmentTransaction transaction =  manager.beginTransaction();
            // Load the problem index on the position that was cliclked
            LazyLoadingManager.setProblemIndex(position);
            // Transitions to RecordsFragment page
            RecordsFragment fragment = RecordsFragment.newInstance(position);
            // Allow user to bring back previous fragment when back button is pressed
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            // Make permanent all changes made in transaction
            transaction.commit();
        }
    }
}

