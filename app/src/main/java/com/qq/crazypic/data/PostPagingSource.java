package com.qq.crazypic.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.qq.crazypic.api.MainService;
import com.qq.crazypic.bean.Post;
import com.qq.crazypic.bean.PostDTO;

import java.util.ArrayList;
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
        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }
        LoadResult.Page<Integer, Post> anchorPage = pagingState.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey + 1;
        }

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey - 1;
        }
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
                .map(postDTOS -> {
                    ArrayList<Post> posts = new ArrayList<>();
                    for (PostDTO postDTO : postDTOS) {
                        if (postDTO == null) {
                            continue;
                        }
                        posts.add(new Post(postDTO));
                    }
                    return posts;
                })
                .map(posts -> getLoadResult(posts, finalPage))
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer, Post> getLoadResult(@NonNull List<Post> posts, int page) {
        Integer prevPage = page == POST_STARTING_PAGE_INDEX ? null : (page - 1);
        Integer nextPage = posts.isEmpty() ? null : (page + 1);
        return new LoadResult.Page<>(posts, prevPage, nextPage);
    }
}
