package com.example.meditrackr.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.MessageListAdapter;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.Comment;
import com.example.meditrackr.models.CommentList;
import com.example.meditrackr.models.Profile;

/**
 * Created by Skryt on Nov 15, 2018
 */

public class MessageListFragment extends Fragment {
    private MessageListAdapter adapter;
    private Profile profile = ProfileManager.getProfile();

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
        final Button sendbutton = (Button) rootView.findViewById(R.id.button_chatbox_send);
        final EditText chatBox = (EditText) rootView.findViewById(R.id.edittext_chatbox);
        final RecyclerView messageList = (RecyclerView) rootView.findViewById(R.id.reyclerview_message_list);

        messageList.setHasFixedSize(false);
        adapter = new MessageListAdapter(getContext(), comments);
        messageList.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        messageList.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        messageList.setLayoutManager(manager);

        // on click listener for send button
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment comment = new Comment(
                        chatBox.getText().toString(),
                        profile.getUsername()
                );
                comments.addComment(comment);
                adapter.notifyDataSetChanged();
                chatBox.setText(null);
                SaveLoadController.saveProfile(getContext(), profile);
            }
        });

        return rootView;
    }
}
