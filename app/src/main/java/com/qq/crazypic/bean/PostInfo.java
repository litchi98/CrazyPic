package com.qq.crazypic.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

@Fts4
@Entity(tableName = "post_info")
public class PostInfo {
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    private long id;

    @ColumnInfo(name = "date_gmt")
    private long dateGmt;

    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDateGmt() {
        return dateGmt;
    }

    public void setDateGmt(long dateGmt) {
        this.dateGmt = dateGmt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}