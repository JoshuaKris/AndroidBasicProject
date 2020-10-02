package com.example.androidbasicproject.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidbasicproject.R;
import com.example.androidbasicproject.database.UserEntity;
import com.example.androidbasicproject.databinding.RecyclerviewFavoritesItemBinding;

import java.util.List;

public class FavoriteListViewAdapter extends RecyclerView.Adapter<FavoriteListViewAdapter.ViewHolder> {

    private final List<UserEntity> itemList;
    private final ItemOnClickListener onClickListener;

    public FavoriteListViewAdapter(List<UserEntity> itemList, ItemOnClickListener onClickListener) {
        this.itemList = itemList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerviewFavoritesItemBinding mBinding;
        mBinding = RecyclerviewFavoritesItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        View view = mBinding.getRoot();
        return new ViewHolder(view, mBinding, onClickListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bindData(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return Math.max(itemList.size(), 0);
    }

    public interface ItemOnClickListener {
        void onClick(int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemOnClickListener onClickListener;
        private RecyclerviewFavoritesItemBinding mBinding;

        ViewHolder(View view, RecyclerviewFavoritesItemBinding mBinding, ItemOnClickListener onClickListener) {
            super(view);
            this.mBinding = mBinding;
            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);
        }

        void bindData(UserEntity user) {
            mBinding.itemUsername.setText(user.getName());
            mBinding.tvItemFollowing.setText(String.format(itemView.getContext().getString(R.string.detail_following), user.getFollowing()));
            mBinding.tvItemFollower.setText(String.format(itemView.getContext().getString(R.string.detail_follower), user.getFollowers()));
            mBinding.tvItemRepository.setText(String.format(itemView.getContext().getString(R.string.detail_repository), user.getPublicRepos()));
            mBinding.tvItemLocation.setText(String.format(itemView.getContext().getString(R.string.detail_location), user.getLocation()));
            Glide.with(itemView.getContext()).load(user.getAvatarUrl()).placeholder(R.drawable.ic_account_box_black_24dp).into(mBinding.ivItemImage);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(getAdapterPosition());
        }
    }
}
