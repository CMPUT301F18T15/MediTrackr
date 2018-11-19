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
package com.example.meditrackr.ui;

//imports
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.FullScreenImageAdapter;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.record.ImageSave;

/**
 * Crated by Skryt on Nov 17, 2018
 */

public class FullScreenViewActivity extends AppCompatActivity{
    private FullScreenImageAdapter adapter;
    private ViewPager viewPager;
    private ImageSave images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        viewPager = (ViewPager) findViewById(R.id.pager);

        Intent i = getIntent();
        int position = i.getIntExtra("position", 0);
        images = LazyLoadingManager.getImages();


        adapter = new FullScreenImageAdapter(FullScreenViewActivity.this,
                images);

        viewPager.setAdapter(adapter);

        // displaying selected image first
        viewPager.setCurrentItem(position);
    }
}