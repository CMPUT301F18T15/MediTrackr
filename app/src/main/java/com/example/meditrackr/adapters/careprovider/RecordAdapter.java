/*--------------------------------------------------------------------------
 * FILE: RecordAdapter.java
 *
 * PURPOSE: Tracks the record list for a specific problem of a specific patient
 *          of a given Care Provider and notifies the view when the list
 *          changes.
 *
 *     Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 --------------------------------------------------------------------------*/
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
import com.example.meditrackr.models.record.RecordList;
import com.example.meditrackr.ui.patient.RecordFragment;

/**
 * This class displays all of the records of a problem selected from a previous page (using the
 * problem adapter) in a recycler view.
 *
 * There is also an onclick listener which when a record is clicked will take the user to a page
 * with more detailed information about that record.
 *
 * It uses onCreateView to create the recycler view and uses onBindViewHolder to put each record
 * into the recycler view.
 *
 * This class can use getItemCount to display the number of items (records) in the recycler view.
 *
 * This class uses viewHolder to put information about each record into its own view so we won't
 * display information from one record as another. This function mainly serves an organizational
 * purpose.
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 10, 2018
 * @see ProblemAdapter
 */
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    // Class objects
    private FragmentActivity activity;
    private static RecordList records;

    /**
     * creating variables activity and records for the other functions to use
     *
     * @author  Orest Cokan
     * @param activity this is the activity to pass the data
     * @param recordList this is the records the problem has
     */
    public RecordAdapter(FragmentActivity activity, RecordList recordList) {
        this.activity = activity;
        records = recordList;
    }

    // Display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Instantiates layout XML into its proper view object
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View recordView = inflater.inflate(R.layout.record_entry, parent, false);
        return new ViewHolder(recordView, this);
    }

    // Set the data into each viewHolder (ie. place place the record info into the view)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Display each record's date and description in each viewHolder
        holder.date.setText(records.getRecord(position).getDate());
        holder.description.setText(records.getRecord(position).getDescription());
    }

    // Return the number of records currently in RecyclerView
    @Override
    public int getItemCount() {
        return records.getSize();
    }


    /**
     * This class uses viewHolder to put information about each record into its own view so we won't
     * display information from one record as another. This function mainly serves an organizational
     * purpose.
     *
     * @author  Orest Cokan
     * @version 1.0 Nov 10, 2018
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Class objects
        private RecordAdapter adapter;
        public TextView date;
        public TextView description;

        // Constructor and gets the corresponding data for each view
        public ViewHolder(View itemView, final RecordAdapter adapter){
            super(itemView);
            date = itemView.findViewById(R.id.record_date);
            description = itemView.findViewById(R.id.record_description);
            itemView.setOnClickListener(this);
            this.adapter = adapter;
        }

        // Set onClick listener for each record, so the record can be viewed
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction =  manager.beginTransaction();
            RecordFragment fragment = RecordFragment.newInstance(records.getRecord(position));
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }
}
