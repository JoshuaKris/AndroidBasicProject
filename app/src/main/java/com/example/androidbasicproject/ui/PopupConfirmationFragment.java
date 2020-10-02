package com.example.androidbasicproject.ui;


import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.androidbasicproject.R;
import com.example.androidbasicproject.databinding.FragmentPopupConfirmationBinding;

import java.util.Objects;

public class PopupConfirmationFragment extends DialogFragment {

    private PopupConfirmationListener mPopupListener;

    public PopupConfirmationFragment() {
    }

    public static PopupConfirmationFragment newInstance() {
        PopupConfirmationFragment fragment = new PopupConfirmationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentPopupConfirmationBinding mBinding = FragmentPopupConfirmationBinding.inflate(getLayoutInflater());
        View fragmentView = mBinding.getRoot();

        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCancelable(false);

        mBinding.btConfirmationCancel.setOnClickListener(view -> getDialog().dismiss());

        mBinding.btConfirmationOk.setOnClickListener(v -> {
            getDialog().dismiss();
            if (mPopupListener != null) {
                mPopupListener.okClicked(true);
            }
        });

        return fragmentView;
    }

    public void setListener(PopupConfirmationListener mPopupListener) {
        this.mPopupListener = mPopupListener;
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = Objects.requireNonNull(getDialog()).getWindow();
        Point size = new Point();
        Display display = Objects.requireNonNull(window).getWindowManager().getDefaultDisplay();
        display.getSize(size);
//        int height = size.y;
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawableResource(R.drawable.dialog_background);
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public interface PopupConfirmationListener {
        void okClicked(boolean isOkClicked);
    }
}
