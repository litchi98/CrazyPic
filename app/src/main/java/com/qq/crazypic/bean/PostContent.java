package com.qq.crazypic.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.qq.crazypic.utilities.ResponseTextUtil;

import java.util.List;

@Entity(tableName = "post_content")
public class PostContent {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "content_id")
    private long contentId;

    @ColumnInfo(name = "content")
    private List<String> content;

    @ColumnInfo(name = "post_info_id")
    private long postInfoId;

    public PostContent() {
    }

    @Ignore
    public PostContent(PostDTO.ContentDTO content) {
        if (content == null) {
            return;
        }
        this.content = ResponseTextUtil.parseImgUrl(content.getRendered());
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public long getPostInfoId() {
        return postInfoId;
    }

    public void setPostInfoId(long postInfoId) {
        this.postInfoId = postInfoId;
    }
}