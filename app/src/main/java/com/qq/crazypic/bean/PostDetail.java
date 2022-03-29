package com.qq.crazypic.bean;

import androidx.annotation.Nullable;
import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class PostDetail {

    @Embedded
    private PostInfo postInfo;

    @Relation(
            parentColumn = "rowid",
            entityColumn = "post_info_id"
    )
    private PostContent content;

    @Relation(
            parentColumn = "rowid",
            entityColumn = "category_id",
            associateBy = @Junction(PostInfoCategoryCrossRef.class)
    )
    private List<PostCategory> categories;

    @Relation(
            parentColumn = "rowid",
            entityColumn = "tag_id",
            associateBy = @Junction(PostInfoTagCrossRef.class)
    )
    private List<PostTag> tags;

    public PostDetail() {
    }

    public PostDetail(PostDTO postDTO) {
        if (postDTO == null) {
            return;
        }
        this.postInfo = new PostInfo(postDTO);
        this.content = new PostContent(postDTO.getContent());
        if (postDTO.getCategories() != null && postDTO.getCategories().size() > 0) {
            this.categories = new ArrayList<>();
            for (Long categoryId : postDTO.getCategories()) {
                this.categories.add(new PostCategory(categoryId));
            }
        }
        if (postDTO.getTags() != null && postDTO.getTags().size() > 0) {
            this.tags = new ArrayList<>();
            for (Long tagId : postDTO.getTags()) {
                this.tags.add(new PostTag(tagId));
            }
        }
    }

    public PostInfo getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(PostInfo postInfo) {
        this.postInfo = postInfo;
    }

    public PostContent getContent() {
        return content;
    }

    public void setContent(PostContent content) {
        this.content = content;
    }

    public List<PostCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<PostCategory> categories) {
        this.categories = categories;
    }

    public List<PostTag> getTags() {
        return tags;
    }

    public void setTags(List<PostTag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        PostDetail that = obj instanceof PostDetail ? ((PostDetail) obj) : null;
        if (that == null) {
            return false;
        }
        if (!PostInfo.equals(that.postInfo, this.postInfo)) {
            return false;
        }
        if (that.content != null && this.content != null) {
            return that.content.hashCode() == this.content.hashCode();
        }
        return false;
    }
}