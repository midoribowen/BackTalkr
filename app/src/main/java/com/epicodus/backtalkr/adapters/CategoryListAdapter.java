package com.epicodus.backtalkr.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.backtalkr.R;
import com.epicodus.backtalkr.models.Category;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private ArrayList<Category> mCategories = new ArrayList<>();
    private Context mContext;

    public CategoryListAdapter(Context context, ArrayList<Category> categories) {
        mContext = context;
        mCategories = categories;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_item, parent, false);
        CategoryViewHolder viewHolder = new CategoryViewHolder(view, mCategories);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.bindCategory(mCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

}
