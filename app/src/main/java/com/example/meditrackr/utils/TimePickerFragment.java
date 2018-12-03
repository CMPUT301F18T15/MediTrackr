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
package com.example.meditrackr.utils;

/**
 * this class gets the time and is able to set it for where it is needed
 * this class can also check to see if the time is set on an item
 *
 * @author Orest Cokan
 * @version 1.0 Nov 23, 2018
 */

//imports
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

// Utility class allows ParseText functionality
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    // Class objects
    private Boolean isSet = false;
    private int hour;
    private int minute;

    // Creates new time picker fragment instance and saves it in bundle
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static TimePickerFragment newInstance(@NonNull LocalDateTime date){
        TimePickerFragment timePickerFragment = new TimePickerFragment();

        Bundle message = new Bundle();
        message.putInt("HOUR", date.getHour());
        message.putInt("MINUTE", date.getMinute());
        timePickerFragment.setArguments(message);

        return timePickerFragment;
    }

    // Creates view dialog for time picker
    @NonNull
    @Override
    public Dialog onCreateDialog (Bundle saved){
        return new TimePickerDialog(getActivity(), this, getArguments().getInt("HOUR"),
                getArguments().getInt("MINUTE"), DateFormat.is24HourFormat(getActivity()));
    }

    // Set time
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