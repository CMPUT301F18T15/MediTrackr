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
 *
 */
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    private FragmentActivity activity;
    private static RecordList records;

    // constructor
    public RecordAdapter(FragmentActivity activity, RecordList records) {
        this.activity = activity;
        this.records = records;
    }

    // display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View recordView = inflater.inflate(R.layout.record_entry, parent, false);
        return new ViewHolder(recordView, this);
    }

    // set the data into each viewHolder (ie. place place the record's info into the view)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.date.setText(records.getRecord(position).getDate());
        holder.description.setText(records.getRecord(position).getDescription());
    }

    @Override
    // can return the number of records currently in the view
    public int getItemCount() {
        return records.getSize();
    }


    // place each record into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RecordAdapter adapter;
        public TextView date;
        public TextView description;

        public ViewHolder(View itemView, final RecordAdapter adapter){
            super(itemView);
            date = itemView.findViewById(R.id.record_date);
            description = itemView.findViewById(R.id.record_description);
            itemView.setOnClickListener(this);
            this.adapter = adapter;

        }

        // set onClick listener for each record, so the record can be viewed
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