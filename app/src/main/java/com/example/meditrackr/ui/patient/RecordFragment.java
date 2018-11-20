/*
 *Apache 2.0 License Notice
 *
 *Copyright 2018 CMPUT301F18T15
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
package com.example.meditrackr.ui.patient;

//imports
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meditrackr.R;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.utils.ConvertImage;

/**
 * shows user a list of the records associated with that problem in a recycler view.
 * in each item it the recycler view displays the date and the description with that record.
 *
 * when a user clicks on a record it will bring them to a page where they can edit the
 * record (AddRecordFragment)
 *
 * there is also a + icon that when pressed brings the user to a page where they
 * can create a new record gor that problem (AddRecordFragment)
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 12, 2018.
 * @see RecordFragment
 */

// Class creates a Record Fragment for patients
public class RecordFragment extends Fragment {
    // Initialize class object record and image view array
    private Record record;
    private ImageView[] images = new ImageView[10];

    // Creates new instance fragment and maps record as a serializable value in bundle
    public static RecordFragment newInstance(Record record) {
        RecordFragment fragment = new RecordFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Record", record);
        fragment.setArguments(bundle);
        return fragment;
    }

    // Creates record fragment view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_record, container, false);


        // Initialize ui attributes
        final TextView title = rootView.findViewById(R.id.record_title);
        final TextView date = rootView.findViewById(R.id.record_date);
        final TextView location = rootView.findViewById(R.id.record_location);
        final TextView description = rootView.findViewById(R.id.record_description);
        record = (Record) getArguments().getSerializable(
                "Record");


        // Allows 10 images for each record
        images[0] = rootView.findViewById(R.id.record_image_1);
        images[1] = rootView.findViewById(R.id.record_image_2);
        images[2] = rootView.findViewById(R.id.record_image_3);
        images[3] = rootView.findViewById(R.id.record_image_4);
        images[4] = rootView.findViewById(R.id.record_image_5);
        images[5] = rootView.findViewById(R.id.record_image_6);
        images[6] = rootView.findViewById(R.id.record_image_7);
        images[7] = rootView.findViewById(R.id.record_image_8);
        images[8] = rootView.findViewById(R.id.record_image_9);
        images[9] = rootView.findViewById(R.id.record_image_10);


        // Populate a record with data
        title.setText(record.getTitle());
        description.setText(record.getDescription());
        date.setText(record.getDate());
        location.setText(record.getGeoLocation().getAddress());


        // Populate with images
        try {
            for (int i = 0; i < record.getImagesSave().getSize(); i++) {
                Bitmap bitmap = ConvertImage.base64Decode(record.getImageSave(i));
                images[i].setImageBitmap(bitmap);
            }
        }catch (NullPointerException e){
            Log.d("Images", "size of array is zero, no images");
        }

        // set click listeners for each image to display them in full screen
//        for (int i = 0; i < 10; ++i) {
//            final int index = i;
//            images[i].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String record_image = record.getImageSave(index);
//                    Intent intent = new Intent(getActivity(), FullScreenViewActivity.class);
//                    ImageSave record_images = new ImageSave();
//                    record_images.addImage(record_image);
//                    intent.putExtra("IMAGES", record_images);
//                    getActivity().startActivity(intent);
//
//                }
//            });
//
//        }

        return rootView;
    }


}
