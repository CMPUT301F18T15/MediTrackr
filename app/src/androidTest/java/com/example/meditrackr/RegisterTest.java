package com.example.meditrackr;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import com.example.meditrackr.models.Patient;
import com.example.meditrackr.ui.LoginActivity;
import com.example.meditrackr.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.*;

public class RegisterTest extends ActivityTestRule<LoginActivity> {

    private Context instrumentationCtx;
    private final String patientName = "Patient Zero";
    private final String patientEmail = "patzero@zero.com";
    private final String patientPhone = "7801234567";
    private Patient newPatient;
    Instrumentation.ActivityMonitor activityMonitor;

    public RegisterTest() {
        super(LoginActivity.class);
    }

    @Rule
    public IntentsTestRule<LoginActivity> loginIntent =
            new IntentsTestRule<>(LoginActivity.class, false, false);

    @Before
    public void reset() {
        activityMonitor = getInstrumentation().addMonitor
                (MainActivity.class.getName(), null, false);
        newPatient = new Patient(patientName, patientEmail, patientPhone, false);
        Intent start = new Intent();
        loginIntent.launchActivity(start);
    }

    @Test
    public void usernameTooShortTest() {
        final String shortPatientName = "short";

        onView(withId(R.id.not_member)).perform(click());
        onView(withId(R.id.patient_username)).perform(click(), typeText(shortPatientName), pressBack());
        onView(withId(R.id.phone_number)).perform(click(), typeText(patientPhone), pressBack());
        onView(withId(R.id.patient_email)).perform(click(), typeText(patientEmail), pressBack());
        onView(withId(R.id.Patient)).perform(click());
        onView(withId(R.id.signup_button)).perform(click());

        Activity mainAct = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5);
        assertNull("Passed with short username", mainAct);
    }

    @Test
    public void phoneEmptyTest() {
        final String emptyPhone = "";
        onView(withId(R.id.not_member)).perform(click());
        onView(withId(R.id.patient_username)).perform(click(), typeText("Long enough name"), pressBack());
        onView(withId(R.id.patient_email)).perform(click(), typeText(patientEmail), pressBack());
        onView(withId(R.id.signup_button)).perform(click());

        assertNull("Passed with empty phone",
                getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5));
    }

    @Test
    public void emailEmptyTest() {
        final String emptyEmail = "";
        onView(withId(R.id.not_member)).perform(click());
        onView(withId(R.id.patient_username)).perform(click(), typeText("Long enough name"), pressBack());
        onView(withId(R.id.phone_number)).perform(click(), typeText(patientPhone), pressBack());
        onView(withId(R.id.signup_button)).perform(click());

        assertNull("Passed with empty phone",
                getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5));
    }

}
