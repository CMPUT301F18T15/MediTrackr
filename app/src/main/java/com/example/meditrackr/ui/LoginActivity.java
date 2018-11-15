package com.example.meditrackr.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.meditrackr.R;

/**
 * this activity only exists for LoginFragment to work
 *
 * @author  Orest Cokan
 * @version 2.0 Nov 13, 2018.
 */


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        LoginFragment fragment = LoginFragment.newInstance();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

    }
}
