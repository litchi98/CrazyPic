package com.qq.crazypic.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.qq.crazypic.bean.PostDetail;
import com.qq.crazypic.repositories.PostRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    @Inject
    PostRepository postRepository;

    @Inject
    public MainViewModel() {
    }

    public Flowable<PagingData<PostDetail>> getPostPagingData() {
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Flowable<PagingData<PostDetail>> postPageData = postRepository.getPostPageData();
        PagingRx.cachedIn(postPageData, viewModelScope);
        return postPageData;
    }
}