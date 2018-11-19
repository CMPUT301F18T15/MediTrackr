package com.example.meditrackr;

import com.example.meditrackr.models.record.BodyLocation;
import com.example.meditrackr.models.record.Geolocation;
import com.example.meditrackr.models.record.ImageList;
import com.example.meditrackr.models.record.Record;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Record Unit Tests
 */

public class RecordTest {
    private Record record;

    public RecordTest() {}

    // Initialize a new base record
    @Before
    public void initRecord() {
        final double[] tempCoords = new double[2];
        tempCoords[0] = 0.0;
        tempCoords[1] = 0.0;
        final BodyLocation bodyLoc = new BodyLocation(tempCoords, "", "");
        final Geolocation geoLoc = new Geolocation(0.0, 0.0, "");
        record = new Record
                ("", "", "", bodyLoc);
        record.setGeoLocation(geoLoc);
        }

    // Test if the record can have its properties modified
    @Test
    public void setRecordTest() {
        final String newDate = "New Date";
        final String newDesc = "New Desc";
        final String newTitle = "New Title";

        // Set the new properties
        record.setDate(newDate);
        record.setDescription(newDesc);
        record.setTitle(newTitle);

        assertEquals(newDate, record.getDate());
        assertEquals(newDesc, record.getDescription());
        assertEquals(newTitle, record.getTitle());
    }

    // Test if a body location can be set in the record
    @Test
    public void setBodyLocationTest() {
        final double[] tempCoords = new double[2];
        tempCoords[0] = 1.0;
        tempCoords[1] = 2.0;

        final String face = "Body Face";
        final String loc = "Body Location";
        final BodyLocation newBodyLoc = new BodyLocation
                (tempCoords, face, loc);

        record.setBodyLocation(newBodyLoc);

        assertEquals("Body Location mismatch or not set",
                newBodyLoc, record.getBodyLocation());
    }

    // Test if a geolocation can be set in the record
    @Test
    public void setGeoLocationTest() {
        final double newLatitude = 60;
        final double newLongitude = 50;
        final String newAddr = "Earth";
        final Geolocation newGeoLoc = new Geolocation
                (newLatitude, newLongitude, newAddr);

        record.setGeoLocation(newGeoLoc);

        assertEquals("Geo Location mismatch or not set",
                newGeoLoc, record.getGeoLocation());
    }

    // Test if a reminder can be set in the record
    @Test
    public void setReminderTest() {
        final boolean[] reminders = new boolean[3];
        reminders[0] = true;
        reminders[1] = true;
        reminders[2] = false;

        record.setReminder(reminders);

        // Test each reminder
        assertEquals(reminders[0], record.getReminder(0));
        assertEquals(reminders[1], record.getReminder(1));
        assertEquals(reminders[2], record.getReminder(2));

        assertEquals("Reminders mismatch or not set",
                reminders, record.getReminders());
    }

    // Test if images can be added to the record
    @Test
    public void setImageTest() {
        final ImageList imgList = new ImageList();

        record.setImages(imgList);

        // Object equality comparison
        assertEquals("Images mismatch or not set",
                imgList, record.getImages());
    }

}
