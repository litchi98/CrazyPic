package com.qq.crazypic.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.qq.crazypic.bean.PostDetail;
import com.qq.crazypic.db.dao.PostDao;

@Database(entities = {PostDetail.class}, version = 1)
public abstract class PostDatabase extends RoomDatabase {
    public abstract PostDao postDao();
}