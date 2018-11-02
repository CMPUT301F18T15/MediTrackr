package com.example.meditrackr;

import android.graphics.Bitmap;

import com.example.meditrackr.models.Record;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static android.graphics.Bitmap.Config.ARGB_8888;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Unit Tests for Record
 * Trivial getters & setters testing omitted.
 */

public class RecordTest {
    private Record record;


    // Initialize a simple record
    @Before
    public void initJUnitTest() {
        final String initTitle = "Initial Title";
        final Date initDate = new Date();
        final String initComment = "Initial Comment";
        // create a basic bitmap and set Bitmap.config to ARGB_8888
        final Bitmap[] images = {Bitmap.createBitmap(200, 200, ARGB_8888)};

        final String bodyLocation = "front, 1, 1";

        // NOTE: we treat the geolocation as an array of LONGITUDE, LATITUDE both in degrees
        final double[] geoLocation = {0, 0};

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
        assertNotEquals("Title was too long but was set anyway",
                longTitle, record.getTitle());
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
        assertNotEquals("Comment was too long but was set anyway",
                longComment, record.getComment());
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

    @Test
    public void BodyLocationWithoutPhotoTest() {
        // a body location should not be added if the bitmap of photos is null
        record.setImages(null);
        record.setBodyLocation("front, 12, 123");

        assertNotEquals("Body location set when no image was specified",
                "front, 12, 123", record.getBodyLocation());
    }

    @Test
    public void BodyLocationOutOfRange() {
        // a body location should not be added if the bitmap of photos is null
        record.setImages(null);
        // this should not be allowed because a photo cannot have negative coordinates
        record.setBodyLocation("front, -12, -123");

        assertNotEquals("Body location set when coordinates were out of range",
                "front, -12, -123", record.getBodyLocation());
    }

    @Test
    public void GeoLocationWithoutPhotoTest() {
        // a geolocation location should not be added if the bitmap of photos
        // is null for this record
        record.setImages(null);
        double[] geoLocations = {12, 13};
        record.setGeoLocation(geoLocations);

        assertNotEquals("Geo-location set when no image was specified",
                geoLocations, record.getGeoLocation());
    }

    @Test
    public void InvalidGeoLocationTest() {
        // valid latitudes are in the range of -90 to 90 degrees
        // valid longitudes are in the range of -180 to 180 degrees

        // set initial values for comparison
        Bitmap[] images = {Bitmap.createBitmap(200, 200, ARGB_8888)};
        record.setImages(images);
        double[] geoLocations = {0, 0};
        record.setGeoLocation(geoLocations);

        // check that invalid geolocations are not allowed

        // check values too low
        geoLocations[0] = -181;
        geoLocations[1] = -91;
        record.setGeoLocation(geoLocations);

        assertNotEquals("Geo-location was set to invalid values",
                geoLocations, record.getGeoLocation());

        // both values too large
        geoLocations[0] = 181;
        geoLocations[1] = 91;
        record.setGeoLocation(geoLocations);

        assertNotEquals("Geo-location was set to invalid values",
                geoLocations, record.getGeoLocation());

        // check that boundary values are still accepted
        geoLocations[0] = 180;
        geoLocations[1] = 90;
        record.setGeoLocation(geoLocations);

        assertEquals("Geo-location was not set even though value are valid",
                geoLocations, record.getGeoLocation());


        // check that boundary values are still accepted
        geoLocations[0] = -180;
        geoLocations[1] = -90;
        record.setGeoLocation(geoLocations);

        assertEquals("Geo-location was not set even though value are valid",
                geoLocations, record.getGeoLocation());
    }


}
