package com.qq.crazypic;

import static autodispose2.AutoDispose.autoDisposable;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.qq.crazypic.adapter.PostAdapter;
import com.qq.crazypic.databinding.ActivityMainBinding;
import com.qq.crazypic.utilities.CLog;
import com.qq.crazypic.viewmodels.MainViewModel;

import javax.inject.Inject;

import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding activityMainBinding;

    private MainViewModel mainViewModel;

    @Inject
    PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initView();
        loadData();
    }

    private void initView() {
        activityMainBinding.postList.setAdapter(postAdapter);
        activityMainBinding.postList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadData() {
        mainViewModel.getPostPagingData()
                .doOnCancel(() -> CLog.d(TAG, "onCancel"))
                .to(autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(
                        postPagingData -> postAdapter.submitData(getLifecycle(), postPagingData),
                        Throwable::printStackTrace,
                        () -> CLog.d(TAG, "onComplete.")
                );
    }
}