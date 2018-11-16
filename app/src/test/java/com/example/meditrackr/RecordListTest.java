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

    @Before
    public void initRecordList() {
        recordList = new RecordList();
        record = new Record
                ("", "", "", null, null);
    }

    @Test
    public void addRecordTest() {
        recordList.addRecord(record);
        assertTrue("Record not added to Record List",
                recordList.getSize() == 1);
    }

    @Test
    public void removeRecordTest() {
        recordList.addRecord(record);
        assertTrue("Record not added to Record List",
                recordList.getSize() == 1);
        recordList.removeRecord(0);
        assertTrue("Reocrd not removed from Record List",
                recordList.getSize() == 0);
    }

    @Test
    public void hasRecordTest() {
        recordList.addRecord(record);
        assertTrue("Record not added to Record List",
                recordList.recordExists(record));

        assertEquals(recordList.getRecord(0), record);
        assertEquals(recordList.getIndex(record), 0);

    }

    @Test
    public void recordListSizeTest() {
        recordList.addRecord(record);
        final Record tempRecord = new Record
                ("Second record", "", "", null, null);
        recordList.addRecord(tempRecord);
        assertEquals("Incorrect number of elements in Record List",
                recordList.getSize(), 2);
        recordList.removeRecord(1);
        assertEquals("Incorrect number of elements in Record List",
                recordList.getSize(), 1);
    }
}
