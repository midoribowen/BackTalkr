package com.epicodus.backtalkr.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.epicodus.backtalkr.BackTalkrApplication;
import com.epicodus.backtalkr.R;
import com.epicodus.backtalkr.adapters.FirebaseMessageAdapter;
import com.epicodus.backtalkr.models.Category;
import com.epicodus.backtalkr.models.Message;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    private Query mQuery;
    private Firebase mFirebaseRef;
    private FirebaseMessageAdapter mAdapter;
    private String mCurrentUserId;
    private Category category;

    @Bind(R.id.messageRecyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.addMessageButton) FloatingActionButton mAddMessageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        category = Parcels.unwrap(bundle.getParcelable("chosenCategory"));
        mFirebaseRef = BackTalkrApplication.getAppInstance().getFirebaseRef().child("categories/" + category.getCategoryId());

        checkForAuthenticatedUser();
        setupFirebaseQuery();
        setUpRecyclerView();



        Log.d("Category:", category.getName());

        mAddMessageButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addMessageButton:
                routeUnauthenticatedUserFromNewMessage();
                launchAddMessageFragment();
                break;
        }
    }

    private void launchAddMessageFragment() {
        FragmentManager fm = getSupportFragmentManager();
        AddMessageFragment addMessage = AddMessageFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("categoryId", category.getCategoryId());
        addMessage.setArguments(bundle);
        addMessage.show(fm, "fragment_add_message");
    }

    private void setupFirebaseQuery() {
        Firebase.setAndroidContext(this);
//        set up location so that its route is flat vs. nested -- ("messages/" + category.getCategoryId()).toString();
        String location = mFirebaseRef.child("/messages").toString();
        mQuery = new Firebase(location);
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseMessageAdapter(mQuery, Message.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void checkForAuthenticatedUser() {
        AuthData authData = mFirebaseRef.getAuth();
        if (authData == null) {
            Toast.makeText(CategoryActivity.this, "You're not logged in!", Toast.LENGTH_SHORT).show();
        }
    }

    private void routeUnauthenticatedUserFromNewMessage() {
        AuthData authData = mFirebaseRef.getAuth();
        if (authData == null) {
            goToLoginActivity();
        } else {
            mCurrentUserId = mFirebaseRef.getAuth().getUid();
        }
    }

    private void goToLoginActivity() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                this.logout();
                return true;
            case R.id.action_login:
                Intent loginIntent = new Intent(CategoryActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        mFirebaseRef.unauth();
    }
}