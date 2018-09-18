package com.example.mdonline;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView problems = (ImageView) findViewById(R.id.problems);
        final ImageView map = (ImageView) findViewById(R.id.map);
        final ImageView camera = (ImageView) findViewById(R.id.camera);
        final ImageView search = (ImageView) findViewById(R.id.search);
        final ImageView profile = (ImageView) findViewById(R.id.profile);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                problems.setColorFilter(getResources().getColor(R.color.colorPrimary));
                map.setColorFilter(getResources().getColor(R.color.colorPrimary));
                camera.setColorFilter(getResources().getColor(R.color.colorPrimary));
                search.setColorFilter(getResources().getColor(R.color.colorPrimary));
                profile.setColorFilter(getResources().getColor(R.color.colorPrimary));

                ImageView image = (ImageView) v;
                image.setColorFilter(Color.BLACK);
                Log.d("test", v.getId()+"");
                Log.d("test", problems.getId()+"");
            }
        };
        problems.setOnClickListener(listener);
        map.setOnClickListener(listener);
        camera.setOnClickListener(listener);
        search.setOnClickListener(listener);
        profile.setOnClickListener(listener);

    }
}
