package com.qq.crazypic.bean;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.qq.crazypic.utilities.CollectionUtil;
import com.qq.crazypic.utilities.DateUtil;
import com.qq.crazypic.utilities.ResponseTextUtil;

import java.util.Date;
import java.util.List;

@Entity
public class Post {

    @PrimaryKey
    private long id;

    private long dateGmt;

    private String title;

    private List<String> contentUrls;

    private List<Integer> categories;

    private List<Integer> tags;

    public Post() {
    }

    @SuppressLint("SimpleDateFormat")
    public Post(PostDTO postDTO) {
        parseFromDTO(postDTO);
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

    public List<String> getContentUrls() {
        return contentUrls;
    }

    public void setContentUrls(List<String> contentUrls) {
        this.contentUrls = contentUrls;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public void parseFromDTO(PostDTO postDTO) {
        if (postDTO == null) {
            return;
        }
        this.id = postDTO.getId();
        Date date = DateUtil.parse(null, postDTO.getDateGmt());
        this.dateGmt = date == null ? 900763199L : date.getTime();
        this.title = postDTO.getTitle() == null ? null : ResponseTextUtil.unescapeHtmlText(postDTO.getTitle().getRendered());
        this.contentUrls = postDTO.getContent() == null ? null : ResponseTextUtil.parseImgUrl(postDTO.getContent().getRendered());
        this.categories = postDTO.getCategories();
        this.tags = postDTO.getTags();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        Post that = obj instanceof Post ? ((Post) obj) : null;
        if (that == null) {
            return false;
        }
        if (that.id != this.id) {
            return false;
        }
        if (that.dateGmt != this.dateGmt) {
            return false;
        }
        if (TextUtils.equals(that.title, this.title)) {
            return false;
        }
        return CollectionUtil.elementsEquals(that.contentUrls, this.contentUrls);
    }
}