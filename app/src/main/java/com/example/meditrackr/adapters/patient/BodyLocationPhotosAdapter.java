/*--------------------------------------------------------------------------
 * FILE: BodyLocationPhotosAdapter.java
 *
 * PURPOSE: Tracks a list of Body Location Photos specific to a patient and
 *          notifies the view when photos are added or deleted.
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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
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
import com.example.meditrackr.controllers.model.BodyPhotoController;
import com.example.meditrackr.models.BodyLocationPhotoList;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.record.BodyLocation;
import com.example.meditrackr.utils.ConvertImage;
import com.example.meditrackr.utils.ThreadSave;

public class BodyLocationPhotosAdapter extends RecyclerView.Adapter<BodyLocationPhotosAdapter.ViewHolder>{
    private FragmentActivity activity;
    private Context context;
    private static Patient patient = LazyLoadingManager.getPatient();
    private BodyLocationPhotoList photos = patient.getBodyLocationPhotos();

    // constructor
    public BodyLocationPhotosAdapter(FragmentActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    // display the view
    @Override
    public BodyLocationPhotosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE); //creates view objects based on layouts in XML
        View bodyView = inflater.inflate(R.layout.body_location_entry, parent, false);
        return new BodyLocationPhotosAdapter.ViewHolder(bodyView, this);
    }


    // set the data into each viewHolder (ie. place what each record has into the view)
    @Override
    public void onBindViewHolder(BodyLocationPhotosAdapter.ViewHolder holder, int position) {
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
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private BodyLocationPhotosAdapter adapter;
        public ImageView photo;
        public TextView labelPhoto;

        //gets the corresponding data for each view
        public ViewHolder(View itemView, final BodyLocationPhotosAdapter adapter){
            super(itemView);
            photo = itemView.findViewById(R.id.image_item_view);
            ImageButton deleteBodyPhoto = (ImageButton) itemView.findViewById(R.id.image_remove_button);
            labelPhoto = (TextView) itemView.findViewById(R.id.body_location_label);
            this.adapter = adapter;


            deleteBodyPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = getAdapterPosition(); // Returns position that was clicked
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(adapter.activity,
                            R.style.AlertDialogStyle);
                    builder1.setMessage("Are you sure you want to delete the BodyLocation?");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Only delete the problem if the answer was yes
                                    BodyPhotoController.deletePhoto(adapter.context, position);
                                    ThreadSave.save(adapter.context, patient);
                                    adapter.notifyItemRemoved(position);
                                    adapter.notifyItemRangeChanged(position,
                                    patient.getBodyLocationPhotos().getSize());

                                    // Save changes to memory and ES
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    // Create and show alert dialog box
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });

        }

    }
}