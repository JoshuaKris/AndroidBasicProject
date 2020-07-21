package com.example.androidbasicproject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.androidbasicproject.databinding.RecyclerviewItemBinding;
import com.example.androidbasicproject.model.GithubUsers;
import com.example.androidbasicproject.model.Users;

public class ItemListViewAdapter extends RecyclerView.Adapter<ItemListViewAdapter.ViewHolder> {

    private final GithubUsers itemList;
    private final ItemOnClickListener onClickListener;
    private final Context context;

    ItemListViewAdapter(GithubUsers itemList, ItemOnClickListener onClickListener, Context context) {
        this.itemList = itemList;
        this.onClickListener = onClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerviewItemBinding mBinding;
        mBinding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        View view = mBinding.getRoot();
        return new ViewHolder(view, mBinding, onClickListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bindData(itemList.getUsers().get(position),context);
    }

    @Override
    public int getItemCount() {
        return Math.max(itemList.getUsers().size(), 0);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemOnClickListener onClickListener;
        private RecyclerviewItemBinding mBinding;

        ViewHolder(View view, RecyclerviewItemBinding mBinding, ItemOnClickListener onClickListener) {
            super(view);
            this.mBinding = mBinding;
            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);
        }

        void bindData(Users user, Context context) {
            mBinding.tvItemCompany.setText(user.getCompany());
            mBinding.itemUsername.setText(user.getUsername());
            mBinding.itemName.setText(user.getName());
            int id = itemView.getContext().getResources().getIdentifier(user.getAvatar(), "drawable", context.getPackageName());
            Glide.with(itemView.getContext()).load(id).placeholder(R.drawable.ic_account_box_black_24dp).circleCrop().into(mBinding.ivItemImage);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(getAdapterPosition());
        }
    }

    interface ItemOnClickListener{
        void onClick(int position);
    }
}
