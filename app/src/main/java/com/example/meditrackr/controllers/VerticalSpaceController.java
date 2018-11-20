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
package com.example.meditrackr.controllers;

//imports
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * This class adds spaces between each item in a recycler view across the project.
 * This allows for more of a comfortable experience for the user
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 10, 2018
 *
 */

// Class creates VerticalSpaceController for RecyclerView
public class VerticalSpaceController extends RecyclerView.ItemDecoration {
    //Initialize class objects
    private final int verticalSpaceHeight;


    /**
     * sets the integer of space between items
     * @author  Orest Cokan
     * @param verticalSpaceHeight the integer to set the space to
     */
    // Sets the height of padding
    public VerticalSpaceController(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }


    /**
     * sets the integer of space between items
     * @author  Orest Cokan
     * @param outRect  the shape we want to change
     * @param view     the view it is a part of
     * @param parent   the recycler view to change
     */
    // Adds padding between views
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = verticalSpaceHeight;
    }
}
