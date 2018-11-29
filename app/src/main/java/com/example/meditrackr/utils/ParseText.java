package com.example.meditrackr.utils;

import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Problem;
import com.example.meditrackr.models.record.Record;

import java.util.ArrayList;

/**
 * Crated by Skryt on Nov 29, 2018
 */

public class ParseText {

    // Parse records/problems only
    public static ArrayList<CustomFilter> parseRecordProblem(String query, Patient patient, ArrayList<CustomFilter> customFilter){
        String[] keywords = query.split(" ");

        for (String keyword : keywords) {
            for (int i = 0; i < patient.getProblems().getSize(); i++) {
                Problem problem = patient.getProblem(i);
                if (problem.getDescription().contains(keyword)
                        || problem.getTitle().contains(keyword)) {
                    CustomFilter filter = new CustomFilter(
                            patient.getUsername(),
                            false,
                            problem.getTitle(),
                            problem.getDescription(),
                            problem.getDate(),
                            i);
                    customFilter.add(filter);
                }

                for (int j = 0; j < problem.getRecords().getSize(); j++) {
                    Record record = problem.getRecord(j);
                    if (record.getDescription().contains(keyword)
                            || record.getTitle().contains(keyword)) {
                        CustomFilter filter = new CustomFilter(
                                patient.getUsername(),
                                true,
                                record.getTitle(),
                                record.getDescription(),
                                record.getDate(),
                                i,
                                j);
                        customFilter.add(filter);

                    }
                }
            }
        }
        return customFilter;
    }

    public static ArrayList<CustomFilter> parseGeolocation(){
        return null;
    }


    public static ArrayList<CustomFilter> parseBodylocation(){
        return null;
    }
}
