/*
 *  Apache 2.0 License Notice
 *
 *  Copyright 2018 CMPUT301F18T15
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
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
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.ui.careprovider.ProblemsFragment;

import java.util.ArrayList;

/**
 * This class displays information about all of the patients that a Care Provider is assigned
 * to in a recycler view (a type of list view).
 *
 * There is also an onclick listener, which when clicked will take the Care Provider to a page with
 * more detailed information about a particular patient (this is the ProblemAdapter). It uses
 * onCreateView to create the recycler view and uses onBindViewHolder to put the patient's
 * information into the recycler view.
 *
 * This class can use getItemCount to display the number of items (patients) in the recycler view.
 *
 * This class uses viewHolder to put information about each patient into its own view so we won't
 * display information from one patient as another. This function mainly serves an organizational
 * purpose.
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 10, 2018
 * @see ProblemAdapter
 *
 */
public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder>{
    // Class objects
    private FragmentActivity activity;
    private ArrayList<Patient> patients = LazyLoadingManager.getPatients();

    /**
     * Constructor: Initialize the PatientAdapter class by setting the activity
     * to the appropriate fragment.
     *
     * @author  Orest Cokan
     * @param activity the fragment we are currently on
     */
    public PatientAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    /**
     * Construct the view and create the ViewHolder.
     *
     * @author  Orest Cokan
     * @param parent the parent ViewGroup
     * @param viewType an integer representing the view's type
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Instantiates layout XML (patient_entry) into its proper view object
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View patientView = inflater.inflate(R.layout.patient_entry, parent, false);
        return new ViewHolder(patientView, this);
    }

    // Set the data into each viewHolder (ie. place the patient info into the view)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Display each patient's username, email, and phone number in each viewHolder
        holder.patientUsername.setText(patients.get(position).getUsername());
        holder.patientEmail.setText(patients.get(position).getEmail());
        holder.patientPhone.setText(patients.get(position).getPhone());
    }

    // Return the number of patients currently in RecyclerView (assigned to this care provider)
    @Override
    public int getItemCount() {
        return patients.size();
    }


    /**
     *
     * This class puts information about each patient into its own view so we won't
     * display information from one patient as another. This function mainly serves an organizational
     * purpose.
     *
     * @author  Orest Cokan
     * @version 1.0 Nov 10, 2018
     */
    // Class places each patient into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Class objects
        private PatientAdapter adapter;
        private TextView patientUsername;
        private TextView patientEmail;
        private TextView patientPhone;
        private ImageButton gps;

        // Constructor and gets the corresponding data for each view
        private ViewHolder(View itemView, final PatientAdapter adapter) {
            super(itemView);
            patientUsername = itemView.findViewById(R.id.patient_username);
            patientEmail = itemView.findViewById(R.id.patient_email);
            patientPhone = itemView.findViewById(R.id.patient_phone);
            itemView.setOnClickListener(this);
            this.adapter = adapter;
        }





        // Set onClick listener for each patient, so they can be viewed
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction =  manager.beginTransaction();
            Patient patient = adapter.patients.get(position);
            LazyLoadingManager.setCarePatient(patient);
            ProblemsFragment fragment = ProblemsFragment.newInstance(position);
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit();

        }

    }

}

