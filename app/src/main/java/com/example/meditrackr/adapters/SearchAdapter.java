package com.example.meditrackr.adapters;


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


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
        private FragmentActivity activity;
        private Context context;
        private Patient patient;

        // constructor
        public SearchAdapter(FragmentActivity activity, Context context, Patient patient) {
            this.activity = activity;
            this.context = context;
            this.patient = patient;
    }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View searchView = inflater.inflate(R.layout.search_entry, parent, false);
            return new ViewHolder(searchView, this);
        }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //BIND DATA
        holder.posTxt.setText("Position");
        holder.nameTxt.setText("Name");

    }

    //GET TOTAL NUM OF PLAYERS
    @Override
    public int getItemCount() {
        return 10;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private SearchAdapter adapter;
        private ImageView img;
        private TextView nameTxt, posTxt;



        public ViewHolder(View itemView, final SearchAdapter adapter) {
            super(itemView);
            this.img = (ImageView) itemView.findViewById(R.id.playerImage);
            this.nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
            this.posTxt = (TextView) itemView.findViewById(R.id.posTxt);

            itemView.setOnClickListener(this);
            this.adapter = adapter;

        }


        // set onClick listener for each problem to be viewed
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

