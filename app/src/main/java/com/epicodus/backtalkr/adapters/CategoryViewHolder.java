package com.epicodus.backtalkr.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.backtalkr.BackTalkrApplication;
import com.epicodus.backtalkr.R;
import com.epicodus.backtalkr.models.Category;
import com.epicodus.backtalkr.ui.CategoryActivity;
import com.firebase.client.Firebase;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.categoryNameTextView) TextView mCategoryNameTextView;
    private Context mContext;
    private ArrayList<Category> mCategories = new ArrayList<>();
    private Firebase mFirebaseRef;

    public CategoryViewHolder(View itemView, ArrayList<Category> categories) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);

        mCategories = categories;
        mFirebaseRef = BackTalkrApplication.getAppInstance().getFirebaseRef();

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CategoryActivity.class);
                intent.putExtra("chosenCategory", Parcels.wrap(mCategories.get(getLayoutPosition())));
                mContext.startActivity(intent);
            }
        });

    }

    public void bindCategory(Category category) {
        mCategoryNameTextView.setText(category.getName());
    }
}
