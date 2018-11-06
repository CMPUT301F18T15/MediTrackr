package com.example.meditrackr.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.R;

import io.searchbox.core.SearchResult;

public class MainActivity extends AppCompatActivity {

    final ImageView problems = (ImageView) findViewById(R.id.problems);
    final ImageView map = (ImageView) findViewById(R.id.map);
    final ImageView camera = (ImageView) findViewById(R.id.camera);
    final ImageView search = (ImageView) findViewById(R.id.search);
    final ImageView profile = (ImageView) findViewById(R.id.profile);
    final Bundle bundle = getIntent().getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        problems.setImageDrawable(getResources().getDrawable(R.drawable.cross_full));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = (ImageView) v;
                problems.setImageDrawable(getResources().getDrawable(R.drawable.cross));
                map.setImageDrawable(getResources().getDrawable(R.drawable.map));
                camera.setImageDrawable(getResources().getDrawable(R.drawable.camera));
                search.setImageDrawable(getResources().getDrawable(R.drawable.search));
                profile.setImageDrawable(getResources().getDrawable(R.drawable.person));
                if(v == problems){
                    image.setImageDrawable(getResources().getDrawable(R.drawable.cross_full));
                }
                else if (v == map) {
                    image.setImageDrawable(getResources().getDrawable(R.drawable.map_full));
                }
                else if (v == camera) {
                    image.setImageDrawable(getResources().getDrawable(R.drawable.camera_full));
                }
                else if (v == search) {
                    image.setImageDrawable(getResources().getDrawable(R.drawable.search_full));
                }
                else{
                    image.setImageDrawable(getResources().getDrawable(R.drawable.person_full));
                    FragmentTransition(v);
                }
            }
        };
        problems.setOnClickListener(listener);
        map.setOnClickListener(listener);
        camera.setOnClickListener(listener);
        search.setOnClickListener(listener);
        profile.setOnClickListener(listener);

        if(bundle.get("doctor") != null ){
            CareProvider careProvider = (CareProvider) bundle.getSerializable("careProvider");

        }
        else{
            Patient patient = (Patient) bundle.getSerializable("patient");
        }
    }

    public void FragmentTransition(View v){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        if(v == problems){
            ProblemsFragment fragment = ProblemsFragment.newInstance();
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
        else if (v == map){
            MapFragment fragment = MapFragment.newInstance();
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
        else if (v == camera) {
            CameraFragment fragment = CameraFragment.newInstance();
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
        else if (v == search){
            CameraFragment fragment = CameraFragment.newInstance();
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
        else {
            UserFragment fragment = UserFragment.newInstance();
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }



}
