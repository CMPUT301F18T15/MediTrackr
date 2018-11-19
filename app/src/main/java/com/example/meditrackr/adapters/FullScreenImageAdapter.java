package com.example.meditrackr.adapters;

/**
 * takes and image and puts it in a full screen view
 *
 * @author Orest Cokan
 * @version 1.0  Nov 17, 2018
 */


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.meditrackr.R;
import com.example.meditrackr.models.record.ImageList;
import com.example.meditrackr.models.record.ImageSave;
import com.example.meditrackr.utils.ConvertImage;

public class FullScreenImageAdapter extends PagerAdapter {

    private Activity activity;
    private ImageSave images;
    private LayoutInflater inflater;

    // constructor

    /**
     * creates variables for the class to use
     *
     * @author Orest Cokan
     * @version 1.0  Nov 17, 2018
     * @param activity      the activity that it is using
     * @param images        the images that the class will display
     */
    public FullScreenImageAdapter(Activity activity,
                                  ImageSave images) {
        this.activity = activity;
        this.images = images;
    }

    @Override
    public int getCount() {
        return this.images.getSize();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgDisplay;
        Button btnClose;

        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container,
                false);

        imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgDisplay);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        String image = images.getImage(position);
        imgDisplay.setImageBitmap(ConvertImage.base64Decode(image));

        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}