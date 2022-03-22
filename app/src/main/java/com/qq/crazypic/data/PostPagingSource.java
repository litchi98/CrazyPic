package com.qq.crazypic.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.qq.crazypic.api.MainService;
import com.qq.crazypic.bean.Post;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostPagingSource extends RxPagingSource<Integer, Post> {

    private static final int POST_STARTING_PAGE_INDEX = 1;

    private final MainService mainService;

    public PostPagingSource(MainService mainService) {
        this.mainService = mainService;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Post> pagingState) {
        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, Post>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        Integer page = loadParams.getKey();
        if (page == null) {
            page = POST_STARTING_PAGE_INDEX;
        }
        int loadSize = loadParams.getLoadSize();
        final int finalPage = page;
        return mainService
                .getPosts(loadSize, page)
                .singleOrError()
                .subscribeOn(Schedulers.io())
                .map(posts -> getLoadResult(posts, finalPage))
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer, Post> getLoadResult(@NonNull List<Post> posts, int page) {
        Integer prevPage = page == POST_STARTING_PAGE_INDEX ? null : (page - 1);
        Integer nextPage = posts.isEmpty() ? null : (page + 1);
        return new LoadResult.Page<>(posts, prevPage, nextPage);
    }
}
