/*
 *Apache 2.0 License Notice
 *
 *Copyright 2018 CMPUT301F18T15
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
package com.example.meditrackr.models.record;

//imports
import com.example.meditrackr.models.Problem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class creates a recordlist which stores all records.
 * this class uses addRecord to add a record to the list
 * the class uses removeRecord to remove a record from the list
 * the class uses recordExist to check to see if a record exists in the list
 * the class can use getIndex to find the index of a specific item
 * the class can use getSize find out the number of records in the list
 * lastly the class can use getRecord to find a specific record given an index
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

// A RecordList class that holds all methods pertaining to RecordList
public class RecordList implements Serializable {

    // Create array of records
    private ArrayList<Record> records = new ArrayList<>();


    /**
     * adds  a record to the to the records list
     * @author  Orest Cokan
     * @param newRecord a new record to add to the list
     * @see Record
     */
    public void addRecord(Record newRecord) {
        records.add(newRecord);
    }


    /**
     * removes  a record to the to the records list
     * @author  Orest Cokan
     * @param record    the record to remove from the list
     */
    public void removeRecord(Record record){
        records.remove(record);
    }


    /**
     * checks too see if a record is in the records list
     * @author  Orest Cokan
     * @param record     the record to check for
     * @return           1 if it exists or 0 if not
     * @see Record
     */
    public Boolean recordExists(Record record){
        return records.contains(record);
    }

    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/

    /**
     * gets index of a specific record
     * @author  Orest Cokan
     * @param record    the record we are looking for the inxex
     * @see Record
     */
    public int getIndex(Record record){
        return records.indexOf(record);
    }


    /**
     * gets the number of the records in the list
     * @author  Orest Cokan
     */
    public int getSize(){
        return records.size();
    }


    //just a test function to remove later
    public String toString() { return records.toString(); }


    /**
     * gets a record from a given index
     * @author  Orest Cokan
     * @param index     the index of the record
     * @return          the record that it got
     * @see Record
     */
    public Record getRecord(int index){
        return records.get(index);
    }

}