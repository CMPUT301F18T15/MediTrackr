package com.example.meditrackr.adapters.patient;


import android.content.Context;
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
import com.example.meditrackr.models.record.RecordList;
import com.example.meditrackr.ui.patient.RecordFragment;


/**
 * Created by Skryt on Nov 12, 2018
 */

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder>{
    private FragmentActivity activity;
    private RecordList records;

    // constructor
    public RecordsAdapter(FragmentActivity activity, RecordList records) {
        this.activity = activity;
        this.records = records;
    }

    // display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View problemView = inflater.inflate(R.layout.record_entry, parent, false);
        return new ViewHolder(problemView, this);
    }

    // set the data into each viewHolder (ie. place what each record it has into the view)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.date.setText(records.getRecord(position).getDate());
        holder.description.setText(records.getRecord(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return records.getSize();
    }

    // place each record into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RecordsAdapter adapter;
        public TextView date;
        public TextView description;

        public ViewHolder(View itemView, final RecordsAdapter adapter){
            super(itemView);
            date = itemView.findViewById(R.id.record_date);
            description = itemView.findViewById(R.id.record_description);
            itemView.setOnClickListener(this);
            this.adapter = adapter;

        }

        // set onClick listener for each record so you can view it in greater detail..
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
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

