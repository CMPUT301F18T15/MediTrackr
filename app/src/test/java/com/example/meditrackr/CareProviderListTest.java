package com.example.meditrackr;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.CareProviderList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * CareProviderList Unit Tests
 */

public class CareProviderListTest {

    private CareProvider careProvider;
    private CareProviderList careProviderList;

    // Set an initial care provider and an empty CareProviderList
    @Before
    public void newCareProviderList() {
        careProvider = new CareProvider
                ("", "", "", true);
        careProviderList = new CareProviderList();
    }

    // Test if care providers can be added
    @Test
    public void addCareProviderTest() {
        careProviderList.addCareProvider(careProvider);
        assertTrue("CareProvider not added to CareProviderList",
                careProviderList.getSize() != 0);
    }

    // Test if care providers can be deleted
    @Test
    public void deleteCareProviderTest() {
        careProviderList.addCareProvider(careProvider);
        careProviderList.deleteCareProvider(careProvider);
        assertTrue("CareProvider not removed from CareProviderList",
                careProviderList.getSize() == 0);
    }

    // Test if the list is updating its size
    @Test
    public void sizeOfListTest() {
        final String user1 = "ABC";
        final String user2 = "XYZ";
        careProvider.setUsername(user1);
        final CareProvider careProviderTwo = new CareProvider
                (user2, "", "", true);

        // Add two care providers to list
        careProviderList.addCareProvider(careProvider);
        careProviderList.addCareProvider(careProviderTwo);

        assertTrue(careProviderList.careProviderExists(user1));
        assertTrue(careProviderList.careProviderExists(user2));
        assertTrue("Unexpected care provider list size",
                careProviderList.getSize() == 2);
    }

    // Test if the list is printing correctly
    @Test
    public void printListTest() {
        final CareProvider firstDoc = new CareProvider
                ("John Dorian", "", "", true);
        final CareProvider secondDoc = new CareProvider
                ("Gregory House", "", "", true);
        final CareProvider thirdDoc = new CareProvider
                ("Android 20", "", "", true);

        careProviderList.addCareProvider(firstDoc);
        careProviderList.addCareProvider(secondDoc);
        careProviderList.addCareProvider(thirdDoc);

        assertEquals(careProviderList.toString(),
                "[John Dorian, Gregory House, Android 20]");
    }

}
