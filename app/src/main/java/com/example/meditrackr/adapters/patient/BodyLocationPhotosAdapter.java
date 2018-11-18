package com.example.meditrackr.adapters.patient;

import android.content.Context;
import android.media.Image;
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
import com.example.meditrackr.models.record.BodyLocationPhotoList;

public class BodyLocationPhotosAdapter extends RecyclerView.Adapter<BodyLocationPhotosAdapter.ViewHolder>{
    private FragmentActivity activity;
    private BodyLocationPhotoList photos;

    // constructor
    public BodyLocationPhotosAdapter(FragmentActivity activity, BodyLocationPhotoList photos) {
        this.activity = activity;
        this.photos = photos;
    }


    // display the view
    @Override
    public BodyLocationPhotosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE); //creates view objects based on layouts in XML
        View bodyView = inflater.inflate(R.layout.body_location_photo_entry, parent, false);
        return new BodyLocationPhotosAdapter.ViewHolder(bodyView, this);
    }


    // set the data into each viewHolder (ie. place what each record has into the view)
    @Override
    public void onBindViewHolder(BodyLocationPhotosAdapter.ViewHolder holder, int position) {
        holder.name.setText(photos.getImage(position).getName());
        if(photos.getImage(position) == null){
            holder.photo.setImageBitmap(null);
            Log.d("ImageTest", "New profile this should be shown!");
        }else {
            holder.photo.setImageBitmap(photos.getImage(position).getImage());
        }
//        holder.photo.setImageBitmap(photos.getImage(position).getImage());
    }


    //get the number of records in RecyclerView
    @Override
    public int getItemCount() {
        return photos.getSize();
    }


    // place each record into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private BodyLocationPhotosAdapter adapter;
        public TextView name;
        public ImageView photo;

        //gets the corresponding data for each view
        public ViewHolder(View itemView, final BodyLocationPhotosAdapter adapter){
            super(itemView);
            name = itemView.findViewById(R.id.bodylocation_name);
            photo = itemView.findViewById(R.id.bodylocation_photo);
            itemView.setOnClickListener(this);
            this.adapter = adapter;
        }

        // set onClick listener for each record so you can view it in greater detail
        @Override
        public void onClick(View v) {
//            int position = getAdapterPosition();
//            FragmentManager manager = adapter.activity.getSupportFragmentManager();
//            FragmentTransaction transaction =  manager.beginTransaction();
//            Log.d("RecordsFragments", "index is: " + position);
//            RecordFragment fragment = RecordFragment.newInstance(adapter.records.getRecord(position));
//            transaction.addToBackStack(null); //allows user to bring back previous fragment when back button is pressed
//            transaction.replace(R.id.content, fragment);
//            transaction.commit();
        }
    }
}
