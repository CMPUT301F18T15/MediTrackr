/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
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

package com.example.meditrackr.adapters;

    /**
     * takes and image and puts it in a full screen view
     *
     * @author Orest Cokan
     * @version 1.0  Nov 17, 2018
     */


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.meditrackr.R;
import com.example.meditrackr.models.record.PhotoList;
import com.example.meditrackr.utils.ConvertImage;

    public class FullScreenImageAdapter extends PagerAdapter {

        private Activity activity;
        private PhotoList images;
        private LayoutInflater inflater;

        /**
         * creates variables for the class to use
         *
         * @author Orest Cokan
         * @version 1.0  Nov 17, 2018
         * @param activity      the activity that it is using
         * @param images        the images that the class will display
         */
        // constructor
        public FullScreenImageAdapter(Activity activity,
                                      PhotoList images) {
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
            // Creates view objects based on layouts in XML
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container,
                    false);

            // Get view to display an image view based on image in XML
            imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgDisplay);

            // Set image configurations
            BitmapFactory.Options options = new BitmapFactory.Options();
            // Each pixel in a bitmap image will be stored on 4 bytes
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            // Get byte array of the image
            byte[] image = images.getImage(position);
            // Convert a Base64 String into an image
            imgDisplay.setImageBitmap(ConvertImage.convertByteToBitmap(image));
            ((ViewPager) container).addView(viewLayout);
            return viewLayout; // return image
        }


        // Handle the close button for image view?
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((RelativeLayout) object);

        }
    }