package com.qq.crazypic.api;

import com.qq.crazypic.bean.PostDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainService {
    @GET("wp-json/wp/v2/posts")
    Observable<List<PostDTO>> getPosts(@Query("filter[posts_per_page]") int perPage, @Query("page") int page);
}