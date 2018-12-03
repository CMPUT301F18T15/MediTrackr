/*--------------------------------------------------------------------------
 * FILE: MessageListAdapter.java
 *
 * PURPOSE: Tracks the messages sent to and from patients and care providers
 *          for the problem messaging feature.
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
package com.example.meditrackr.adapters;

//imports
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.meditrackr.R;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.Comment;
import com.example.meditrackr.models.CommentList;


// got this from https://github.com/smilefam/SendBird-Android
// Shows a patient's and care provider's messages in a recycler view
public class MessageListAdapter extends RecyclerView.Adapter {
    // Enumeration values for 2 states of message
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    // Class objects
    private Context mContext;
    private CommentList comments;

    // Constructor
    public MessageListAdapter(Context context, CommentList messageList) {
        mContext = context;
        comments = messageList;
    }

    // Returns the number of messages currently in RecyclerView
    @Override
    public int getItemCount() {
        return comments.getSize();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        // Get message
        Comment comment = (Comment) comments.getComment(position);

        if (comment.getUsername().equals(LazyLoadingManager.getProfile().getUsername())){
            // If the sender of the message is the current user
            Log.d("Messaging", "We are the current user logged in : "
                    + LazyLoadingManager.getProfile().getUsername());
            return VIEW_TYPE_MESSAGE_SENT; // Return 1 stating that message was sent
        } else {
            // If some other user sent the message
            Log.d("Messaging", "We are not the current user logged in : "
                    +comment.getUsername());
            return VIEW_TYPE_MESSAGE_RECEIVED; // Return 2 stating that the message was received
        }
    }

    // Inflates the appropriate layout according to the ViewType.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view); // Returns a view for the message sender
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view); // Returns a view for message receiver
        }
        return null;
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Get message
        Comment comment = (Comment) comments.getComment(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(comment);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(comment);
        }
    }

    // UI for message sender
    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        SentMessageHolder(View itemView) { // Message sender view
            super(itemView);

            // Each message sent will contain a text message and time stamp
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }

        // Set message and time stamp
        void bind(Comment comment) {
            messageText.setText(comment.getComment());

            // Format the stored timestamp into a readable String using method.
            timeText.setText(comment.getDate());
        }
    }

    // UI for message receiver
    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        ImageView profileImage;

        ReceivedMessageHolder(View itemView) { // Message receiver view
            super(itemView);

            // Each message received will contain a text messages, time stamp, name, and profile image
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
            profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
        }

        // Set the text message, time stamp, name, and profile image
        void bind(Comment comment) {
            messageText.setText(comment.getComment());

            // Format the stored timestamp into a readable String using method.
            timeText.setText(comment.getDate());

            nameText.setText(comment.getUsername());

            // Insert the profile image from the URL into the ImageView.
            //Utils.displayRoundImageFromUrl(mContext, comment.getUsername(), profileImage);
        }
    }
}
