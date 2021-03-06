/*--------------------------------------------------------------------------
 * FILE: SelectBodyLocationAdapter.java
 *
 * PURPOSE: Tracks a list of Body Location Photos specific to a patient and
 *          is used when selecting a Body Location Photo.
 *
 *     Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 --------------------------------------------------------------------------*/

package com.example.meditrackr.adapters.patient;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.BodyLocationPhotoList;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.record.BodyLocation;
import com.example.meditrackr.ui.patient.SelectPinBodyLocationFragment;
import com.example.meditrackr.utils.ConvertImage;

public class SelectBodyLocationAdapter extends RecyclerView.Adapter<SelectBodyLocationAdapter.ViewHolder> {
    private FragmentActivity activity;
    private Context context;
    private static Patient patient = LazyLoadingManager.getPatient();
    private BodyLocationPhotoList photos = patient.getBodyLocationPhotos();

    // constructor
    public SelectBodyLocationAdapter(FragmentActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    // display the view
    @Override
    public SelectBodyLocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE); //creates view objects based on layouts in XML
        View bodyView = inflater.inflate(R.layout.body_location_entry, parent, false);
        return new SelectBodyLocationAdapter.ViewHolder(bodyView, this);
    }


    // set the data into each viewHolder (ie. place what each record has into the view)
    @Override
    public void onBindViewHolder(SelectBodyLocationAdapter.ViewHolder holder, int position) {
        if(photos.getBodyLocationPhoto(position).getImage() == null){
            holder.photo.setImageBitmap(null);
            Log.d("ImageTest", "New profile this should be shown!");
        }else {
            BodyLocation photo = photos.getBodyLocationPhoto(position);
            Bitmap bitmap = ConvertImage.convertByteToBitmap(photos.getBodyLocationPhoto(position).getImage());
            holder.photo.setImageBitmap(bitmap);
            if(photo.getName() != null){
                holder.labelPhoto.setText(photo.getName());
            }
        }
    }


    //get the number of records in RecyclerView
    @Override
    public int getItemCount() {
        try {
            return photos.getSize();
        }catch(NullPointerException e){
            return 0;
        }
    }


    // place each record into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private SelectBodyLocationAdapter adapter;
        public ImageView photo;
        public TextView labelPhoto;

        //gets the corresponding data for each view
        public ViewHolder(View itemView, final SelectBodyLocationAdapter adapter){
            super(itemView);
            photo = itemView.findViewById(R.id.image_item_view);
            ImageButton deleteBodyPhoto = (ImageButton) itemView.findViewById(R.id.image_remove_button);
            deleteBodyPhoto.setVisibility(View.INVISIBLE);

            labelPhoto = (TextView) itemView.findViewById(R.id.body_location_label);
            itemView.setOnClickListener(this);
            this.adapter = adapter;

        }

        // Set onClick listener for each record, so the record can be viewed
        @Override
        public void onClick(View v) {
            final int position = getAdapterPosition();
            LazyLoadingManager.setBodyLocationPhoto(adapter.photos.getBodyLocationPhoto(position));
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            SelectPinBodyLocationFragment fragment = SelectPinBodyLocationFragment.newInstance(patient.getBodyLocationPhotos().getBodyLocationPhoto(position));
            fragment.show(manager, "fragment_body_location");

        }

    }

}
