package com.example.meditrackr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.meditrackr.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * this class creates a custome sized window so that the UI fits the users phone
 *
 * @author Orest Cokan
 * @version 1.0 Nov 18, 2018
 */

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Context mContext;

    /**
     * this function creates the variables for the class to use
     *
     * @author Orest Cokan
     * @version 1.0 Nov 18, 2018
     * @param context       the context for the adapter
     */
    public CustomInfoWindowAdapter(Context context) {
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    /**
     * this function adjust the texts for the new sizing so it fits the new window
     *
     * @author Orest Cokan
     * @version 1.0 Nov 18, 2018
     * @param marker       marker for the text position
     * @param view         the view to resize
     */
    private void rendowWindowText(Marker marker, View view){

        String title = marker.getTitle();
        TextView tvTitle = (TextView) view.findViewById(R.id.title);

        if(!title.equals("")){
            tvTitle.setText(title);
        }

        String snippet = marker.getSnippet();
        TextView tvSnippet = (TextView) view.findViewById(R.id.snippet);

        if(!snippet.equals("")){
            tvSnippet.setText(snippet);
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        rendowWindowText(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        rendowWindowText(marker, mWindow);
        return mWindow;
    }
}
