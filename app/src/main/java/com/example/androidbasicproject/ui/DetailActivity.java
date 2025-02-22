package com.example.androidbasicproject.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.androidbasicproject.R;
import com.example.androidbasicproject.database.UserEntity;
import com.example.androidbasicproject.databinding.ActivityDetailBinding;
import com.example.androidbasicproject.ui.viewpager.SectionsPagerAdapter;
import com.example.androidbasicproject.viewmodel.BaseViewModelFactory;
import com.example.androidbasicproject.viewmodel.DetailViewModel;

public class DetailActivity extends AppCompatActivity {

    public static final String _USER_DETAIL = "_user_detail";
    private DetailViewModel mViewModel;
    private ActivityDetailBinding mBinding;
    private boolean statusFavorite = false;
    private UserEntity userEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        View rootView = mBinding.getRoot();
        setContentView(rootView);

        mBinding.progressCircular.show();
        mBinding.layoutData.setVisibility(View.GONE);
        BaseViewModelFactory<DetailViewModel> baseVMF = new BaseViewModelFactory<>(new DetailViewModel(getApplication()));
        mViewModel = new ViewModelProvider(this, baseVMF).get(DetailViewModel.class);

        fetchDetail(getIntent().getStringExtra(_USER_DETAIL));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.detail_page_title);
        }

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        mBinding.viewPager.setAdapter(sectionsPagerAdapter);
        mBinding.tabs.setupWithViewPager(mBinding.viewPager);

        mBinding.fab.setOnClickListener(v -> {
            statusFavorite = !statusFavorite;
            setFavorite(statusFavorite);
            setButton(statusFavorite);
        });

        initLiveData();
    }

    private void setFavorite(boolean statusFavorite) {
        if (statusFavorite) {
            //insert
            mViewModel.insertToDB(userEntity);
        } else {
            //delete
            mViewModel.deleteFromDB(userEntity);
        }
    }

    private void setButton(boolean statusFavorite) {
        if (statusFavorite) {
            mBinding.fab.setImageResource(R.drawable.ic_baseline_favorite_24);
        } else {
            mBinding.fab.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }
    }

    private void fetchDetail(String name) {
        mViewModel.fetchUserDetail(name);
        mViewModel.fetchUserFollower(name);
        mViewModel.fetchUserFollowing(name);
    }

    private void initLiveData() {
        mViewModel.getUserDetail().observe(this, userDetail -> {
            if (userDetail != null) {
                mBinding.tvItemUsername.setText(userDetail.getLogin());
                mBinding.tvItemName.setText(String.format(getString(R.string.detail_name), userDetail.getName()));
                mBinding.tvItemFollowing.setText(String.format(getString(R.string.detail_following), userDetail.getFollowing()));
                mBinding.tvItemFollower.setText(String.format(getString(R.string.detail_follower), userDetail.getFollowers()));
                mBinding.tvItemRepository.setText(String.format(getString(R.string.detail_repository), userDetail.getPublicRepos()));
                mBinding.tvItemLocation.setText(String.format(getString(R.string.detail_location), userDetail.getLocation()));
                mBinding.tvItemCompany.setText(userDetail.getCompany());
                Glide.with(DetailActivity.this)
                        .load(userDetail.getAvatarUrl())
                        .placeholder(R.drawable.ic_account_box_black_24dp)
                        .into(mBinding.ivItemImage);

                //region create UserEntity
                this.userEntity = new UserEntity(
                        userDetail.getId(),
                        userDetail.getLogin(),
                        userDetail.getName(),
                        userDetail.getAvatarUrl(),
                        userDetail.getLocation(),
                        userDetail.getPublicRepos(),
                        userDetail.getFollowers(),
                        userDetail.getFollowing());
                //endregion
            }
        });

        mViewModel.getUserFollower().observe(this, followList -> {
            if (followList != null) {
                mBinding.progressCircular.setVisibility(View.GONE);
                mBinding.layoutData.setVisibility(View.VISIBLE);
            }
        });

        mViewModel.getUser().observe(this, aBoolean -> {
            statusFavorite = aBoolean;
            setButton(statusFavorite);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.setIsViewDestroyed(true);
    }
}
