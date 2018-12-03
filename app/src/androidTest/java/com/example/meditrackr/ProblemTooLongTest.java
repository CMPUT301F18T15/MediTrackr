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
package com.example.meditrackr;

//imports
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import com.example.meditrackr.ui.LoginActivity;
import com.example.meditrackr.ui.MainActivity;
import com.example.meditrackr.utils.ElasticSearch;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

// Test class for too long problem titles
public class ProblemTooLongTest extends ActivityTestRule<MainActivity> implements IntentTestInterface {

    private final String testPatientName;

    public ProblemTooLongTest() {
        super(MainActivity.class);
        testPatientName = "InstrumentationTestPatient";
    }

    @Rule
    public IntentsTestRule<LoginActivity> loginIntent =
            new IntentsTestRule<>(LoginActivity.class, false, false);

    @Before
    public void reset() {
        if (ElasticSearch.searchProfile(testPatientName) == null) {
            createTestProfile();
        } else {
            // Login to testPatient
            Intent start = new Intent();
            loginIntent.launchActivity(start);
            onView(withId(R.id.patient_username)).perform
                    (click(), typeText("InstrumentationTestPatient"), pressBack());
            onView(withId(R.id.login_button)).perform(click());
        }
    }


    @Test
    public void testLongProblem() {

        final String longTitle = "This problem has a very long name (over 30 characters)";
        final String shortDesc = "But a short description";

        final String shortTtile = "Short problem";
        final String longDesc = "A very long description: over 300 characters. GJDFIGUGEHJW9EF0J90FJFIDNVIUNQ9 RQDU9SFHDUIFHUDFH 2UJ3JD9IJFDSFSFOJWIDJ9023JOSJFOIDSHFIOGJ4 98JJFIDFJOGIJ40 JEIFJEIFJDIFJ9WFJ4 UDSFJ9UJ4FI9 JEWIFJEFJ92IFJIFEJFIJFIW9J9EWJFI9WFI9FJI9FJW9FJEI9 FJE9WFJE 8FJE9FJ9FJ9IFJE39J FE9JF9J3F93JF93F9E9FJ9EJF9J3F93J9E8jkDNADASSIFUSF";

        // Long title, short description
        onView(withId(R.id.add_problem_floating)).perform(click());
        onView(withId(R.id.problem_title_field)).perform(click(), typeText(longTitle), pressBack());
        onView(withId(R.id.problem_description_field)).perform
                (click(), closeSoftKeyboard(), typeText(shortDesc), pressBack());
        onView(withId(R.id.problem_add_button)).perform(click());

        try {
            onView(withId(R.id.problem_add_button)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            fail("Problem was added with a long title");
        }

        // Short title, long description
        onView(withId(R.id.problem_title_field)).perform(click(), replaceText(shortTtile), pressBack());
        onView(withId(R.id.problem_description_field)).perform
                (click(), replaceText(longDesc), pressBack());
        pauseTest(3);
        onView(withId(R.id.problem_add_button)).perform(click());

        try {
            onView(withId(R.id.problem_add_button)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            fail("Problem was added without a title");
        }

        // Return to Main
        Espresso.pressBack();
    }

    // Creates the test patient account in case it wasn't created
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