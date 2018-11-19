package com.example.meditrackr;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;

import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.ui.LoginActivity;
import com.example.meditrackr.ui.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecordTest extends ActivityTestRule<MainActivity> {

    final String problemName = "Hydrophobia";
    final String problemDesc = "Water D:";
    final String testPatientName = "InstrumentationTestPatient";
    final String recordName = "Spooked";
    final String recordDesc = "Water is spooking me OwO";
    Instrumentation.ActivityMonitor activityMonitor;

    public RecordTest() {
        super(MainActivity.class);
    }

    @Rule
    public IntentsTestRule<LoginActivity> loginIntent =
            new IntentsTestRule<>(LoginActivity.class, false, false);

    @Before
    public void login() {
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
    public void testANewProblem() {
        onView(withId(R.id.add_problem_floating)).perform(click());
        onView(withId(R.id.problem_title_field)).perform(click(), typeText(problemName), pressBack());
        onView(withId(R.id.problem_description_field)).perform
                (click(), closeSoftKeyboard(), typeText(problemDesc), pressBack());
        onView(withId(R.id.problem_add_button)).perform(click());
        onView(withId(R.id.problem_description)).perform(click());
        onView(withId(R.id.add_record_floating)).perform(click());

        // Allow accessing location
        try {
            onView(withText("ALLOW")).perform(click());
        } catch (NoMatchingViewException e) {}

        onView(withId(R.id.record_title_field)).perform(click(), typeText(recordName), pressBack());
        onView(withId(R.id.add_record_button)).perform(scrollTo(), click());
        try {
            onView(withId(R.id.add_record_button)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            fail("Record was added without a description");
        }

        onView(withId(R.id.record_title_field)).perform(scrollTo(), click(), replaceText(""), pressBack());
        onView(withId(R.id.record_description_field)).perform(scrollTo(), click(), typeText(recordDesc), pressBack());
        onView(withId(R.id.add_record_button)).perform(scrollTo(), click());
        try {
            onView(withId(R.id.add_record_button)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            fail("Record was added without a name");
        }

        Espresso.pressBack();
        Espresso.pressBack();
        onView(withId(R.id.problem_delete_button)).perform(click());
        onView(withText("YES")).perform(click());
    }


    @Test
    public void testBTwoRecords() {
        onView(withId(R.id.add_problem_floating)).perform(click());
        onView(withId(R.id.problem_title_field)).perform(click(), typeText(problemName), pressBack());
        onView(withId(R.id.problem_description_field)).perform
                (click(), closeSoftKeyboard(), typeText(problemDesc), pressBack());
        onView(withId(R.id.problem_add_button)).perform(click());
        onView(withId(R.id.problem_description)).perform(click());
        onView(withId(R.id.add_record_floating)).perform(click());
        try { // Allow accessing location
            onView(withText("ALLOW")).perform(click());
        } catch (NoMatchingViewException e) {}

        // First Record
        onView(withId(R.id.record_title_field)).perform(click(), typeText(recordName), pressBack());
        onView(withId(R.id.record_description_field)).perform(scrollTo(), click(), typeText(recordDesc), pressBack());
        onView(withId(R.id.add_record_button)).perform(scrollTo(), click());
        try {
            onView(withId(R.id.add_record_floating)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            fail("Record was added without a name");
        }

        // Second Record
        onView(withId(R.id.add_record_floating)).perform(click());
        try { // Allow accessing location
            onView(withText("ALLOW")).perform(click());
        } catch (NoMatchingViewException e) {}
        onView(withId(R.id.record_title_field)).perform(click(), typeText("WATERRr"), pressBack());
        onView(withId(R.id.record_description_field))
                .perform(scrollTo(), click(), typeText("Press F to pay respects"), pressBack());
        onView(withId(R.id.add_record_button)).perform(scrollTo(), click());
        
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();
        onView(withId(R.id.problem_delete_button)).perform(click());
        onView(withText("YES")).perform(click());
    }


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
