package com.example.androidbasicproject.ui.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidbasicproject.databinding.FragmentInsideDetailBinding;
import com.example.androidbasicproject.model.GithubList.FollowList;
import com.example.androidbasicproject.ui.ItemListViewAdapter;
import com.example.androidbasicproject.viewmodel.DetailViewModel;

public class PlaceholderFragment extends Fragment implements ItemListViewAdapter.ItemOnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private DetailViewModel mViewModel;
    private FragmentInsideDetailBinding mBinding;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentInsideDetailBinding.inflate(getLayoutInflater());
        initLiveData();
        return mBinding.getRoot();
    }

    private void initLiveData() {
        if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
            mViewModel.getUserFollower().observe(getViewLifecycleOwner(), new Observer<FollowList>() {
                @Override
                public void onChanged(FollowList followList) {
                    if (followList != null) {
                        mBinding.rvFollowList.setHasFixedSize(true);
                        mBinding.rvFollowList.setLayoutManager(
                                new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                        mBinding.rvFollowList.setAdapter(
                                new ItemListViewAdapter(followList.getFollowList(), PlaceholderFragment.this));
                    }
                }
            });
        } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
            mViewModel.getUserFollowing().observe(getViewLifecycleOwner(), new Observer<FollowList>() {
                @Override
                public void onChanged(FollowList followList) {
                    if (followList != null) {
                        mBinding.rvFollowList.setHasFixedSize(true);
                        mBinding.rvFollowList.setLayoutManager(
                                new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                        mBinding.rvFollowList.setAdapter(
                                new ItemListViewAdapter(followList.getFollowList(), PlaceholderFragment.this));
                    }
                }
            });
        }
    }

    @Override
    public void onClick(int position) {
        //intended
    }
}