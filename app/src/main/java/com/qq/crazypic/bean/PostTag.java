package com.qq.crazypic.bean;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "post_tag")
public class PostTag {

    @PrimaryKey
    @ColumnInfo(name = "tag_id")
    private long tagId;

    @Nullable
    @ColumnInfo(name = "tag_name")
    private String tagName;

    public PostTag() {
    }

    @Ignore
    public PostTag(long tagId) {
        this.tagId = tagId;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
