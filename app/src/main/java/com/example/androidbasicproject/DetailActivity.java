package com.example.androidbasicproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.androidbasicproject.databinding.ActivityDetailBinding;
import com.example.androidbasicproject.model.Users;

public class DetailActivity extends AppCompatActivity {

    public static final String ITEM_DETAIL_ = "item_detail";

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding mBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        View rootView = mBinding.getRoot();
        setContentView(rootView);

        mBinding.ivBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (getIntent() != null) {
            Users user = getIntent().getParcelableExtra(ITEM_DETAIL_);

            if (user != null) {
                mBinding.tvItemUsername.setText(user.getUsername());
                mBinding.tvItemName.setText(String.format("Name : %1$s",user.getName()));
                mBinding.tvItemFollowing.setText(String.format("Following : %1$d",user.getFollowing()));
                mBinding.tvItemFollower.setText(String.format("Follower : %1$d",user.getFollower()));
                mBinding.tvItemRepository.setText(String.format("Repository : %1$s",user.getRepository()));
                mBinding.tvItemLocation.setText(String.format("Location : %1$s",user.getLocation()));
                mBinding.tvItemCompany.setText(user.getCompany());

                int id = DetailActivity.this.getResources().getIdentifier(user.getAvatar(), "drawable", this.getPackageName());
                Glide.with(DetailActivity.this).load(id).placeholder(R.drawable.ic_account_box_black_24dp).into(mBinding.ivItemImage);
            }

        }

    }


}
