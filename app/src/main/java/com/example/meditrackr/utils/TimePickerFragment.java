package com.example.meditrackr.utils;

/**
 * Crated by Skryt on Nov 23, 2018
 */


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.time.LocalDateTime;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    private Boolean isSet = false;
    private int hour;
    private int minute;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static TimePickerFragment newInstance(@NonNull LocalDateTime date){
        TimePickerFragment timePickerFragment = new TimePickerFragment();

        Bundle message = new Bundle();
        message.putInt("HOUR", date.getHour());
        message.putInt("MINUTE", date.getMinute());
        timePickerFragment.setArguments(message);

        return timePickerFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog (Bundle saved){
        return new TimePickerDialog(getActivity(), this, getArguments().getInt("HOUR"),
                getArguments().getInt("MINUTE"), DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.hour = hourOfDay;
        this.minute = minute;
        this.isSet = true;
    }

    public int getHour(){
        if (isSet){
            return this.hour;
        }
        else{
            return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        }
    }

    public int getMinute(){
        if (isSet){
            return this.minute;
        }
        else{
            return Calendar.getInstance().get(Calendar.MINUTE);
        }
    }

    public boolean getIsSet(){
        return this.isSet;
    }

}