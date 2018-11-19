package com.example.meditrackr.ui;

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
public class SearchFragment extends Fragment {
    private Patient patient;
    private SearchView mSearch;


    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    // Creates view for fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_search, container, false);
        mSearch = (SearchView) rootView.findViewById(R.id.mSearch);
        ImageView icon = mSearch.findViewById(android.support.v7.appcompat.R.id.search_button);
        icon.setColorFilter(Color.BLACK);
        mSearch.setIconified(false);
        mSearch.setClickable(true);
        RecyclerView rv = rootView.findViewById(R.id.myRecycler);

        //SET ITS PROPETRIES
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        final SearchAdapter adapter = new SearchAdapter(getActivity(),getContext(), patient);
        rv.setAdapter(adapter);

        //SEARCH
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

