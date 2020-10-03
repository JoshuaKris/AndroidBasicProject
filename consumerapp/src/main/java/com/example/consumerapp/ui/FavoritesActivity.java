package com.example.consumerapp.ui;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidbasicproject.ui.DetailActivity;
import com.example.consumerapp.R;
import com.example.consumerapp.databinding.ActivityFavoritesBinding;
import com.example.consumerapp.entity.UserColumn;
import com.example.consumerapp.entity.UserEntity;
import com.example.consumerapp.utils.MappingHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

interface LoadCallback {
    void preExecute();

    void postExecute(ArrayList<UserEntity> userEntities);
}

public class FavoritesActivity extends AppCompatActivity implements FavoriteListViewAdapter.ItemOnClickListener, PopupConfirmationFragment.PopupConfirmationListener, LoadCallback {

    private static final String EXTRA_STATE = "EXTRA_STATE";
    private ActivityFavoritesBinding mBinding;
    private List<UserEntity> userList;

    private PopupConfirmationFragment popupConfirmationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityFavoritesBinding.inflate(getLayoutInflater());
        View rootView = mBinding.getRoot();
        setContentView(rootView);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.favorite_page);
        }

        mBinding.rvItemList.setVisibility(View.GONE);
        mBinding.progressCircular.show();

        mBinding.rvItemList.setHasFixedSize(true);
        mBinding.rvItemList.setLayoutManager(
                new LinearLayoutManager(FavoritesActivity.this, RecyclerView.VERTICAL, false));

        mBinding.fabDeleteAll.setOnClickListener(v -> {
            popupConfirmationFragment = PopupConfirmationFragment.newInstance();
            popupConfirmationFragment.show(getSupportFragmentManager(), "confirmation");
            popupConfirmationFragment.setListener(this);
        });

        //content observer
        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());

        DataObserver myObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(UserColumn.CONTENT_URI, true, myObserver);

        if (savedInstanceState == null) {
            new LoadAsync(this, this).execute();
        } else {
            ArrayList<UserEntity> userEntities = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (userEntities != null) {
                mBinding.rvItemList.setAdapter(
                        new FavoriteListViewAdapter(userEntities, FavoritesActivity.this));
            }
        }

//        initLiveData();

    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(FavoritesActivity.this, DetailActivity.class);
        intent.putExtra(DetailActivity._USER_DETAIL, userList.get(position).getLogin());
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
        }
    }

    @Override
    public void preExecute() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBinding.progressCircular.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void postExecute(ArrayList<UserEntity> userEntities) {
        mBinding.progressCircular.setVisibility(View.INVISIBLE);
        if (userEntities.size() > 0) {
            mBinding.rvItemList.setAdapter(
                    new FavoriteListViewAdapter(userEntities, FavoritesActivity.this));
            mBinding.tvNoFav.setVisibility(View.GONE);
            mBinding.rvItemList.setVisibility(View.VISIBLE);
            mBinding.fabDeleteAll.setVisibility(View.VISIBLE);

        } else {
            mBinding.tvNoFav.setVisibility(View.VISIBLE);
            mBinding.rvItemList.setVisibility(View.GONE);
            mBinding.fabDeleteAll.setVisibility(View.GONE);
            Toast.makeText(this, "Tidak ada data saat ini", Toast.LENGTH_SHORT).show();
        }
    }

    public static class DataObserver extends ContentObserver {

        final Context context;

        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadAsync(context, (LoadCallback) context).execute();

        }
    }

    private static class LoadAsync extends AsyncTask<Void, Void, ArrayList<UserEntity>> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadCallback> weakCallback;

        private LoadAsync(Context context, LoadCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<UserEntity> doInBackground(Void... voids) {
            Context context = weakContext.get();
            Cursor dataCursor = context.getContentResolver().query(UserColumn.CONTENT_URI, null, null, null, null);
            return MappingHelper.mapCursorToArrayList(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<UserEntity> userEntities) {
            super.onPostExecute(userEntities);
            weakCallback.get().postExecute(userEntities);
        }
    }
}