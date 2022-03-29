package com.qq.crazypic.bean;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "post_category")
public class PostCategory {
    @PrimaryKey
    @ColumnInfo(name = "category_id")
    private long categoryId;

    @ColumnInfo(name = "category_name")
    @Nullable
    private String categoryName;

    public PostCategory() {
    }

    @Ignore
    public PostCategory(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}