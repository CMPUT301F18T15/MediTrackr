/*--------------------------------------------------------------------------
 * FILE: RecordSingleTest.java
 *
 * PURPOSE: Intent test for adding a single new record.
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
import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import com.example.meditrackr.ui.LoginActivity;
import com.example.meditrackr.ui.MainActivity;
import com.example.meditrackr.utils.ElasticSearch;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecordSingleTest extends ActivityTestRule<MainActivity> implements IntentTestInterface {

    private final String testPatientName = "InstrumentationTestPatient";
    private final String problemName = "Hydrophobia";
    private final String problemDesc = "Water D:";
    private final String recordName = "Spooked";
    private final String recordDesc = "Water is spooking me OwO";

    public RecordSingleTest() {
        super(MainActivity.class);
    }

    @Rule
    public IntentsTestRule<LoginActivity> loginIntent =
            new IntentsTestRule<>(LoginActivity.class, false, false);

    @Before
    public void login() {
        if (ElasticSearch.searchProfile(testPatientName) == null) {
            createTestProfile();
        } else {
            // Login to testPatient
            Intent start = new Intent();
            loginIntent.launchActivity(start);
            onView(withId(R.id.patient_username)).perform
                    (click(), typeText(testPatientName), pressBack());
            onView(withId(R.id.login_button)).perform(click());
        }
    }


    @Test
    public void testNewRecord() {
        onView(withId(R.id.add_problem_floating)).perform(click());
        onView(withId(R.id.problem_title_field)).perform(click(), typeText(problemName), pressBack());
        onView(withId(R.id.problem_description_field)).perform
                (click(), closeSoftKeyboard(), typeText(problemDesc), pressBack());
        onView(withId(R.id.problem_add_button)).perform(click());
        onView(withId(R.id.problem_description)).perform(click());
        onView(withId(R.id.add_record_floating)).perform(click());

        pauseTest(3);
        // Allow accessing location
        try {
            onView(withText("ALLOW")).perform(click());
        } catch (NoMatchingViewException e) {}

        onView(withId(R.id.record_title_field)).perform(click(), typeText(recordName), pressBack());
        onView(withId(R.id.add_record_button)).perform(scrollTo(), click());

        try {
            onView(withId(R.id.add_record_floating)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            fail("Record was not added");
        }

        Espresso.pressBack();
        onView(withId(R.id.problem_delete_button)).perform(click());
        onView(withText("YES")).perform(click());
    }

    public void createTestProfile() {
        Intent start = new Intent();
        loginIntent.launchActivity(start);
        onView(withId(R.id.not_member)).perform(click());
        onView(withId(R.id.patient_username)).perform(click(), typeText(testPatientName), pressBack());
        onView(withId(R.id.phone_number)).perform(click(), typeText("18002263001"), pressBack());
        onView(withId(R.id.patient_email)).perform(click(), typeText("testPatient@test.com"), pressBack());
        onView(withId(R.id.Patient)).perform(click());
        onView(withId(R.id.signup_button)).perform(click());
    }

    public void pauseTest(int seconds) {
        final long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + (long) seconds) { }
    }
}