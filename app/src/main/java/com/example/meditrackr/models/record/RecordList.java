package com.example.meditrackr.models.record;

import com.example.meditrackr.models.Problem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class creates a recordlist which stores all records.
 * this class can add a record, remove a record, check to see if a record exists.
 * find out the number of records in the list, find a specific record and
 *
 * tostring??????
 * @parama ArrayList<Record>      creates a new empty list to put records in
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

public class RecordList implements Serializable {
    private ArrayList<Record> records = new ArrayList<>();

    public void addRecord(Record newRecord) {
        records.add(newRecord);
    }

    public void removeRecord(int record){
        records.remove(record);
    }

    public Boolean recordExists(Record record){
        return records.contains(record);
    }

    public int getIndex(Record record){
        return records.indexOf(record);
    }

    public int getSize(){
        return records.size();
    }

    public String toString() {
        return records.toString();
    }

    public Record getRecord(int index){
        return records.get(index);
    }



}