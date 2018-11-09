package com.example.meditrackr.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.example.meditrackr.R;

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
        final Bundle bundle = getIntent().getExtras();
        final String userType;

        userType = getUserType(bundle);
        setHomeView(userType);

        problems.setImageDrawable(getResources().getDrawable(R.drawable.cross_full));
        map.setImageDrawable(getResources().getDrawable(R.drawable.map));
        camera.setImageDrawable(getResources().getDrawable(R.drawable.camera));
        search.setImageDrawable(getResources().getDrawable(R.drawable.search));
        profile.setImageDrawable(getResources().getDrawable(R.drawable.person));


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = (ImageView) v;
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                problems.setImageDrawable(getResources().getDrawable(R.drawable.cross));
                map.setImageDrawable(getResources().getDrawable(R.drawable.map));
                camera.setImageDrawable(getResources().getDrawable(R.drawable.camera));
                search.setImageDrawable(getResources().getDrawable(R.drawable.search));
                profile.setImageDrawable(getResources().getDrawable(R.drawable.person));
                if(v == problems){
                    image.setImageDrawable(getResources().getDrawable(R.drawable.cross_full));
                    ProblemsFragment fragment = ProblemsFragment.newInstance();
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
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
                    UserFragment fragment = UserFragment.newInstance();
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                }
            }
        };
        problems.setOnClickListener(listener);
        map.setOnClickListener(listener);
        camera.setOnClickListener(listener);
        search.setOnClickListener(listener);
        profile.setOnClickListener(listener);
    }


    public void setHomeView(String userType){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(userType.equals("Patient")){
            ProblemsFragment fragment = ProblemsFragment.newInstance();
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }else{
            CareProviderPatientsFragment fragment = CareProviderPatientsFragment.newInstance();
            transaction.replace(R.id.content, fragment);
            transaction.commit();

        }
    }

    public String getUserType(Bundle bundle){
        String user;
        if(bundle.get("CareProvider") != null){
            user = "CareProvider";
            Log.d("LoginUser", bundle.get("CareProvider").toString());
        }
        else {
            user = "Patient";
            Log.d("LoginUser", bundle.get("Patient").toString());

        }
        return user;
    }


}
