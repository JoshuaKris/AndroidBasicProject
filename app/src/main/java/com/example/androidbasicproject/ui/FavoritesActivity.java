package com.example.androidbasicproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidbasicproject.R;
import com.example.androidbasicproject.database.UserEntity;
import com.example.androidbasicproject.databinding.ActivityFavoritesBinding;
import com.example.androidbasicproject.viewmodel.BaseViewModelFactory;
import com.example.androidbasicproject.viewmodel.FavoritesViewModel;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements FavoriteListViewAdapter.ItemOnClickListener, PopupConfirmationFragment.PopupConfirmationListener {

    private ActivityFavoritesBinding mBinding;
    private FavoritesViewModel mViewModel;
    private List<UserEntity> userList;

    private PopupConfirmationFragment popupConfirmationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityFavoritesBinding.inflate(getLayoutInflater());
        View rootView = mBinding.getRoot();
        setContentView(rootView);

        BaseViewModelFactory<FavoritesViewModel> baseVMF = new BaseViewModelFactory<>(new FavoritesViewModel(getApplication()));
        mViewModel = new ViewModelProvider(this, baseVMF).get(FavoritesViewModel.class);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.favorite_page);
        }

        mBinding.rvItemList.setVisibility(View.GONE);
        mBinding.progressCircular.show();

        mBinding.fabDeleteAll.setOnClickListener(v -> {
            popupConfirmationFragment = PopupConfirmationFragment.newInstance();
            popupConfirmationFragment.show(getSupportFragmentManager(), "confirmation");
            popupConfirmationFragment.setListener(this);
        });

        initLiveData();
    }

    private void initLiveData() {
        mViewModel.getUser().observe(this, userEntities -> {
            mBinding.progressCircular.setVisibility(View.GONE);
            if (userEntities != null) {
                userList = userEntities;
                mBinding.rvItemList.setHasFixedSize(true);
                mBinding.rvItemList.setLayoutManager(
                        new LinearLayoutManager(FavoritesActivity.this, RecyclerView.VERTICAL, false));
                mBinding.rvItemList.setAdapter(
                        new FavoriteListViewAdapter(userList, FavoritesActivity.this));

                if (userList.isEmpty()) {
                    mBinding.tvNoFav.setVisibility(View.VISIBLE);
                    mBinding.rvItemList.setVisibility(View.GONE);
                    mBinding.fabDeleteAll.setVisibility(View.GONE);
                } else {
                    mBinding.tvNoFav.setVisibility(View.GONE);
                    mBinding.rvItemList.setVisibility(View.VISIBLE);
                    mBinding.fabDeleteAll.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void checkDB(String login) {
        mViewModel.checkThisUser(login);
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(FavoritesActivity.this, DetailActivity.class);
        intent.putExtra(DetailActivity._USER_DETAIL, userList.get(position).getLogin());
        checkDB(userList.get(position).getLogin());
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return true;
    }

    @Override
    public void okClicked(boolean isOkClicked) {
        //execute
        if (isOkClicked) {
            mViewModel.deleteAllFavorites();
        }
    }
}