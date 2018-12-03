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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
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
import com.example.meditrackr.controllers.model.ProblemController;
import com.example.meditrackr.controllers.model.RecordController;
import com.example.meditrackr.models.record.RecordList;
import com.example.meditrackr.ui.patient.EditProblemFragment;
import com.example.meditrackr.ui.patient.EditRecordFragment;
import com.example.meditrackr.ui.patient.RecordFragment;

import net.steamcrafted.materialiconlib.MaterialIconView;

/**
 * this class will display all of the records associated with a problem in a recycler view.
 *
 * there is also a onclick listener which when clicked will take the patient to a page with
 * more detailed information about that record.
 *
 * it uses onCreateViewHolder to create the recycler view and uses onBindViewHolder to put the records
 * into the recycler view.
 *
 * this class can use getItemCount to display the number of items (records) in the recycler view
 *
 * this class uses viewHolder to put information to each problem into its own view so we wont display
 * information from one problem as another. this function servers mainly as an organization purpose
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 10, 2018
 */

// Adapter class shows a patient's record list and info for patients in a recycler view
public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder>{
    // Class objects
    private FragmentActivity activity;
    private RecordList records;
    private Context context;

    /**
     * creating variables activity and records for the other functions to use
     *
     * @author  Orest Cokan
     * @version 1.0 Nov 10, 2018
     * @param activity this is the activity to pass the data
     * @param records this is the records the problem has
     */

    // Constructor
    public RecordsAdapter(FragmentActivity activity, RecordList records, Context context) {
        this.activity = activity;
        this.records = records;
        this.context = context;
    }

    // Display the view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Creates view objects based on layouts in XML
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View problemView = inflater.inflate(R.layout.record_entry, parent, false);
        return new ViewHolder(problemView, this);
    }

    // Set the data into each viewHolder (ie. place what each record has into the view)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Display each record's date and description in each viewHolder
        holder.date.setText(records.getRecord(position).getDate());
        holder.description.setText(records.getRecord(position).getDescription());
        holder.title.setText(records.getRecord(position).getTitle());
    }

    // Return the number of records currently in RecyclerView
    @Override
    public int getItemCount() {
        return records.getSize();
    }


    /**
     * this class uses viewHolder to put information to each problem into its own view so we wont display
     * information from one problem as another. this function servers mainly as an organization purpose
     *
     * @author  Orest Cokan
     * @version 1.0 Nov 10, 2018
     */

    // Class places each record into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Class objects
        private RecordsAdapter adapter;
        public TextView date;
        public TextView description;
        private TextView title;
        private MaterialIconView deleteRecord;
        private MaterialIconView editRecord;

        // Constructor and gets the corresponding data for each view
        private ViewHolder(View itemView, final RecordsAdapter adapter){
            super(itemView);
            date = itemView.findViewById(R.id.record_date);
            title = itemView.findViewById(R.id.record_title);
            description = itemView.findViewById(R.id.record_description);
            deleteRecord = itemView.findViewById(R.id.record_delete_button);
            editRecord= itemView.findViewById(R.id.record_edit_button);
            itemView.setOnClickListener(this);
            this.adapter = adapter;


            // Onclick listener for delete problem
            deleteRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Creates an alert dialog box to confirm and
                    // To ensure that the user will not accidentally delete the problem
                    final int position = getAdapterPosition(); // Returns position that was clicked
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(adapter.activity,
                            R.style.AlertDialogStyle);
                    builder1.setMessage("Are you sure you want to delete the Record?");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Only delete the problem if the answer was yes
                                    RecordController.deleteRecord(adapter.context, position, adapter.records);

                                    //adapter.problems.removeProblem(position);
                                    adapter.notifyItemRemoved(position);
                                    adapter.notifyItemRangeChanged(position,
                                            adapter.records.getSize());

                                    // Save changes to memory and ES
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

                    // Create and show alert dialog box
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });


            // Onclick listener for edit a problem
            editRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition(); // Returns position that was clicked
                    // Prepare for fragment transaction
                    FragmentManager manager = adapter.activity.getSupportFragmentManager();
                    FragmentTransaction transaction =  manager.beginTransaction();
                    // Transition to EditProblemFragment page
                    EditRecordFragment fragment = EditRecordFragment.newInstance(position, adapter.records);
                    // Allow user to bring back previous fragment when back button is pressed
                    transaction.addToBackStack(null);
                    transaction.replace(R.id.content, fragment);
                    transaction.commit(); // Make permanent all changes performed in the transaction
                }
            });

        }



        // Set onClick listener for each record so you can view it in greater detail
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // Returns position that was clicked
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction =  manager.beginTransaction();
            Log.d("RecordsFragments", "index is: " + position);
            RecordFragment fragment = RecordFragment.newInstance(adapter.records.getRecord(position));
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }
}

