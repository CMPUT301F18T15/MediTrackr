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
import android.widget.TextView;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.PatientList;
import com.example.meditrackr.ui.careprovider.ProblemsFragment;

/**
 * This class displays information about all of the patients that a Care Provider is assigned
 * to in a recycler view.
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

// Class shows patient list and info for care providers in a recycler view
public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder>{
    // Class objects
    private FragmentActivity activity;
    private CareProvider careProvider = LazyLoadingManager.getCareProvider();
    private PatientList patients = careProvider.getPatients();

    /**
     * creating variables activity  functions to use
     *
     * @author  Orest Cokan
     * @version 1.0 Nov 10, 2018
     * @param activity this is the activity to pass the data
     */

    // Constructor
    public PatientAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    // Display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Instantiates layout XML into its proper view object
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View patientView = inflater.inflate(R.layout.patient_entry, parent, false);
        return new ViewHolder(patientView, this);
    }

    // Set the data into each viewHolder (ie. place the patient info into the view)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Display each patient's username, email, and phone number in each viewHolder
        holder.patientUsername.setText(patients.getPatient(position));
        // Search for the proper patient to put into each viewHolder
        Patient patient = (Patient) ElasticSearchController.searchProfile(patients.getPatient(position));
        holder.patientEmail.setText(patient.getEmail());
        holder.patientPhone.setText(patient.getPhone());
    }

    // Return the number of patients currently in RecyclerView
    @Override
    public int getItemCount() {
        return patients.getSize();
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
        public TextView patientUsername;
        public TextView patientEmail;
        public TextView patientPhone;

        // Constructor and gets the corresponding data for each view
        public ViewHolder(View itemView, final PatientAdapter adapter){
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
            // Load care provider
            CareProvider careProvider = LazyLoadingManager.getCareProvider();
            // Get the patient list for each care provider
            PatientList patients = careProvider.getPatients(); // Gets the patient list for each care provider
            // Prepare fragment transaction
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction =  manager.beginTransaction();
            // Search for the profile of the patient clicked on
            Patient patient = (Patient) ElasticSearchController.searchProfile(patients.getPatient(position));
            LazyLoadingManager.setCarePatient(patient); // Loads patient data
            // Transition to ProblemsFragment
            LazyLoadingManager.setImages(patient.getProblem(0).getImageAll());
            ProblemsFragment fragment = ProblemsFragment.newInstance(position);
            // Allow user to bring back previous fragment when back button is pressed
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit(); // Make permanent all changes made in transaction

        }
    }
}

