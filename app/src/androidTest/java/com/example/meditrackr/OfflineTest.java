package com.example.meditrackr;

import com.example.meditrackr.models.Problem;

import org.junit.Test;

import static org.junit.Assert.*;

public class OfflineTest {
    /**
     * Unit test for offline behavior
     */

    private Problem problem;

    // Checks whether the created problem was corrupted when made offline
    @Test
    public void checkForCorruptFile() {
        final String problemTitle = "Itchy nose";
        final String problemDesc = "I have an itchy nose";
        problem.setTitle(problemTitle);
        problem.setDescription(problemDesc);
        assertEquals("Problem title should be Itchy Nose", problemTitle, problem.getTitle());
    }

    // Testing for offline consistency -- deprecated functions?
    // Currently a stub.
    /*
    @RunWith(AndroidJUnit4.class)
    @LargeTest
    public void testRestart() {
        @Rule
        public ActivityTestRule<LoginActivity> activityRule =
                new ActivityTestRule(LoginActivity.class);
    }
    */

}
