package com.example.meditrackr.utils;

/**
 * this class gets the time and is able to set it for where it is needed
 * this class can also check to see if the time is set on an item
 *
 * @author Orest Cokan
 * @version 1.0 Nov 23, 2018
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

    /**
     * this class will get the hour that is already set or the current hour if it is nod22QQQQQQQ2qqqt already set
     * @author Orest cokan
     * @return      returns the hour that was retrieved
     */
    public int getHour(){
        if (isSet){
            return this.hour;
        }
        else{
            return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        }
    }

    /**
     * this class will get the minute that is already set or the current minute if it is not already set
     * @author Orest cokan
     * @return      returns the minute that was retrieved
     */
    public int getMinute(){
        if (isSet){
            return this.minute;
        }
        else{
            return Calendar.getInstance().get(Calendar.MINUTE);
        }
    }
    /**
     * this class will check to see if the item is already set. if it is return true if not return false
     * @author Orest cokan
     * @return      returns ture if it is set or false if it is not
     */
    public boolean getIsSet(){
        return this.isSet;
    }

}