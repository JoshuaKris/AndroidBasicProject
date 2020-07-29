package com.example.androidbasicproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.androidbasicproject.model.GithubModel.Users;
import com.example.androidbasicproject.databinding.MainActivityBinding;
import com.example.androidbasicproject.viewmodel.BaseViewModelFactory;
import com.example.androidbasicproject.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements ItemListViewAdapter.ItemOnClickListener {

    private MainViewModel mViewModel;
    private MainActivityBinding mBinding;
    private Users userList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint("Search Github User");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    mBinding.progressCircular.show();
                    Toast.makeText(MainActivity.this,query,Toast.LENGTH_SHORT).show();
                    mViewModel.searchUser(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.setting) {
            startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = MainActivityBinding.inflate(getLayoutInflater());
        View rootView = mBinding.getRoot();
        setContentView(rootView);

        BaseViewModelFactory<MainViewModel> baseVMF = new BaseViewModelFactory<>(new MainViewModel(getApplication()));
        mViewModel = new ViewModelProvider(this,baseVMF).get(MainViewModel.class);

        initLiveData();
    }

    private void initLiveData() {
        mViewModel.getSearch().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    Log.d("getSearch", "onChanged: " + s);
                }
            }
        });

        mViewModel.getUserList().observe(this, new Observer<Users>() {
            @Override
            public void onChanged(Users users) {
                mBinding.progressCircular.setVisibility(View.GONE);
                if (users != null) {
                    userList = users;
                    mBinding.rvItemList.setHasFixedSize(true);
                    mBinding.rvItemList.setLayoutManager(
                            new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL,false));
                    mBinding.rvItemList.setAdapter(
                            new ItemListViewAdapter(userList, MainActivity.this, MainActivity.this));
//                    Toast.makeText(MainActivity.this, users.getTotalCount(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(this, "this is " + userList.getItems().get(position), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra(DetailActivity._USER_DETAIL,userList.getItems().get(position).getLogin());
        startActivity(intent);
    }
}
