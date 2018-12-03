/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
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
package com.example.meditrackr.adapters;

//imports
import android.content.Context;
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
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.ui.patient.ProblemsFragment;
import com.example.meditrackr.ui.patient.RecordFragment;
import com.example.meditrackr.ui.patient.RecordsFragment;
import com.example.meditrackr.utils.CustomFilter;

import java.util.ArrayList;

/**
 * creates a search adapter for a user to use. allows user to search for items throughout the app
 * ie look for problem, look for care provider, etc
 *
 * @author Orest Cokan
 * @version 1.0 Nov 18, 2018
 */

// Adapter class shows a result list in a recycler view
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    // Class objects
    private FragmentActivity activity;
    private ArrayList<CustomFilter> results;
    private Profile profile = LazyLoadingManager.getProfile();
    private int type;
    private Context context;

    // Constructor
    /**
     * creates variables for the class to use
     *
     * @author Orest Cokan
     * @version 1.0 Nov 18, 2018
     * @param activity      the activity that the user is searching from
     * @param context       the context for the adapter
     * @param results       the results to show on the screen
     */
    public SearchAdapter(FragmentActivity activity, Context context, ArrayList<CustomFilter> results, int type) {
        this.activity = activity;
        this.context = context;
        this.results = results;
        this.type = type;
}

    // Display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Instantiates layout XML into its proper view object
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View searchView = inflater.inflate(R.layout.search_entry, parent, false);
        return new ViewHolder(searchView, this);
    }


    // Places each search item into its corresponding view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleTxt.setText(results.get(position).getTitle());
        holder.descriptionTxt.setText(results.get(position).getDescription());

        switch (type){
            case 1: // If the user is searching for a record keyword
                if(results.get(position).isRecord()){
                    holder.typeTxt.setText("Record");
                }else {
                    holder.typeTxt.setText("Problem");
                }
                break;
            case 2: // If the user is searching for a geolocation keyword
                holder.typeTxt.setText("Geolocation");
                holder.distanceTxt.setText("Distance: "+
                        results.get(position).getGeolocation().getDistance());
                break;
            case 3: // If the user is searching for a body location keyword
                holder.typeTxt.setText("Bodylocation");
                break;
        }

        if(profile.getisCareProvider()){
            holder.usernameTxt.setText(results.get(position).getUsername());
        }
    }


    // Return the number of records currently in RecyclerView
    @Override
    public int getItemCount() {
        return results.size();
    }


// Displays recycler view
public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    // Class objects
    private SearchAdapter adapter;
    private ImageView img;
    private TextView titleTxt, typeTxt, descriptionTxt, usernameTxt, distanceTxt;


    // Constructor and gets the corresponding data for each view
    private ViewHolder(View itemView, final SearchAdapter adapter) {
        super(itemView);
        this.img = (ImageView) itemView.findViewById(R.id.playerImage);
        this.titleTxt = (TextView) itemView.findViewById(R.id.titleText);
        this.typeTxt = (TextView) itemView.findViewById(R.id.typeText);
        this.descriptionTxt = (TextView)itemView.findViewById(R.id.descriptionText);
        this.usernameTxt = (TextView) itemView.findViewById(R.id.usernameText);
        this.distanceTxt = (TextView) itemView.findViewById(R.id.distanceText);
        itemView.setOnClickListener(this);
        this.adapter = adapter;
        }


    // Onclick listener for each problem to be viewed
    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        FragmentManager manager = adapter.activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Profile profile = LazyLoadingManager.getProfile();
        CustomFilter filter = adapter.results.get(position);

        if(!profile.getisCareProvider()){
            Patient patient = (Patient) profile;

            // going in as a PATIENT
            if(adapter.results.get(position).isRecord()){
                Record record = patient.getProblem(filter.getProblemIndex())
                        .getRecord(filter.getRecordIndex());
                RecordFragment fragment = RecordFragment.newInstance(record);
                transaction.addToBackStack(null);
                transaction.replace(R.id.content, fragment);
            }
            else {
                RecordsFragment fragment = RecordsFragment.newInstance(filter.getProblemIndex());
                transaction.addToBackStack(null);
                transaction.replace(R.id.content, fragment);
            }
            transaction.commit();

        } else { // going in as a doctor
            String username = adapter.results.get(position).getUsername();
            ArrayList<Patient> patients = LazyLoadingManager.getPatients();
            if(adapter.results.get(position).isRecord()){
                for(Patient patient: patients){
                    if(patient.getUsername().equals(username)){
                        Record record = patient.getProblem(filter.getProblemIndex()).getRecord(filter.getRecordIndex());
                        RecordFragment fragment = RecordFragment.newInstance(record);
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.content, fragment);
                    }
                }
            }else{
                for(Patient patient: patients){
                    if(patient.getUsername().equals(username)){
                        com.example.meditrackr.ui.careprovider.RecordsFragment fragment =
                                com.example.meditrackr.ui.careprovider.RecordsFragment.
                                        newInstance(patient.getProblem(filter.getProblemIndex()).
                                                getRecords(), patient.getProblem(filter.getProblemIndex()).getComments());
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.content, fragment);
                    }
                }
            }
            transaction.commit();
        }
    }
}
}

