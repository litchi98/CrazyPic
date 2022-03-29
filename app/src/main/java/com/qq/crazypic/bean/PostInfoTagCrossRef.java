package com.qq.crazypic.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"tag_id", "rowid"})
public class PostInfoTagCrossRef {
    private long tagId;

    private long postId;

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
}