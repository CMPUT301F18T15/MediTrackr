package com.example.meditrackr;

import com.example.meditrackr.models.Problem;
import com.example.meditrackr.models.Record;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class SearchingUnitTest {
    /**
     * Unit test for search functionality
     * Structure based off JUnit Test Lab Slides and lonelyTwitter
     */

    private final String[] exampleGeneralKeywords = {"Weird bruise", "Orange finger",
            "Blue Rash", "Itchy Ear"};
    private final String[] exampleLocationKeywords = {"Canada", "US", "Singapore",
            "Japan", "Brazil", "South Africa"};
    private final String[] exampleBodyKeywords = {"Arm", "Leg", "Ear", "Elbow", "Cheek",
            "toe", "foot", "heel"};
    private Keyword keyword = new Keyword();

    // Constructor method for SearchingUnitTest
    public SearchingUnitTest() {
        super(com.example.meditrackr.ElasticSearch.java);
    }

    // First 3 methods are for general keyword search
    // Checks whether a keyword was entered for a search
    // Search input line should not be left blank
    public void blankGeneralSearchTest() {
        Keyword generalKeyword = new GeneralKeyword("");
        keyword.add(generalKeyword);
        assertTrue(keyword.length() == 0);
        System.out.println("Search input line cannot be left blank");
    }

    // Checks whether the keyword search is able to match up to another string
    // If general keyword doesn't exists in array, search should not be able to match
    public void dontMatchGeneralKeywordTest() {
        Keyword generalKeyword = new GeneralKeyword("Black tongue");
        keyword.add(generalKeyword);
        assertFalse(exampleGeneralKeywords.hasKeyword(keyword));
        System.out.println("General Keyword does not match any keywords in list");
    }

    // Checks whether the keyword search is able to match up to another string
    // If general keyword exists in array, search should be able to match
    public void matchGeneralKeywordTest() {
        Keyword generalKeyword = new GeneralKeyword("Itchy Ear");
        keyword.add(generalKeyword);
        assertTrue(exampleGeneralKeywords.hasKeyword(keyword));
        System.out.println("General Keyword matches with a keyword in list");
    }

   // Next 3 methods are for Geo-location keywords
   // Search input line for Geo-location shoul not be left blank
   public void blankGeoLocationSearchTest() {
        Keyword locationKeyword = new LocationKeyword("");
        keyword.add(locationKeyword);
        assertTrue(keyword.length() == 0);
        System.out.println("Search input line cannot be left blank");
   }

    // Checks whether the keyword search is able to match up to another string
    // If location keyword doesn't exists in array, search should not be able to match
    public void dontMatchLocationKeywordTest() {
        Keyword locationKeyword = new LocationKeyword("Sweden");
        keyword.add(locationKeyword);
        assertFalse(exampleLocationKeywords.hasKeyword(keyword));
        System.out.println("Geo-location does not match any keywords in list");
    }

    // Checks whether the keyword search is able to match up to another string
    // If location keyword exists in array, search should be able to match
    public void matchLocationKeywordTest() {
        Keyword locationKeyword = new LocationKeyword("South Africa");
        keyword.add(locationKeyword);
        assertTrue(exampleLocationKeywords.hasKeyword(keyword));
        System.out.println("Geo-location matches with a keyword in list");
    }

    // Next 3 methods are for Body-location keywords
    // Search input line for Body-location shoul not be left blank
    public void blankBodyLocationSearchTest() {
        Keyword bodyKeyword = new BodyKeyword("");
        keyword.add(BodyKeyword);
        assertTrue(keyword.length() == 0);
        System.out.println("Search input line cannot be left blank");
    }

    // Checks whether the keyword search is able to match up to another string
    // If body keyword doesn't exists in array, search should not be able to match
    public void dontMatchBodyKeywordTest() {
        Keyword bodyKeyword = new BodyKeyword("wrist");
        keyword.add(bodyKeyword);
        assertFalse(exampleBodyKeywords.hasKeyword(keyword));
        System.out.println("Body location does not match with any keywords in list");
    }

    // Checks whether the keyword search is able to match up to another string
    // If body keyword exists in array, search should be able to match
    public void matchBodyKeywordTest() {
        Keyword bodyKeyword = new BodyKeyword("heel");
        keyword.add(bodyKeyword);
        assertTrue(exampleBodyKeywords.hasKeyword(keyword));
        System.out.println("Body location matches with a keyword in list");
    }

}
