package com.epicodus.backtalkr.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.backtalkr.BackTalkrApplication;
import com.epicodus.backtalkr.R;
import com.epicodus.backtalkr.models.Category;
import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCategoryFragment extends DialogFragment implements View.OnClickListener {
    private Firebase mFirebaseRef;
    private String categoryId;
    @Bind(R.id.nameEditText) EditText mNameEditText;
    @Bind(R.id.addNewCategoryButton) Button mAddNewCategoryButton;
    private Context mContext;

    public AddCategoryFragment() {
        // Required empty public constructor
    }

    public static AddCategoryFragment newInstance() {
        return new AddCategoryFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);
        ButterKnife.bind(this, view);

        mFirebaseRef = BackTalkrApplication.getAppInstance().getFirebaseRef().child("categories/").push();

        mAddNewCategoryButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mAddNewCategoryButton) {
            String name = mNameEditText.getText().toString();
            createCategory(name);
            dismiss();
        }
    }

    private void createCategory(String name) {
        Category category = new Category(name, mFirebaseRef.getKey().toString());
        mFirebaseRef.setValue(category);
    }

}
