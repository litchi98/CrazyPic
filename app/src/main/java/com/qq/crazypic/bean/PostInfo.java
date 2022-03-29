package com.qq.crazypic.bean;

import android.text.TextUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.qq.crazypic.utilities.DateUtil;
import com.qq.crazypic.utilities.ResponseTextUtil;

import java.util.Date;

@Fts4
@Entity(tableName = "post_info")
public class PostInfo {
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    private long id;

    @ColumnInfo(name = "date_gmt")
    private long dateGmt;

    private String title;

    public PostInfo() {
    }

    @Ignore
    public PostInfo(PostDTO postDTO) {
        if (postDTO == null) {
            return;
        }
        this.id = postDTO.getId();
        Date date = DateUtil.parse(null, postDTO.getDateGmt());
        this.dateGmt = date == null ? 900763199L : date.getTime();
        this.title = postDTO.getTitle() == null ? null : ResponseTextUtil.unescapeHtmlText(postDTO.getTitle().getRendered());
    }

    public static boolean equals(PostInfo a, PostInfo b) {
        if (a == b) {
            return true;
        }
        if (a != null && b != null) {
            if (a.getId() != b.getId()) {
                return false;
            }
            if (a.getDateGmt() != b.getDateGmt()) {
                return false;
            }
            if (!TextUtils.equals(a.getTitle(), b.getTitle())) {
                return false;
            }
        }
        return false;
    }

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