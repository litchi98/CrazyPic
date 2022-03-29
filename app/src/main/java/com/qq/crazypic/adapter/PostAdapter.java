package com.qq.crazypic.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qq.crazypic.bean.PostDetail;
import com.qq.crazypic.databinding.ListItemPostBinding;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ActivityRetainedScoped;

@ActivityRetainedScoped
public class PostAdapter extends PagingDataAdapter<PostDetail, PostAdapter.PostViewHolder> {

    @Inject
    public PostAdapter() {
        super(new DiffUtil.ItemCallback<PostDetail>() {
            @Override
            public boolean areItemsTheSame(@NonNull PostDetail oldItem, @NonNull PostDetail newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull PostDetail oldItem, @NonNull PostDetail newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(ListItemPostBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostDetail postDetail = getItem(position);
        if (postDetail != null) {
            holder.bind(postDetail, position);
        }
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        private final ListItemPostBinding binding;

        public PostViewHolder(ListItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(@NonNull PostDetail postDetail, int position) {
            binding.postTitle.setText(postDetail.getTitle());
            binding.postIndex.setText(String.valueOf(position));
        }
    }
}