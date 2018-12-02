package com.example.meditrackr;

import android.content.Intent;
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
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProfileTest extends ActivityTestRule<MainActivity> implements IntentTestInterface {

    private final String testDoctorName = "TestDoctorAcc";
    private final String newName = testDoctorName + "New";

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


    @Test
    public void testAModifyProfile() {
        onView(withId(R.id.patient_username)).perform
                (click(), typeText(testDoctorName), pressBack());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.profile)).perform(click());
        onView(withId(R.id.edit_button)).perform(click());
        pauseTest(1);
        onView(withId(R.id.patient_username)).perform(click(), replaceText(newName), pressBack());
        onView(withId(R.id.save_edits_button)).perform(click());
    }


    @Test
    public void testBLoginNewProfile() {
        onView(withId(R.id.patient_username)).perform
                (click(), typeText(newName), pressBack());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.profile)).perform(click());
        onView(withId(R.id.edit_button)).perform(click());
        pauseTest(1);

        onView(withId(R.id.patient_username)).perform(click(), replaceText(testDoctorName), pressBack());
        onView(withId(R.id.save_edits_button)).perform(click());
    }

    public void createTestProfile() {
        Intent start = new Intent();
        loginIntent.launchActivity(start);
        onView(withId(R.id.not_member)).perform(click());
        onView(withId(R.id.patient_username)).perform(click(), typeText(testDoctorName), pressBack());
        onView(withId(R.id.phone_number)).perform(click(), typeText("7808471293"), pressBack());
        onView(withId(R.id.patient_email)).perform(click(), typeText("testDoctor@test.com"), pressBack());
        onView(withId(R.id.CareProvider)).perform(click());
        onView(withId(R.id.signup_button)).perform(click());
    }

    public void pauseTest(int seconds) {
        final long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + (long) seconds) { }
    }

}