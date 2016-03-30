package com.epicodus.backtalkr.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.backtalkr.BackTalkrApplication;
import com.epicodus.backtalkr.R;
import com.epicodus.backtalkr.models.Message;
import com.firebase.client.Firebase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 3/30/16.
 */
public class MessageViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.messageTextView)
    TextView mMessageTextView;
    @Bind(R.id.authorTextView) TextView mAuthorTextView;
    private Context mContext;
    private ArrayList<Message> mMessages = new ArrayList<>();
    private Firebase mFirebaseRef;

    public MessageViewHolder(View itemView, ArrayList<Message> messages) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
        mMessages = messages;
        mFirebaseRef = BackTalkrApplication.getAppInstance().getFirebaseRef();

    }

    public void bindMessage(Message message) {
        mMessageTextView.setText(message.getContent());
    }
}
