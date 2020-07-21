package com.example.androidbasicproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidbasicproject.databinding.MainActivityBinding;
import com.example.androidbasicproject.model.GithubUsers;
import com.example.androidbasicproject.viewmodel.BaseViewModelFactory;
import com.example.androidbasicproject.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements ItemListViewAdapter.ItemOnClickListener {

    private MainViewModel mViewModel;
    private MainActivityBinding mBinding;
    private GithubUsers userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = MainActivityBinding.inflate(getLayoutInflater());
        View rootView = mBinding.getRoot();
        setContentView(rootView);

        BaseViewModelFactory<MainViewModel> baseVMF = new BaseViewModelFactory<>(new MainViewModel(this));
        mViewModel = ViewModelProviders.of(this,baseVMF).get(MainViewModel.class);
        mViewModel.fetchItemList();

        initLiveData();

    }

    private void initLiveData() {
        mViewModel.getItemList().observe(this, new Observer<GithubUsers>() {
            @Override
            public void onChanged(GithubUsers githubUsers) {
                if (githubUsers != null) {
                    userList = githubUsers;
                    mBinding.rvItemList.setHasFixedSize(true);
                    mBinding.rvItemList.setLayoutManager(
                            new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL,false));
                    mBinding.rvItemList.setAdapter(
                            new ItemListViewAdapter(userList, MainActivity.this, MainActivity.this));
                }
            }
        });
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(this, "this is " + userList.getUsers().get(position), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra(DetailActivity.ITEM_DETAIL_,userList.getUsers().get(position));
        startActivity(intent);
    }
}
