package com.qq.crazypic.bean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PostContent {

    @PrimaryKey
    private long id;

    private String imgUrl;

    private long postInfoId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getPostInfoId() {
        return postInfoId;
    }

    public void setPostInfoId(long postInfoId) {
        this.postInfoId = postInfoId;
    }
}