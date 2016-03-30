package com.epicodus.backtalkr.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.backtalkr.R;
import com.firebase.client.Firebase;


public class AddMessageFragment extends DialogFragment implements View.OnClickListener {
    private Firebase mFirebaseRef;
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
        //Bind here
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
    }

    @Override
    public void onClick(View v) {

    }
}
