package com.example.meditrackr.models;

import java.io.Serializable;
import java.util.ArrayList;


public class RecordList implements Serializable {
    private ArrayList<Record> recordList = new ArrayList<>();

    public void addRecord(Record newRecord) {
        recordList.add(newRecord);
    }

    public void removeRecord(Record record){
        recordList.remove(record);
    }

    public Boolean recordExists(Record record){
        return recordList.contains(record);
    }

    public int getIndex(Record record){
        return recordList.indexOf(record);
    }

    public int getSize(){
        return recordList.size();
    }

    public String toString(){
        return recordList.toString();
    }


}