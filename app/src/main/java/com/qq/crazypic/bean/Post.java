package com.qq.crazypic.bean;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {

    @SerializedName("id")
    private Long id;

    @SerializedName("date")
    private String date;

    @SerializedName("date_gmt")
    private String dateGmt;

    @SerializedName("title")
    private TitleDTO title;

    @SerializedName("content")
    private ContentDTO content;

    @SerializedName("categories")
    private List<Integer> categories;

    @SerializedName("tags")
    private List<String> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateGmt() {
        return dateGmt;
    }

    public void setDateGmt(String dateGmt) {
        this.dateGmt = dateGmt;
    }

    public TitleDTO getTitle() {
        return title;
    }

    public void setTitle(TitleDTO title) {
        this.title = title;
    }

    public ContentDTO getContent() {
        return content;
    }

    public void setContent(ContentDTO content) {
        this.content = content;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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
        // TODO: 2022/3/22 判断this和that值是否相等
        return true;
    }

    public static class TitleDTO {
        @SerializedName("rendered")
        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }
    }

    public static class ContentDTO {
        @SerializedName("rendered")
        private String rendered;

        @SerializedName("protected")
        private Boolean protectedX;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }

        public Boolean getProtectedX() {
            return protectedX;
        }

        public void setProtectedX(Boolean protectedX) {
            this.protectedX = protectedX;
        }
    }
}