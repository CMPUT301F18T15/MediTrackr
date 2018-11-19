package com.example.meditrackr.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;

import br.com.mauker.materialsearchview.MaterialSearchView;

/**
 * Crated by Skryt on Nov 18, 2018
 */
public class SearchFragment extends Fragment {
    MaterialSearchView searchView;

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


        MaterialSearchView searchView = (MaterialSearchView) rootView.findViewById(R.id.search_view);
        //searchView.openSearch();


        return rootView;
    }
}
