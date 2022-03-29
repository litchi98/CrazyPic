package com.qq.crazypic.di;

import android.content.Context;

import androidx.room.Room;

import com.qq.crazypic.db.PostDatabase;
import com.qq.crazypic.db.dao.PostDetailDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class DatabaseModule {

    private static final String POST_DATABASE_NAME = "db_post";

    @Singleton
    @Provides
    public static PostDatabase providePostDatabase(Context context) {
        return Room
                .databaseBuilder(context, PostDatabase.class, POST_DATABASE_NAME)
                .build();
    }

    @Provides
    public static PostDetailDao providePostDao(PostDatabase postDatabase) {
        return postDatabase.postDao();
    }
}