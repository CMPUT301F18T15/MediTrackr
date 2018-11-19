package com.example.meditrackr;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.ui.LoginActivity;
import com.example.meditrackr.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;


public class ProblemTest extends ActivityTestRule<MainActivity> {

    final String problemName = "Hydrophobia";
    final String problemDesc = "Water D:";
    final String testPatientName = "InstrumentationTestPatient";
    Instrumentation.ActivityMonitor activityMonitor;

    public ProblemTest() {
        super(MainActivity.class);
    }

    @Rule
    public IntentsTestRule<LoginActivity> loginIntent =
            new IntentsTestRule<>(LoginActivity.class, false, false);

    @Before
    public void reset() {

        Context instrumentationCtx;

        if (ElasticSearchController.searchProfile(testPatientName) == null) {
            createTestPatient();

        } else {

            // Login to testPatient
            activityMonitor = getInstrumentation().addMonitor
                    (MainActivity.class.getName(), null, false);
            Intent start = new Intent();
            loginIntent.launchActivity(start);
            onView(withId(R.id.patient_username)).perform
                    (click(), typeText("InstrumentationTestPatient"), pressBack());
            onView(withId(R.id.login_button)).perform(click());

        }
    }


    @Test
    public void addProblemTest() {

        // Valid name with empty description
        onView(withId(R.id.add_problem_floating)).perform(click());
        onView(withId(R.id.problem_title_field)).perform(click(), typeText(problemName), pressBack());
        onView(withId(R.id.problem_add_button)).perform(click());

        try {
            onView(withId(R.id.problem_add_button)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            fail("Problem was added without a description");
        }

        // Empty name with valid description
        Espresso.pressBack();
        onView(withId(R.id.add_problem_floating)).perform(click());

        // Close keyboard due to overlap
        onView(withId(R.id.problem_description_field)).perform
                (click(), closeSoftKeyboard(), typeText(problemDesc), pressBack());
        onView(withId(R.id.problem_add_button)).perform(click());

        try {
            onView(withId(R.id.problem_add_button)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            fail("Problem was added without a title");
        }

        // Return to Main
        Espresso.pressBack();
    }


    @Test
    public void deleteProblemTest() {
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

        onView(withId(R.id.problem_delete_button)).perform(click());
        onView(withText("YES")).perform(click());

        try {
            onView(withId(R.id.add_problem_floating)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            fail("Problem was not deleted");
        }

    }


    @Test
    public void editProblemTest() {
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
    private void createTestPatient() {
        Intent start = new Intent();
        loginIntent.launchActivity(start);
        onView(withId(R.id.not_member)).perform(click());
        onView(withId(R.id.patient_username)).perform(click(), typeText(testPatientName), pressBack());
        onView(withId(R.id.phone_number)).perform(click(), typeText("18002263001"), pressBack());
        onView(withId(R.id.patient_phone)).perform(click(), typeText("testPatient@test.com"), pressBack());
        onView(withId(R.id.Patient)).perform(click());
        onView(withId(R.id.signup_button)).perform(click());
    }
    
}
