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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meditrackr.R;
import com.example.meditrackr.models.Patient;

/**
 * Crated by Skryt on Nov 18, 2018
 */

// Class shows a result list in a recycler view
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
        // Class objects
        private FragmentActivity activity;
        private Context context;
        private Patient patient;

        // Constructor
        public SearchAdapter(FragmentActivity activity, Context context, Patient patient) {
            this.activity = activity;
            this.context = context;
            this.patient = patient;
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


    // Class places each record into its corresponding view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         // Class objects   
        //BIND DATA
        holder.posTxt.setText("Position");
        holder.nameTxt.setText("Name");

    }

    //GET TOTAL NUM OF PLAYERS
    // Return the number of records currently in RecyclerView
    @Override
    public int getItemCount() {
        return 10;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private SearchAdapter adapter;
        private ImageView img;
        private TextView nameTxt, posTxt;


        // Constructor and gets the corresponding data for each view
        public ViewHolder(View itemView, final SearchAdapter adapter) {
            super(itemView);
            this.img = (ImageView) itemView.findViewById(R.id.playerImage);
            this.nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
            this.posTxt = (TextView) itemView.findViewById(R.id.posTxt);

            itemView.setOnClickListener(this);
            this.adapter = adapter;

        }


        // Set onClick listener for each problem to be viewed
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            //RecordsFragment fragment = RecordsFragment.newInstance();
            //transaction.addToBackStack(null);
            //transaction.replace(R.id.content, fragment);
            //transaction.commit();
        }
    }
}

