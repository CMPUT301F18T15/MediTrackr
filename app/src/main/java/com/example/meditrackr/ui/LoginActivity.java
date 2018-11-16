/*
 *Apache 2.0 License Notice
 *
 *Copyright 2018 CMPUT301F18T15
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

// Class creates Login Activity
public class LoginActivity extends AppCompatActivity {

    // Creates login activity view by switching to login fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager manager = getSupportFragmentManager(); // Prepares to switch fragments
        FragmentTransaction transaction = manager.beginTransaction();
        LoginFragment fragment = LoginFragment.newInstance(); // Switch to LoginFragment
        transaction.replace(R.id.content, fragment);
        transaction.commit(); // Make permanent any changes

    }
}
