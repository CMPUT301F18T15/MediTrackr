package com.example.meditrackr;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class OfflineTest {
    /**
     * Unit test for offline behavior
     */

    private Problem problem;

    // OfflineTest constructor method
    public OfflineTest() {
        super(com.example.meditrackr.OfflineBehavior.class);
    }

    // Checks whether the created problem was corrupted when made offline
    public void checkForCorruptFile() {
        public String problemTitle = "Itchy nose";
        public String problemDesc = "I have an itchy nose";
        problem.setTitle(problemTitle);
        problem.setDescription(problemDesc);
        assertEquals("Problem title should be Itchy Nose", problemTitle, problem.getTitle);
        system.out.println("Problem file was not corrupted while adding offline");
    }

}
