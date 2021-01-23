package com.leo.videocall.ui.selectfriend;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leo.videocall.R;
import com.leo.videocall.base.BaseFragment;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;

public class FriendFragment extends BaseFragment implements FriendAdapter.IOnClickFriend, View.OnClickListener {

    @BindView(R.id.btnBackSelectFriend)
    ImageView btnBack;
    @BindView(R.id.btnOk)
    TextView btnOk;
    @BindView(R.id.rcFriend)
    RecyclerView rcFriend;

    public static String TAG = "FriendFragment";
    public static String FRIEND = "FRIEND";
    private ArrayList<Friend> friendsArr;
    private ArrayList<Friend> friendSelectedArr;
    private FriendAdapter adapter;
    private Friend friend;
    private static FriendFragment instance;

    public static FriendFragment getInstance() {
        if (instance == null) {
            instance = new FriendFragment();
        }
        return instance;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void setUp(View view) {
        friendSelectedArr = new ArrayList<>();
        fakeDataFriend();
        adapter = new FriendAdapter(getContext(), friendsArr, this);
        rcFriend.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rcFriend.setAdapter(adapter);


        btnBack.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_select_friend;
    }


    private void fakeDataFriend() {
        friendsArr = new ArrayList<>();
        friendsArr.add(new Friend("Vũ Hồng Việt", "https://i.pinimg.com/originals/01/48/0f/01480f29ce376005edcbec0b30cf367d.jpg", 1));
        friendsArr.add(new Friend("Nguyễn Văn Tuấn", "https://png.pngtree.com/element_our/20190530/ourlarge/pngtree-520-couple-avatar-boy-avatar-little-dinosaur-cartoon-cute-image_1263411.jpg", 2));
        friendsArr.add(new Friend("Nguyễn Hồng Đoàn", "https://hinhnendephd.com/wp-content/uploads/2019/10/anh-avatar-dep.jpg", 3));
        friendsArr.add(new Friend("Nguyễn Đức Trình", "", 3));
    }

    @Override
    public void onClickFiend(int pos, Friend friend) {
        if (friend.isChecked()) {
            friendSelectedArr.add(friend);
        } else {
            Iterator<Friend> itr = friendSelectedArr.iterator();
            while (itr.hasNext()) {
                Friend friend1 = (Friend) itr.next();
                if (friend1.getId() == friend.getId()) {
                    itr.remove();
                }
            }
        }
        for (int i = 0; i < friendSelectedArr.size(); i++) {
            Log.d(TAG, friendSelectedArr.get(i).getName());
        }
    }

    @Override
    public void onDestroy() {
//        instance = null;
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBackSelectFriend:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btnOk:
                break;
        }
    }
}
