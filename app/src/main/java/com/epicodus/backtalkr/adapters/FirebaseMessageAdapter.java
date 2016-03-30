package com.epicodus.backtalkr.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.backtalkr.R;
import com.epicodus.backtalkr.models.Message;
import com.epicodus.backtalkr.util.FirebaseRecyclerAdapter;
import com.firebase.client.Query;


public class FirebaseMessageAdapter extends FirebaseRecyclerAdapter<MessageViewHolder, Message>{

    public FirebaseMessageAdapter(Query query, Class<Message> itemClass) {
        super(query, itemClass);
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_list_item, parent, false);
        return new MessageViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.bindMessage(getItem(position));
    }

    @Override
    protected void itemAdded(Message item, String key, int position) {

    }

    @Override
    protected void itemChanged(Message oldItem, Message newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Message item, String key, int position) {

    }

    @Override
    protected void itemMoved(Message item, String key, int oldPosition, int newPosition) {

    }

}
