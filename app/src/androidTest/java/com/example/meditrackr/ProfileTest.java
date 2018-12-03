/*--------------------------------------------------------------------------
 * FILE: ProblemTooLongTest.java
 *
 * PURPOSE: Intent test for logging in to profiles.
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
package com.example.meditrackr;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import com.example.meditrackr.ui.LoginActivity;
import com.example.meditrackr.ui.MainActivity;
import com.example.meditrackr.utils.ElasticSearch;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class ProfileTest extends ActivityTestRule<MainActivity> implements IntentTestInterface {

    private final String testDoctorName = "TestDoctorAcc";
    private final String testDoctorEmail = "testDoctor@test.com";
    private final String testDoctorPhone = "7808471293";
    private final String newEmail = "testDoctor@gmail.com";
    private final String newPhone = "7801234567";

    public ProfileTest() {
        super(MainActivity.class);
    }

    @Rule
    public IntentsTestRule<LoginActivity> loginIntent =
            new IntentsTestRule<>(LoginActivity.class, false, false);

    @Before
    public void login() {
        if (ElasticSearch.searchProfile(testDoctorName) == null) {
            createTestProfile();
        } else {
            // Login to testDoctor
            Intent start = new Intent();
            loginIntent.launchActivity(start);
        }
    }

    // Run this test when account email and phone are different from newEmail and newPhone.
    @Test
    public void testModifyProfile() {
        onView(withId(R.id.patient_username)).perform
                (click(), typeText(testDoctorName), pressBack());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.profile)).perform(click());
        onView(withId(R.id.edit_button)).perform(click());
        pauseTest(1);
        onView(withId(R.id.patient_email)).perform(click(), replaceText(newEmail), pressBack());
        onView(withId(R.id.patient_phone)).perform(click(), replaceText(newPhone), pressBack());
        onView(withId(R.id.save_edits_button)).perform(click());
    }

    public void createTestProfile() {
        Intent start = new Intent();
        loginIntent.launchActivity(start);
        onView(withId(R.id.not_member)).perform(click());
        onView(withId(R.id.patient_username)).perform(click(), typeText(testDoctorName), pressBack());
        onView(withId(R.id.phone_number)).perform(click(), typeText(testDoctorPhone), pressBack());
        onView(withId(R.id.patient_email)).perform(click(), typeText(testDoctorEmail), pressBack());
        onView(withId(R.id.CareProvider)).perform(click());
        onView(withId(R.id.signup_button)).perform(click());
    }

    public void pauseTest(int seconds) {
        final long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + (long) seconds) { }
    }

}