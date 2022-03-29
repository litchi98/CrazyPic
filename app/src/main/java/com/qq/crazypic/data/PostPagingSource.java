package com.qq.crazypic.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.qq.crazypic.api.MainService;
import com.qq.crazypic.bean.PostDetail;
import com.qq.crazypic.bean.PostDTO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostPagingSource extends RxPagingSource<Integer, PostDetail> {

    private static final int POST_STARTING_PAGE_INDEX = 1;

    private final MainService mainService;

    public PostPagingSource(MainService mainService) {
        this.mainService = mainService;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, PostDetail> pagingState) {
        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }
        LoadResult.Page<Integer, PostDetail> anchorPage = pagingState.closestPageToPosition(anchorPosition);
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
    public Single<LoadResult<Integer, PostDetail>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
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
                    ArrayList<PostDetail> postDetails = new ArrayList<>();
                    for (PostDTO postDTO : postDTOS) {
                        if (postDTO == null) {
                            continue;
                        }
                        postDetails.add(new PostDetail(postDTO));
                    }
                    return postDetails;
                })
                .map(posts -> getLoadResult(posts, finalPage))
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer, PostDetail> getLoadResult(@NonNull List<PostDetail> postDetails, int page) {
        Integer prevPage = page == POST_STARTING_PAGE_INDEX ? null : (page - 1);
        Integer nextPage = postDetails.isEmpty() ? null : (page + 1);
        return new LoadResult.Page<>(postDetails, prevPage, nextPage);
    }
}
