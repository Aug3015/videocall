package com.leo.videocall.ui.login;

import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.leo.videocall.R;
import com.leo.videocall.base.BaseFragment;
import com.leo.videocall.ui.selectfriend.FriendFragment;

import butterknife.BindView;

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.btnNext)
    ImageView btnNext;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void setUp(View view) {


        btnNext.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                replaceFragment(FriendFragment.getInstance());
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frMain, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
