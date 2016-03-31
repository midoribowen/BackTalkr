package com.epicodus.backtalkr.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 3/31/16.
 */
@Parcel
public class Category {
    String name;
    String categoryId;

    public Category() {
    }

    public Category(String name, String categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }


    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }
}
