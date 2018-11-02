package com.example.meditrackr;

import android.graphics.Bitmap;

import com.example.meditrackr.models.Problem;
import com.example.meditrackr.models.Record;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RecordTest {

    /**
     * Unit Tests for Problem
     * Trivial getters & setters testing omitted.
     */

    private Record record;


    // Initialize problem
    @Before
    public void initJUnitTest() {
        final String initTitle = "Initial Title";
        final Date initDate = new Date();
        final String initComment = "Initial Comment";
        final Bitmap[] images = {};
        final double bodyLocation = 0;
        final double[] geoLocation = {0};

        record = new Record(initDate, images, initComment, initTitle, bodyLocation, geoLocation);
    }

    // Should not be able to set title if length > MAX_TITLE_LENGTH
    // Currently assuming MAX_TITLE_LENGTH = 30
    @Test
    public void TitleSizeTest() {
        final String longTitle = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDE"; // length = 31
        record.setTitle(longTitle);
        // check that the longTitle was not allowed or added successfully
        assertNotEquals("Title was too long but was set anyway", longTitle, record.getTitle());
    }

    // Should not be able to set description if length > MAX_COMMENT_LENGTH;
    // Currently assuming MAX_COMMENT_LENGTH = 300;
    @Test
    public void DescSizeTest() {
        // create a comment 301 charactes long
        String longComment = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCD";
        for (short i = 0; i < 10; i++)
            longComment += longComment;
        longComment += "X";
        record.setComment(longComment);
        // check that the long comment was not allowed
        assertNotEquals("Title was too long but was set anyway", longComment, record.getComment());
    }
}
