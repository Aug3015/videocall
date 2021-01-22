package com.leo.videocall.base;

import androidx.annotation.StringRes;

public interface MvpView {
    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);
}
