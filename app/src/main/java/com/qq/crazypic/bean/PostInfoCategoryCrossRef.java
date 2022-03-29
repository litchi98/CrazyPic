package com.qq.crazypic.bean;

import androidx.room.Entity;

@Entity(primaryKeys = {"category_id", "rowid"})
public class PostInfoCategoryCrossRef {
    private long postId;

    private long categoryId;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}