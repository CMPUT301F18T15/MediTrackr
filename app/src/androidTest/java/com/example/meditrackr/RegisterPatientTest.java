package com.example.meditrackr;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.*;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.ui.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertEquals;

public class RegisterPatientTest extends ActivityTestRule<LoginActivity> {

    private final String patientName = "Patient Zero";
    private final String patientEmail = "patzero@zero.com";
    private final String patientPhone = "7801234567";
    private Patient newPatient;

    public RegisterPatientTest() {
        super(LoginActivity.class);
    }

    @Rule
    public IntentsTestRule<LoginActivity> loginIntent =
            new IntentsTestRule<>(LoginActivity.class, false, false);

    @Before
    public void reset() {
        //Intents.init();
        ElasticSearchController.deleteUser(patientName);
        newPatient = new Patient
                (patientName, patientEmail, patientPhone, false);
        Intent start = new Intent();
        start.putExtra("Patient", newPatient);
        loginIntent.launchActivity(start);
    }

    @After
    public void clean() {
        //Intents.release();
    }

    @Test
    public void testStart() {
        onView(withId(R.id.not_member)).perform(click());
        onView(withId(R.id.patient_username)).perform(click()).perform(typeTextIntoFocusedView(patientName));
        Espresso.pressBack();
        onView(withId(R.id.phone_number)).perform(click()).perform(typeTextIntoFocusedView(patientPhone));
        Espresso.pressBack();
        onView(withId(R.id.patient_phone)).perform(click()).perform(typeTextIntoFocusedView(patientEmail));
        Espresso.pressBack();
        onView(withId(R.id.Patient)).perform(click());
        onView(withId(R.id.signup_button)).perform(swipeUp()).perform(click());

        onView(withId(R.id.patient_username)).perform(click(), typeText(patientName), pressBack());

        intended(hasComponent(LoginActivity.class.getName()));


        //assertEquals("New patient not added to Elastic Search", newPatient, ElasticSearchController.searchProfile(patientName));
    }
}
