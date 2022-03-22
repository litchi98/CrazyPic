package com.qq.crazypic.di;

import android.app.Application;

import com.qq.crazypic.BuildConfig;
import com.qq.crazypic.api.MainService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@InstallIn(SingletonComponent.class)
@Module
public class NetworkModule {

    private static final long MaxHttpCacheSize = 50L * 1024L * 1024L; // 50 MiB;

    @Singleton
    @Provides
    public static OkHttpClient provideOkHttpClient(Application application) {
        return new OkHttpClient.Builder()
                .cache(new Cache(application.getCacheDir(), MaxHttpCacheSize))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(
                        BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();
    }

    @Singleton
    @Provides
    public static MainService provideMainService(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
                .baseUrl("https://bgdmfcb.com/")
                .build()
                .create(MainService.class);
    }
}