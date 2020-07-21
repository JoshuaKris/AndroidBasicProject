package com.example.androidbasicproject.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class BaseViewModelFactory<VM extends ViewModel> implements ViewModelProvider.Factory {

    private final VM viewModel;

    public BaseViewModelFactory(VM viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) viewModel;
    }

}