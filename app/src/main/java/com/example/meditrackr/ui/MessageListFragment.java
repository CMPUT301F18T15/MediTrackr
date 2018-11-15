package com.example.meditrackr.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.MessageListAdapter;
import com.example.meditrackr.models.CommentList;

/**
 * Created by Skryt on Nov 15, 2018
 */

public class MessageListFragment extends Fragment {
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;

    public static MessageListFragment newInstance(CommentList comments){
        MessageListFragment fragment = new MessageListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Messages", comments);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_message_list, container, false);

        final CommentList comments = (CommentList) getArguments().getSerializable("Messages");
        mMessageRecycler = (RecyclerView) rootView.findViewById(R.id.reyclerview_message_list);
        mMessageAdapter = new MessageListAdapter(getContext(), comments);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }
}
