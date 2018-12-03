/*--------------------------------------------------------------------------
 * FILE: FullScreenViewActivity.java
 *
 * PURPOSE: For viewing the photo slideshow.
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
package com.example.meditrackr.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.FullScreenImageAdapter;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.models.record.PhotoList;
import com.example.meditrackr.models.record.RecordList;

/**
 * Crated by Skryt on Nov 17, 2018
 */

// Class handles full screen viewing for images
public class FullScreenViewActivity extends AppCompatActivity{
    // Initialize class objects
    private FullScreenImageAdapter adapter;
    protected PhotoList images;
    protected Patient patient;


    // Creates image as view object
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        Profile profile = LazyLoadingManager.getProfile();
        if(profile.getisCareProvider()){
            images = LazyLoadingManager.getImages();
        }else{
            patient = (Patient) profile;
            images = patient.getProblem(position).getImageAll();
        }

        // Adapt images to fullscreen
        adapter = new FullScreenImageAdapter(FullScreenViewActivity.this,
                images);

        viewPager.setAdapter(adapter);

        // Displaying selected image first
        viewPager.setCurrentItem(position);
    }
}