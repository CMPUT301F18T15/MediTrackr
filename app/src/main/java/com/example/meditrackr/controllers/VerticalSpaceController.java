package com.example.meditrackr.controllers;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Skryt on Nov 10, 2018
 */

public class VerticalSpaceController extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;

    public VerticalSpaceController(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = verticalSpaceHeight;
    }
}