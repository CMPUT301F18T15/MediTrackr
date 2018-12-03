package com.example.meditrackr;

import android.content.Intent;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import com.example.meditrackr.ui.LoginActivity;
import com.example.meditrackr.ui.MainActivity;
import com.example.meditrackr.utils.ElasticSearch;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchPatientTest extends ActivityTestRule<MainActivity> {

    private final String testDoctorName = "Gregory House";
    private final String testDoctorEmail = "house@ppth.com";
    private final String testDoctorPhone = "5102941234";

    private final String testPatientName = "Carter Page";
    private final String testPatientEmail = "page@generic.com";
    private final String testPatientPhone = "5109481568";

    public SearchPatientTest() {
        super(MainActivity.class);
    }

    @Rule
    public IntentsTestRule<LoginActivity> loginIntent =
            new IntentsTestRule<>(LoginActivity.class, false, false);

    @Test
    public void APseudoMakeDoc() {
        if (ElasticSearch.searchProfile(testDoctorName) != null)
            return;
        Intent start = new Intent();
        loginIntent.launchActivity(start);
        onView(withId(R.id.not_member)).perform(click());
        onView(withId(R.id.patient_username)).perform(click(), typeText(testDoctorName), pressBack());
        onView(withId(R.id.patient_email)).perform(click(), typeText(testDoctorEmail), pressBack());
        onView(withId(R.id.phone_number)).perform(click(), typeText(testDoctorPhone), pressBack());
        onView(withId(R.id.CareProvider)).perform(click());
        onView(withId(R.id.signup_button)).perform(click());
    }

    @Test
    public void BPseudoMakePat() {
        if (ElasticSearch.searchProfile(testPatientName) != null)
            return;
        Intent start = new Intent();
        loginIntent.launchActivity(start);
        onView(withId(R.id.not_member)).perform(click());
        onView(withId(R.id.patient_username)).perform(click(), typeText(testPatientName), pressBack());
        onView(withId(R.id.patient_email)).perform(click(), typeText(testPatientEmail), pressBack());
        onView(withId(R.id.phone_number)).perform(click(), typeText(testPatientPhone), pressBack());
        onView(withId(R.id.Patient)).perform(click());
        onView(withId(R.id.signup_button)).perform(click());
    }

    @Test
    public void CTestAddPatient() {
        Intent start = new Intent();
        loginIntent.launchActivity(start);
        onView(withId(R.id.patient_username)).perform
                (click(), typeText(testDoctorName), pressBack());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.add_patient_floating)).perform(click());
        onView(withId(R.id.search_patient)).perform
                (click(), ViewActions.closeSoftKeyboard(), typeText(testPatientName), pressBack(), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.careprovider_search_for_patient_button)).perform(click());
        onView(withId(R.id.search_add_patient_button)).perform(click());
    }
}
