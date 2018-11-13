package com.example.meditrackr.models.record;

import com.example.meditrackr.models.Problem;

import java.io.Serializable;
import java.util.ArrayList;


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