package com.qq.crazypic.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qq.crazypic.bean.Post;
import com.qq.crazypic.databinding.ListItemPostBinding;
import com.qq.crazypic.utilities.ResponseTextUtil;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ActivityRetainedScoped;

@ActivityRetainedScoped
public class PostAdapter extends PagingDataAdapter<Post, PostAdapter.PostViewHolder> {

    @Inject
    public PostAdapter() {
        super(new DiffUtil.ItemCallback<Post>() {
            @Override
            public boolean areItemsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
                return oldItem.getId().longValue() == newItem.getId().longValue();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
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
        Post post = getItem(position);
        if (post != null) {
            holder.bind(post);
        }
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        private final ListItemPostBinding binding;

        public PostViewHolder(ListItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(@NonNull Post post) {
            Post.TitleDTO titleDTO = post.getTitle();
            String title = titleDTO == null ? "" : ResponseTextUtil.unescapeHtmlText(titleDTO.getRendered());
            binding.postTitle.setText(title);
        }
    }
}