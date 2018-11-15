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
import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.models.PatientList;
import com.example.meditrackr.ui.careprovider.ProblemsFragment;

/**
 * Team Name CMPUT301F18T15
 * Author Skyrt
 * Created on Nov 10, 2018
 * Version 1.0
 * PatientAdapter.java creates the RecyclerView Adapter for the Patient list
 * RecyclerView
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder>{
    private FragmentActivity activity;
    private CareProvider careProvider = ProfileManager.getCareProvider();
    private PatientList patients = careProvider.getPatients();

    // constructor
    public PatientAdapter(FragmentActivity activity) {
        this.activity = activity;
    }


    // display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View patientView = inflater.inflate(R.layout.patient_entry, parent, false);
        return new ViewHolder(patientView, this);
    }


    // set the data into each viewHolder (ie. place the patient info into the view)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.patientUsername.setText(patients.getPatient(position));
        Patient patient = (Patient) ElasticSearchController.searchProfile(patients.getPatient(position));
        holder.patientEmail.setText(patient.getEmail());
        holder.patientPhone.setText(patient.getPhone());
    }


    // get the number of problems in RecyclerView
    @Override
    public int getItemCount() {
        return patients.getSize();
    }


    // place each patient into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private PatientAdapter adapter;
        public TextView patientUsername;
        public TextView patientEmail;
        public TextView patientPhone;

        //gets the corresponding data for each view
        public ViewHolder(View itemView, final PatientAdapter adapter){
            super(itemView);
            patientUsername = itemView.findViewById(R.id.patient_username);
            patientEmail = itemView.findViewById(R.id.patient_email);
            patientPhone = itemView.findViewById(R.id.patient_phone);
            itemView.setOnClickListener(this);
            this.adapter = adapter;
        }

        // set onClick listener for each patient, so they can be viewed
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FragmentManager manager = adapter.activity.getSupportFragmentManager();  //access Fragment class features
            FragmentTransaction transaction =  manager.beginTransaction();  //allow editing on manager fragment
            ProblemsFragment fragment = ProblemsFragment.newInstance(position);  //instantiate new Fragment
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit(); ////make permanent all changes performed in the transaction
        }
    }
}
