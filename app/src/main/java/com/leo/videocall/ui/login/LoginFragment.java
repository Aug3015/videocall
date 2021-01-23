package com.leo.videocall.ui.login;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.leo.videocall.R;
import com.leo.videocall.base.BaseFragment;
import com.leo.videocall.ui.selectfriend.FriendFragment;
import com.leo.videocall.utils.CommonUtils;

import javax.sql.CommonDataSource;

import butterknife.BindView;

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.btnNext)
    ImageView btnNext;
    @BindView(R.id.btnFriend)
    TextView btnFriend;
    @BindView(R.id.btnCallRoom)
    TextView btnCallRoom;
    @BindView(R.id.edtUsername)
    EditText edtUsername;
    @BindView(R.id.cons_root)
    ConstraintLayout cons_root;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void setUp(View view) {

        cons_root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                CommonUtils.hideKeyboardFrom(getActivity(), v);
                return false;
            }
        });
        btnCallRoom.setOnClickListener(this);
        btnFriend.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFriend:
            case R.id.btnNext:
                replaceFragment(FriendFragment.getInstance());
                break;
            case R.id.btnCallRoom:
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
