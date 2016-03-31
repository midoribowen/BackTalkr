package com.epicodus.backtalkr.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.backtalkr.BackTalkrApplication;
import com.epicodus.backtalkr.R;
import com.epicodus.backtalkr.models.Message;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AddMessageFragment extends DialogFragment implements View.OnClickListener {
    private Firebase mFirebaseRef;
    @Bind(R.id.contentEditText) EditText mContentEditText;
    @Bind(R.id.addNewMessageButton) Button mAddMessageButton;
    private String mCurrentUserId;
    //put binders here!


    public AddMessageFragment() {
        // Required empty public constructor
    }

    public static AddMessageFragment newInstance() {
        return new AddMessageFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_message, container, false);
        ButterKnife.bind(this, view);


        mFirebaseRef = BackTalkrApplication.getAppInstance().getFirebaseRef();
        Log.d("Current User ID:", mFirebaseRef.getAuth().getUid().toString());

        mAddMessageButton.setOnClickListener(this);
        mCurrentUserId = mFirebaseRef.getAuth().getUid();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
    }

    @Override
    public void onClick(View v) {
        if (v == mAddMessageButton) {
            String content = mContentEditText.getText().toString();
            createMessage(content);
            dismiss();
        }

    }

    private void createMessage(String content) {
        Message message = new Message(content);
        message.setUserId(mFirebaseRef.getAuth().getUid());

        mFirebaseRef.child("messages/")
                .child(mCurrentUserId.toString())
                .push()
                .setValue(message);

//        Firebase userRef = mFirebaseRef.child(mCurrentUserId.toString());
//        Firebase messageRef = userRef.child("messages/").push();
//        messageRef.setValue(message);
        mContentEditText.setText("");
    }

}
