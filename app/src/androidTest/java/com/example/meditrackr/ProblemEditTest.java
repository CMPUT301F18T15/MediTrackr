package com.example.meditrackr;

import android.content.Intent;
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
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class ProblemEditTest extends ActivityTestRule<MainActivity> implements IntentTestInterface {

    private final String problemName = "Hydrophobia";
    private final String problemDesc = "Water D:";
    private final String testPatientName;

    public ProblemEditTest() {
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
    public void testEditProblem() {
        // Create problem
        onView(withId(R.id.add_problem_floating)).perform(click());
        onView(withId(R.id.problem_title_field)).perform(click(), typeText(problemName), pressBack());
        onView(withId(R.id.problem_description_field)).perform
                (click(), closeSoftKeyboard(), typeText(problemDesc), pressBack());
        onView(withId(R.id.problem_add_button)).perform(click());

        try {
            onView(withId(R.id.add_problem_floating)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            fail("Problem was not added");
        }

        final String appendTtile = " CRITICAL";
        final String appendDesc = " Very very terrified!!!!";
        onView(withId(R.id.problem_edit_button)).perform(click());
        onView(withId(R.id.edit_problem_title_field))
                .perform(click(), typeText(appendTtile), pressBack());
        onView(withId(R.id.edit_problem_description_field))
                .perform(click(), closeSoftKeyboard(), typeText(appendDesc), pressBack());
        onView(withId(R.id.edit_problem_save_button)).perform(click());

        try {
            onView(withText(problemName + appendTtile)).check(matches(isDisplayed()));
            onView(withText(problemDesc + appendDesc)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            fail("Problem was not edited");
        }
        onView(withId(R.id.problem_delete_button)).perform(click());
        onView(withText("YES")).perform(click());
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