package com.qq.crazypic.repositories;

import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.qq.crazypic.api.MainService;
import com.qq.crazypic.bean.PostDetail;
import com.qq.crazypic.data.PostPagingSource;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class PostRepository {

    private static final int PostCountPerPage = 20;

    @Inject
    MainService mainService;

    @Inject
    public PostRepository() {
    }

    public Flowable<PagingData<PostDetail>> getPostPageData() {
        return PagingRx.getFlowable(
                new Pager<>(
                        new PagingConfig(PostCountPerPage),
                        () -> new PostPagingSource(mainService)
                )
        ).onErrorReturn(throwable -> {
            throwable.printStackTrace();
            return PagingData.empty();
        });
    }
}