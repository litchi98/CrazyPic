package com.qq.crazypic.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostDTO {

    @SerializedName("id")
    private long id;

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
    private List<Integer> tags;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", dateGmt='" + dateGmt + '\'' +
                ", title=" + title +
                ", content=" + content +
                ", categories=" + categories +
                ", tags=" + tags +
                '}';
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

        @Override
        public String toString() {
            return "TitleDTO{" +
                    "rendered='" + rendered + '\'' +
                    '}';
        }
    }

    public static class ContentDTO {
        @SerializedName("rendered")
        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }
    }
}