/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 */

package com.example.meditrackr;

//imports
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
