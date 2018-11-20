package com.example.meditrackr;

import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.models.record.RecordList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * RecordList Unit Tests
 */

public class RecordListTest {
    private RecordList recordList;
    private Record record;

    public RecordListTest() {}

    // Initialize a base record and an empty record list
    @Before
    public void initRecordList() {
        recordList = new RecordList();
        record = new Record("", "", "", null);
    }

    // Test if a record could be added to the record list
    @Test
    public void addRecordTest() {
        recordList.addRecord(record);
        assertTrue("Record not added to Record List",
                recordList.getSize() == 1);
    }

    // Test if a record could be removed
    @Test
    public void removeRecordTest() {
        recordList.addRecord(record);
        assertTrue("Record not added to Record List",
                recordList.getSize() == 1);

        recordList.removeRecord(record);
        assertTrue("Record not removed from Record List",
                recordList.getSize() == 0);
    }

    // Test if an existing record could be identified from the list
    @Test
    public void hasRecordTest() {
        recordList.addRecord(record);
        assertTrue("Record not added to Record List",
                recordList.recordExists(record));

        assertEquals(recordList.getRecord(0), record);
        assertEquals(recordList.getIndex(record), 0);
    }

    // Test if the size of the list is updating
    @Test
    public void recordListSizeTest() {
        recordList.addRecord(record);
        final Record tempRecord = new Record
                ("Second record", "", "",  null);
        recordList.addRecord(tempRecord);
        assertEquals("Incorrect number of elements in Record List",
                recordList.getSize(), 2);

        // Remove a record and test the new size
        recordList.removeRecord(record);
        assertEquals("Incorrect number of elements in Record List",
                recordList.getSize(), 1);
    }

}
