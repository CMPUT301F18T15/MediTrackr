package com.example.meditrackr;

import android.graphics.Bitmap;

import com.example.meditrackr.models.Record;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.graphics.Bitmap.Config.ARGB_8888;
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
        // create a basic bitmap and set Bitmap.config to ARGB_8888
        final Bitmap[] images = {Bitmap.createBitmap(200, 200, ARGB_8888)};

        final String bodyLocation = "front, 1, 1";
        final double[] geoLocation = {0};

        record = new Record(initDate, images, initComment, initTitle, bodyLocation, geoLocation);
    }

    // Should not be able to set title if length > MAX_TITLE_LENGTH
    // Currently assuming MAX_TITLE_LENGTH = 30
    // A title that is too long should not be added
    @Test
    public void TitleTooLongTest() {
        final String longTitle = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDE"; // length = 31
        record.setTitle(longTitle);
        // check that the longTitle was not allowed or added successfully
        assertNotEquals("Title was too long but was set anyway", longTitle, record.getTitle());
    }

    // Should not be able to set description if length > MAX_COMMENT_LENGTH;
    // Currently assuming MAX_COMMENT_LENGTH = 300;
    // A comment that is too long should not be added
    @Test
    public void CommentTooLongTest() {
        // create a comment 301 characters long
        String longComment = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCD";
        for (short i = 0; i < 10; i++)
            longComment += longComment;
        longComment += "X";
        record.setComment(longComment);
        // check that the long comment was not allowed
        assertNotEquals("Title was too long but was set anyway", longComment, record.getComment());
    }


    @Test
    public void TooManyPhotosTest() {
        // check that adding too many photos (more than 10) is not allowed
        // this bitmap array contains 11 images
        Bitmap[] images = {Bitmap.createBitmap(200, 200, ARGB_8888),
                           Bitmap.createBitmap(200, 200, ARGB_8888),
                           Bitmap.createBitmap(200, 200, ARGB_8888),
                           Bitmap.createBitmap(200, 200, ARGB_8888),
                           Bitmap.createBitmap(200, 200, ARGB_8888),
                           Bitmap.createBitmap(200, 200, ARGB_8888),
                           Bitmap.createBitmap(200, 200, ARGB_8888),
                           Bitmap.createBitmap(200, 200, ARGB_8888),
                           Bitmap.createBitmap(200, 200, ARGB_8888),
                           Bitmap.createBitmap(200, 200, ARGB_8888),
                           Bitmap.createBitmap(200, 200, ARGB_8888),};

        record.setImages(images);
        // check that the bitmap was not allowed
        assertNotEquals("Bitmap contained too many photos but was set anyway",
                         images.length, record.getImages().length);
    }

    @Test
    public void FutureDateTest() {
        // check that attempting to set a date in the future is not allowed
        // set a date in the future
        Date todaysDate = new Date(System.currentTimeMillis());

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(todaysDate);

        // change the date to one day in the future
        c.add(Calendar.DAY_OF_MONTH, 1);

        // convert calendar to date
        Date currentDatePlusOne = c.getTime();

        // try to add the new date
        record.setDate(currentDatePlusOne);
        // check that the future date was not allowed
        assertNotEquals("Date set to a future timestamp, which should not be allowed",
                currentDatePlusOne, record.getDate());
    }
}
