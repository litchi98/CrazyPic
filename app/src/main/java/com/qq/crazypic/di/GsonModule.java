package com.qq.crazypic.di;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class GsonModule {

    @Provides
    @Singleton
    public static Gson provideGson() {
        return new Gson();
    }
}