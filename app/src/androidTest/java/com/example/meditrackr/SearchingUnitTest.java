package com.example.meditrackr;

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
    private final Strig[] exampleLocationKeywords = {Canada, US, Singapore,
            Japan, Brazil, South Africa};
    private Keyword keyword = new Keyword();

    // Constructor method for SearchingUnitTest
    public SearchingUnitTest() {
        super(com.example.meditrackr.Searching.class);
    }

    // First 3 methods are for general keyword search
    // Checks whether a keyword was entered for a search
    // Search input line should not be left blank
    public void blankGeneralSearchTest() {
        Keyword generalKeyword = new GeneralKeyword("");
        keyword.add(generalKeyword);
        assertTrue(keyword.length() == 0);
    }

    // Checks whether the keyword search is able to match up to another string
    // If general keyword doesn't exists in array, search should not be able to match
    public void dontMatchKeywordTest() {
        Keyword generalKeyword = new GeneralKeyword("Black tongue");
        keyword.add(generalKeyword);
        assertFalse(exampleGeneralKeywords.hasKeyword(keyword));
    }

    // Checks whether the keyword search is able to match up to another string
    // If general keyword exists in array, search should be able to match
    public void matchKeywordTest() {
        Keyword generalKeyword = new GeneralKeyword("Itchy Ear");
        keyword.add(generalKeyword);
        assertTrue(exampleGeneralKeywords.hasKeyword(keyword));
    }

   // Next 3 methods are for Geo-location keywords
   // Search input line for Geo-location shoul not be left blank
   public void blankGeoLocationSearchTest() {
        Keyword locationKeyword = new LocationKeyword("");
        keyword.add(locationKeyword);
        assertTrue(keyword.length() == 0);
   }

    // Checks whether the keyword search is able to match up to another string
    // If location keyword doesn't exists in array, search should not be able to match
    public void dontMatchKeywordTest() {
        Keyword locationKeyword = new LocationKeyword("Sweden");
        keyword.add(locationKeyword);
        assertFalse(exampleLocationKeywords.hasKeyword(keyword));
    }

    // Checks whether the keyword search is able to match up to another string
    // If location keyword exists in array, search should be able to match
    public void matchKeywordTest() {
        Keyword locationKeyword = new LocationKeyword("South Africa");
        keyword.add(locationKeyword);
        assertTrue(exampleLocationKeywords.hasKeyword(keyword));

    // 2/3rds of searching unit test


    }