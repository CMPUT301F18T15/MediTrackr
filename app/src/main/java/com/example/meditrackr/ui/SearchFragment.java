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
package com.example.meditrackr.ui;

//imports
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.meditrackr.R;
import com.example.meditrackr.adapters.SearchAdapter;
import com.example.meditrackr.models.Patient;

import br.com.mauker.materialsearchview.MaterialSearchView;


/**
 * Crated by Skryt on Nov 18, 2018
 */

// Class creates search fragment
public class SearchFragment extends Fragment {
    // Initialize class objects
    private Patient patient;
    private SearchView mSearch;

    // Create new frgament instance
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    // Creates search fragment view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_search, container, false);

        // Initialize ui attributes
        mSearch = (SearchView) rootView.findViewById(R.id.mSearch);
        ImageView icon = mSearch.findViewById(android.support.v7.appcompat.R.id.search_button);
        icon.setColorFilter(Color.BLACK);
        mSearch.setIconified(false);
        mSearch.setClickable(true);
        RecyclerView rv = rootView.findViewById(R.id.myRecycler);

        // Set view properties
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());


        // Adapt search items into recycler view
        final SearchAdapter adapter = new SearchAdapter(getActivity(),getContext(), patient);
        rv.setAdapter(adapter);

        // Sets a listener for user text input
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return rootView;
    }
}

