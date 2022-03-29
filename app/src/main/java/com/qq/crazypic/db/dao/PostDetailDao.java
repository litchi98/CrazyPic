package com.qq.crazypic.db.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.qq.crazypic.bean.PostDetail;

import java.util.List;

@Dao
public interface PostDetailDao {
    @Transaction
    @Query("SELECT * FROM post_info")
    List<PostDetail> getPostDetailList();
}