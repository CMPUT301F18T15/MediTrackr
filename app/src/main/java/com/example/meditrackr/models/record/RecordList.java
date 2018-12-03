/*--------------------------------------------------------------------------
 * FILE: RecordList.java
 *
 * PURPOSE: Stores the list of Record objects associated with a problem.
 *
 *     Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 --------------------------------------------------------------------------*/
package com.example.meditrackr.models.record;

//imports
import com.example.meditrackr.models.Problem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * RecordList: A list of records.
 *
 * Allows adding and removing elements (by both index and record),
 * checking if a record object exists in the array and retrieving the
 * size of the list.
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

// A RecordList class that holds all methods pertaining to RecordList
public class RecordList implements Serializable {

    // Create array of records
    private ArrayList<Record> records = new ArrayList<>();


    /**
     * Appends a new record to the end of the RecordList.
     *
     * @author  Orest Cokan
     * @param newRecord a new Record object to add to the list
     * @see Record
     */
    public void addRecord(Record newRecord) {
        records.add(newRecord);
    }


    /**
     * Removes a Record object from the RecordList.
     *
     * @author  Orest Cokan
     * @param record    the Record object to remove from the list
     */
    public void removeRecord(Record record){
        records.remove(record);
    }


    /**
     * Removes a Record object from the RecordList.
     *
     * @author  Orest Cokan
     * @param index    the index of the record to remove from the list
     */
    public void removeRecord(int index){
        records.remove(index);
    }



    /**
     * Checks if a record is in the RecordList and returns
     * true or false accordingly.
     *
     * @author  Orest Cokan
     * @param record     the Record object to check for
     * @return           true if the record is in the list, false otherwise
     * @see Record
     */
    public Boolean recordExists(Record record){
        return records.contains(record);
    }


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/


    /**
     * Retrieve the index of a specific item.
     *
     * @author  Orest Cokan
     * @param record    the record whose index we want to find
     * @see Record
     */
    public int getIndex(Record record){
        return records.indexOf(record);
    }


    /**
     * Gets the number of the records in the RecordList.
     *
     * @author  Orest Cokan
     */
    public int getSize(){
        return records.size();
    }


    /**
     * Converts the object to a string representation.
     *
     * @author  Orest Cokan
     * @return  returns a string representation of the object
     */
    public String toString() { return records.toString(); }


    /**
     * Gets a record from a given index.
     *
     * @author  Orest Cokan
     * @param index     the index of the record
     * @return          the record that it got
     * @see Record
     */
    public Record getRecord(int index){
        return records.get(index);
    }

}