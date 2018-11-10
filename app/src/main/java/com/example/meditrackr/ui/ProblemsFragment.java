package com.example.meditrackr.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;
import com.example.meditrackr.models.DataManager;
import com.example.meditrackr.models.Patient;

public class ProblemsFragment extends Fragment {
    Patient patient = DataManager.getPatient();
    private ProblemAdapter adapter;

    public static ProblemsFragment newInstance(){
        ProblemsFragment fragment = new ProblemsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_problems, container, false);

        final FloatingActionButton addProblem = (FloatingActionButton) rootView.findViewById(R.id.add_problem_floating);
        final RecyclerView problemList = (RecyclerView) rootView.findViewById(R.id.problem_recyclerview);
        problemList.setHasFixedSize(false);
        adapter = new ProblemAdapter(getActivity());
        problemList.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        problemList.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        problemList.setLayoutManager(manager);

        VerticalSpaceItemDecoration decoration = new VerticalSpaceItemDecoration(50);
        problemList.addItemDecoration(decoration);


        addProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                ProblemAddFragment fragment = ProblemAddFragment.newInstance();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });


        return rootView;
    }

    public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int verticalSpaceHeight;

        public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = verticalSpaceHeight;
        }
    }



}