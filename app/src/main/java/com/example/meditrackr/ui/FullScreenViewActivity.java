package com.example.meditrackr.ui;

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