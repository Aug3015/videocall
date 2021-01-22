package com.leo.videocall.base;

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);

    void onDetach();
}
