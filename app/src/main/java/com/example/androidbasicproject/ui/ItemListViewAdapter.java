package com.example.androidbasicproject.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidbasicproject.R;
import com.example.androidbasicproject.databinding.RecyclerviewItemBinding;
import com.example.androidbasicproject.model.GithubModel.Items;

import java.util.List;

public class ItemListViewAdapter extends RecyclerView.Adapter<ItemListViewAdapter.ViewHolder> {

    private final List<Items> itemList;
    private final ItemOnClickListener onClickListener;
    private final Context context;

    public ItemListViewAdapter(List<Items> itemList, ItemOnClickListener onClickListener, Context context) {
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
        holder.bindData(itemList.get(position), context);
    }

    @Override
    public int getItemCount() {
        return Math.max(itemList.size(), 0);
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

        void bindData(Items user, Context context) {
            mBinding.itemUsername.setText(user.getLogin());
            Glide.with(itemView.getContext()).load(user.getAvatarUrl()).placeholder(R.drawable.ic_account_box_black_24dp).circleCrop().into(mBinding.ivItemImage);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(getAdapterPosition());
        }
    }

    public interface ItemOnClickListener {
        void onClick(int position);
    }
}
