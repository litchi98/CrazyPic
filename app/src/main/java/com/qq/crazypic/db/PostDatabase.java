package com.qq.crazypic.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.qq.crazypic.bean.PostCategory;
import com.qq.crazypic.bean.PostContent;
import com.qq.crazypic.bean.PostInfo;
import com.qq.crazypic.bean.PostTag;
import com.qq.crazypic.db.converter.Converter;
import com.qq.crazypic.db.dao.PostDetailDao;

@Database(entities = {PostInfo.class, PostContent.class, PostCategory.class, PostTag.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class PostDatabase extends RoomDatabase {
    public abstract PostDetailDao postDao();
}