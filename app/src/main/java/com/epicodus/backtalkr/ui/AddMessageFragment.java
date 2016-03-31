package com.epicodus.backtalkr.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;

import com.epicodus.backtalkr.BackTalkrApplication;
import com.epicodus.backtalkr.R;
import com.epicodus.backtalkr.models.Message;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AddMessageFragment extends DialogFragment implements View.OnClickListener {
    private Firebase mFirebaseRef;
    private String messageId;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mUsername;
    @Bind(R.id.contentEditText) EditText mContentEditText;
    @Bind(R.id.addNewMessageButton) Button mAddMessageButton;
    @Bind(R.id.usernameEditText) EditText mUsernameEditText;
    @Bind(R.id.usernameLabel) TextView mUsernameLabel;
    private Context mContext;


    public AddMessageFragment() {
        // Required empty public constructor
    }

    public static AddMessageFragment newInstance() {
        return new AddMessageFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_message, container, false);
        ButterKnife.bind(this, view);

        mFirebaseRef = BackTalkrApplication.getAppInstance().getFirebaseRef().child("messages/").push();

//        Bundle bundle = getArguments();
//        messageId = bundle.getString("messageId");


        mSharedPreferences = this.getContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        mUsername = mSharedPreferences.getString("username", null);

        if (mUsername != null) {
            mUsernameLabel.setVisibility(View.GONE);
            mUsernameEditText.setVisibility(View.GONE);
        }

        mAddMessageButton.setOnClickListener(this);
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
            String username;
            if (mUsername != null) {
                username = mUsername;
            } else {
                username = mUsernameEditText.getText().toString();
                addToSharedPreferences(username);
            }
            createMessage(content, username);
            dismiss();

        }

    }

    private void createMessage(String content, String username) {
        Message message = new Message(mFirebaseRef.getKey().toString(), content, username);
        mFirebaseRef.setValue(message);
    }

    private void addToSharedPreferences(String username) {
        mEditor.putString("username", username).commit();
    }

}
