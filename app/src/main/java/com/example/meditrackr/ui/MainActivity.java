package com.example.meditrackr.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.ui.careprovider.PatientsFragment;
import com.example.meditrackr.ui.patient.MapActivity;
import com.example.meditrackr.ui.patient.ProblemsFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by Skryt on Nov 13, 2018
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MapActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set ui attributes
        final ImageView problems = (ImageView) findViewById(R.id.problems);
        final ImageView map = (ImageView) findViewById(R.id.map);
        final ImageView camera = (ImageView) findViewById(R.id.camera);
        final ImageView search = (ImageView) findViewById(R.id.search);
        final ImageView profile = (ImageView) findViewById(R.id.profile);
        final Bundle bundle = getIntent().getExtras();
        final boolean isCareProvider = ProfileManager.getIsCareProvider();

        // get userType
        setHomeView(isCareProvider);

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
                    if(!isCareProvider) {
                        ProblemsFragment fragment = ProblemsFragment.newInstance();
                        transaction.replace(R.id.content, fragment);
                    }
                    else{
                        PatientsFragment fragment = PatientsFragment.newInstance();
                        transaction.replace(R.id.content, fragment);
                    }
                    transaction.commit();
                }
                else if (v == map) {
                    if(isServicesOK()) {
                        Log.d("IsService", "why don't we get here");
                        //image.setImageDrawable(getResources().getDrawable(R.drawable.map_full));
                        Intent intent = new Intent(MainActivity.this, MapActivity.class);
                        startActivity(intent);
                    }
                    transaction.commit();
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


    public void setHomeView(boolean isCareProvider){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(isCareProvider){
            PatientsFragment fragment = PatientsFragment.newInstance();
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }else{
            ProblemsFragment fragment = ProblemsFragment.newInstance();
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if(available == ConnectionResult.SUCCESS){
            //everything is okay, user can make map requests
            Log.d(TAG, "isServiceOK: Google play services is working");
            return true;
        }else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            // an error occured but we can resolve it
            Log.d(TAG, "IsServicesOK: an error occured but we can fit it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            Toast.makeText(MainActivity.this, "You can't make map reqeuests", Toast.LENGTH_LONG).show();
        }
        return false;
    }


}
