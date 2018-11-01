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
     */

    private final String[] exampleKeywords = {"Weird bruise", "Orange finger",
            "Blue Rash", "Itchy Ear"};

    // Checks whether a keyword was entered for a search
    // Search input line should not be left blank
    public void blankSearchTest() {
        final String generalKeyword = "";
        if (generalKeyword.length() == 0) {
            assertNull("Search input line cannot be left blank", generalKeyword);
        }
    }

    // Checks whether the keyword search is able to match up to another string
    // If keyword doesn't exists in array, search should not be able to match
    public void checkKeywordTest() {
        final String generalKeyword = "Black tongue";
        for(generalKeyword : exampleKeywords){
            if(exampleKeywords.contains(generalKeyword)){
                assertArrayEquals("Black tongue", "Black tongue");
            }else{
                assertNotEquals("There are no existing records/problems that" +
                        " match with this keyword", generalKeyword);
            }
        }
    }

    // Checks whether the keyword search is able to match up to another string
    // If keyword exists in array, search should be able to match
    public void checkKeywordTest() {
        final String generalKeyword = "Itchy Ear";
        for(generalKeyword : exampleKeywords){
            if(exampleKeywords.contains(generalKeyword)){
                assertArrayEquals("Itchy Ear", "Itchy Ear");
            }else{
                assertNotEquals("There are no existing records/problems that" +
                        " match with this keyword", generalKeyword);
            }
        }
    }

    //up to here is part 1/3 of unit test code
    //still have yet to figure out how to actually use assertions in testing
}