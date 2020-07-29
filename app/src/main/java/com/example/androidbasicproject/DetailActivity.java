package com.example.androidbasicproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.androidbasicproject.model.GithubDetail.UserDetail;
import com.example.androidbasicproject.model.GithubList.FollowList;
import com.example.androidbasicproject.databinding.ActivityDetailBinding;
import com.example.androidbasicproject.ui.main.SectionsPagerAdapter;
import com.example.androidbasicproject.viewmodel.BaseViewModelFactory;
import com.example.androidbasicproject.viewmodel.DetailViewModel;
import com.google.android.material.tabs.TabLayout;

public class DetailActivity extends AppCompatActivity {

    public static final String _USER_DETAIL = "_user_detail";
    private DetailViewModel mViewModel;
    private ActivityDetailBinding mBinding;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        View rootView = mBinding.getRoot();
        setContentView(rootView);

        BaseViewModelFactory<DetailViewModel> baseVMF = new BaseViewModelFactory<>(new DetailViewModel(getApplication()));
        mViewModel = new ViewModelProvider(this,baseVMF).get(DetailViewModel.class);

        fetchDetail(getIntent().getStringExtra(_USER_DETAIL));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.detail_page_title);
        }

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        mBinding.viewPager.setAdapter(sectionsPagerAdapter);
        mBinding.tabs.setupWithViewPager(mBinding.viewPager);

        initLiveData();
    }

    private void fetchDetail(String name) {
        mViewModel.fetchUserDetail(name);
        mViewModel.fetchUserFollower(name);
        mViewModel.fetchUserFollowing(name);
    }

    private void initLiveData() {
        mViewModel.getUserDetail().observe(this, new Observer<UserDetail>() {
            @Override
            public void onChanged(UserDetail userDetail) {
                if (userDetail != null) {
                    mBinding.tvItemUsername.setText(userDetail.getLogin());
                    mBinding.tvItemName.setText(String.format("Name : %1$s",userDetail.getName()));
                    mBinding.tvItemFollowing.setText(String.format("Following : %1$s",userDetail.getFollowing()));
                    mBinding.tvItemFollower.setText(String.format("Follower : %1$s",userDetail.getFollowers()));
                    mBinding.tvItemRepository.setText(String.format("Repository : %1$s",userDetail.getPublicRepos()));
                    mBinding.tvItemLocation.setText(String.format("Location : %1$s",userDetail.getLocation()));
                    mBinding.tvItemCompany.setText(userDetail.getCompany());
                    Glide.with(DetailActivity.this)
                            .load(userDetail.getAvatarUrl())
                            .circleCrop()
                            .placeholder(R.drawable.ic_account_box_black_24dp)
                            .into(mBinding.ivItemImage);
                }
            }
        });

        mViewModel.getUserFollower().observe(this, new Observer<FollowList>() {
            @Override
            public void onChanged(FollowList followList) {
                if (followList != null) {
                    Log.d("Followers", followList.toString());
                }
            }
        });

        mViewModel.getUserFollowing().observe(this, new Observer<FollowList>() {
            @Override
            public void onChanged(FollowList followList) {
                if (followList != null) {
                    Log.d("Following", followList.toString());
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return true;
    }

}
