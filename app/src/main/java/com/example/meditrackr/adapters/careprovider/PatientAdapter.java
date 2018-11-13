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
import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.models.PatientList;
import com.example.meditrackr.ui.careprovider.ProblemsFragment;

/**
 * Created by Skryt on Nov 10, 2018
 */

/**
 * beginning of javDocs
 *
 */
beginning of java docs
/
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



    // set the data into each viewHolder (ie. place place the patients info into the view)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.patientUsername.setText(patients.getPatient(position));
        Patient patient = (Patient) ElasticSearchController.searchProfile(patients.getPatient(position));
        holder.patientEmail.setText(patient.getEmail());
        holder.patientPhone.setText(patient.getPhone());
    }

    @Override
    public int getItemCount() {
        return patients.getSize();
    }


    // place each problem into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private PatientAdapter adapter;
        public TextView patientUsername;
        public TextView patientEmail;
        public TextView patientPhone;

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
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction =  manager.beginTransaction();
            ProblemsFragment fragment = ProblemsFragment.newInstance(position);
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }
}
