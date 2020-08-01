package com.example.androidbasicproject.ui;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidbasicproject.R;
import com.example.androidbasicproject.databinding.ActivityMainBinding;
import com.example.androidbasicproject.model.GithubModel.Users;
import com.example.androidbasicproject.viewmodel.BaseViewModelFactory;
import com.example.androidbasicproject.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements ItemListViewAdapter.ItemOnClickListener {

    private MainViewModel mViewModel;
    private ActivityMainBinding mBinding;
    private Users userList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    mBinding.flStart.setVisibility(View.GONE);
                    mBinding.cvMain.setVisibility(View.GONE);
                    mBinding.progressCircular.show();
                    Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View rootView = mBinding.getRoot();
        setContentView(rootView);

        BaseViewModelFactory<MainViewModel> baseVMF = new BaseViewModelFactory<>(new MainViewModel(getApplication()));
        mViewModel = new ViewModelProvider(this, baseVMF).get(MainViewModel.class);

        mBinding.cvMain.setVisibility(View.GONE);
        mBinding.flStart.setVisibility(View.VISIBLE);
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
                mBinding.cvMain.setVisibility(View.VISIBLE);
                if (users != null) {
                    userList = users;
                    mBinding.rvItemList.setHasFixedSize(true);
                    mBinding.rvItemList.setLayoutManager(
                            new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                    mBinding.rvItemList.setAdapter(
                            new ItemListViewAdapter(userList.getItems(), MainActivity.this));
                }
            }
        });
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra(DetailActivity._USER_DETAIL,userList.getItems().get(position).getLogin());
        startActivity(intent);
    }
}
