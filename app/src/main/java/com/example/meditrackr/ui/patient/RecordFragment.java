package com.example.meditrackr.ui.patient;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.models.record.RecordList;

/**
 * Created by Skryt on Nov 12, 2018
 */

public class RecordFragment extends Fragment {
    private Record record;
    private ImageView[] images = new ImageView[10];

    public static RecordFragment newInstance(Record record) {
        RecordFragment fragment = new RecordFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Record", record);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_record, container, false);

        // other ui attributes
        final TextView title = rootView.findViewById(R.id.record_title);
        final TextView description = rootView.findViewById(R.id.record_description);
        final TextView date = rootView.findViewById(R.id.record_date);
        record = (Record) getArguments().getSerializable(
                "Record");

        // all images
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

        // reminder memes
        final Button[] days = new Button[]{
                rootView.findViewById(R.id.button_1D),
                rootView.findViewById(R.id.button_2D),
                rootView.findViewById(R.id.button_3D),
                rootView.findViewById(R.id.button_5D),
                rootView.findViewById(R.id.button_1W),
                rootView.findViewById(R.id.button_2W),
                rootView.findViewById(R.id.button_1M)
        };

        for(int i = 0; i < days.length; i++){
            if(record.getReminder(i)){
                Drawable background = ContextCompat.getDrawable(getContext(), R.drawable.gradient);
                days[i].setBackgroundDrawable(background);
            }
        }


        // populate a record
        title.setText(record.getTitle());
        description.setText(record.getDescription());
        date.setText(record.getDate());


        // populate the images
        try {
            for (int i = 0; i < record.getImages().getSize(); i++) {
                images[i].setImageBitmap(record.getImages().getImage(i));
            }
        }catch (NullPointerException e){
            Log.d("Images", "size of array is zero, no images");
        }


        return rootView;
    }
}
