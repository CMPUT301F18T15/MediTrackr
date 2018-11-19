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

/**
*This class displays all messages that the user has.
*This class uses getItemViewType to tell the user whether the messages they see were sent by them or recived.
*This class uses onBindViewHolder just turns the message we have as a model into an adapter for the user to see.
*This class uses SentMessageHolder to take the model of messages that the user sent and display them as an adapter. The adapter makes it clear that these messages were sent by the user and not recived. It also shows the time it was sent along with the message.
*This class uses ReceivedMessageHolder to take the model of messages that the user has gotten from others and display them as an adapter. The adapter makes it clear that these messages were received by the user and not sent. It also shows the time it was received along with the message.
*@author Orest Cokan
*@version 1.0 Nov 12, 2018
*/

// got this from https://github.com/smilefam/SendBird-Android

// Class shows a patient's and care provider's messages in a recycler view
public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private Context mContext;
    private CommentList comments;

    /**
     * creates variables for the class to use
     * @author Orest Cokan
     * @version 1.0 Nov 12, 2018
     * @param context           the context for the adapter
     * @param messageList       a list of messages
     */
    public MessageListAdapter(Context context, CommentList messageList) {
        mContext = context;
        comments = messageList;
    }

    @Override
    public int getItemCount() {
        return comments.getSize();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        Comment comment = (Comment) comments.getComment(position);

        if (comment.getUsername().equals(LazyLoadingManager.getProfile().getUsername())){
            // If the current user is the sender of the message
            Log.d("Messaging", "We are the current user logged in : " + LazyLoadingManager.getProfile().getUsername());
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            Log.d("Messaging", "We are not the current user logged in : " +comment.getUsername());
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    // Inflates the appropriate layout according to the ViewType.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Comment comment = (Comment) comments.getComment(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(comment);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(comment);
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }

        void bind(Comment comment) {
            messageText.setText(comment.getComment());

            // Format the stored timestamp into a readable String using method.
            timeText.setText(comment.getDate());
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
            profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
        }

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
