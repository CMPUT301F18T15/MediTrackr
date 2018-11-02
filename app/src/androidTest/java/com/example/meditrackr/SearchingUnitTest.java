package com.example.meditrackr;

import org.junit.Test;

/**
 * Unit test for search functionality
 * Structure based off JUnit Test Lab Slides and lonelyTwitter
 *
 * All JUnit tests for Searching are stubs because implementation
 * for this feature of MediTrackr is reserved for later project parts
 */

public class SearchingUnitTest {
    private final String[] exampleGeneralKeywords = {"Weird bruise", "Orange finger",
            "Blue Rash", "Itchy Ear"};
    private final String[] exampleLocationKeywords = {"Canada", "US", "Singapore",
            "Japan", "Brazil", "South Africa"};
    private final String[] exampleBodyKeywords = {"Arm", "Leg", "Ear", "Elbow", "Cheek",
            "toe", "foot", "heel"};

    // private Keyword keyword = new Keyword();


    // First 3 methods are for general keyword search
    // Checks whether a keyword was entered for a search
    // Search input line should not be left blank
    @Test
    public void blankGeneralSearchTest() {
        /*
        Keyword generalKeyword = new GeneralKeyword("");
        keyword.add(generalKeyword);
        assertTrue("Search input line cannot be left blank",
                keyword.length() == 0);
        */
    }

    // Checks whether the keyword search is able to match up to another string
    // If general keyword doesn't exists in array, search should not be able to match
    @Test
    public void dontMatchGeneralKeywordTest() {
        /*
        Keyword generalKeyword = new GeneralKeyword("Black tongue");
        keyword.add(generalKeyword);
        assertFalse("General Keyword does not match any keywords in list",
                exampleGeneralKeywords.hasKeyword(keyword));
        */
    }

    // Checks whether the keyword search is able to match up to another string
    // If general keyword exists in array, search should be able to match
    @Test
    public void matchGeneralKeywordTest() {
        /*
        Keyword generalKeyword = new GeneralKeyword("Itchy Ear");
        keyword.add(generalKeyword);
        assertTrue("General Keyword matches with a keyword in list",
                exampleGeneralKeywords.hasKeyword(keyword));
        */
    }

   // Next 3 methods are for Geo-location keywords
   // Search input line for Geo-location shoul not be left blank
    @Test
   public void blankGeoLocationSearchTest() {
        /*
        Keyword locationKeyword = new LocationKeyword("");
        keyword.add(locationKeyword);
        assertTrue("Search input line cannot be left blank",
                keyword.length() == 0);
        */
   }

    // Checks whether the keyword search is able to match up to another string
    // If location keyword doesn't exists in array, search should not be able to match
    @Test
    public void dontMatchLocationKeywordTest() {
        /*
        Keyword locationKeyword = new LocationKeyword("Sweden");
        keyword.add(locationKeyword);
        assertFalse("Geo-location does not match any keywords in list",
                exampleLocationKeywords.hasKeyword(keyword));
        */
    }

    // Checks whether the keyword search is able to match up to another string
    // If location keyword exists in array, search should be able to match
    @Test
    public void matchLocationKeywordTest() {
        /*
        Keyword locationKeyword = new LocationKeyword("South Africa");
        keyword.add(locationKeyword);
        assertTrue("Geo-location matches with a keyword in list",
                exampleLocationKeywords.hasKeyword(keyword));
        */
    }

    // Next 3 methods are for Body-location keywords
    // Search input line for Body-location shoul not be left blank
    @Test
    public void blankBodyLocationSearchTest() {
        /*
        Keyword bodyKeyword = new BodyKeyword("");
        keyword.add(BodyKeyword);
        assertTrue("Search input line cannot be left blank",
                keyword.length() == 0);
        */
    }

    // Checks whether the keyword search is able to match up to another string
    // If body keyword doesn't exists in array, search should not be able to match
    @Test
    public void dontMatchBodyKeywordTest() {
        /*
        Keyword bodyKeyword = new BodyKeyword("wrist");
        keyword.add(bodyKeyword);
        assertFalse("Body location does not match with any keywords in list",
                exampleBodyKeywords.hasKeyword(keyword));
        */
    }

    // Checks whether the keyword search is able to match up to another string
    // If body keyword exists in array, search should be able to match
    @Test
    public void matchBodyKeywordTest() {
        /*
        Keyword bodyKeyword = new BodyKeyword("heel");
        keyword.add(bodyKeyword);
        assertTrue("Body location matches with a keyword in list",
                exampleBodyKeywords.hasKeyword(keyword));
        */
    }

}
