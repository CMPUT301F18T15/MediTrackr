/*--------------------------------------------------------------------------
 * FILE: TimePickerFragment.java
 *
 * PURPOSE: For selecting date and time for a problem.
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