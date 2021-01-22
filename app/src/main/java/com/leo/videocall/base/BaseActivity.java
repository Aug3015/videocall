package com.leo.videocall.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.leo.videocall.R;
import com.leo.videocall.utils.CommonUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements MvpView, BaseFragment.Callback {

    public static final int NO_LAYOUT = -1;
    private ProgressDialog mProgressDialog;
    private Unbinder mUnBinder;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getLayoutId() == NO_LAYOUT) return;
        setContentView(getLayoutId());
        initPresenter();
        setUnBinder(ButterKnife.bind(this));
        handleIntent(getIntent());
        setUp();
    }

    protected abstract int getLayoutId();

    protected abstract void handleIntent(Intent intent);

    protected abstract void initPresenter();

    protected abstract void setUp();

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    private void showSnackBarError(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) sbView.getLayoutParams();
        layoutParams.gravity = Gravity.TOP;
        sbView.setBackgroundColor(getResources().getColor(R.color.redError2));
        sbView.setLayoutParams(layoutParams);
        TextView textView = sbView
                .findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setGravity(Gravity.CENTER);
        textView.setCompoundDrawablePadding(24);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    private void showSnackBarSuccess(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);

        View sbView = snackbar.getView();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) sbView.getLayoutParams();
        layoutParams.gravity = Gravity.TOP;

        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.green2));
        sbView.setLayoutParams(layoutParams);
        TextView textView = sbView
                .findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setPadding(0, 2, 0, 2);
        textView.setCompoundDrawablePadding(24);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBarError(message);
        } else {
            showSnackBarError(getString(R.string.some_error));
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            showSnackBarSuccess(message);
        } else {
            showSnackBarError(getString(R.string.some_error));
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        hideLoading();
        super.onBackPressed();
    }
}
