package com.leo.videocall.ui.selectfriend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.leo.videocall.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder> {
    private Context context;
    private ArrayList<Friend> friendArr;
    private IOnClickFriend inter;

    public FriendAdapter(Context context, ArrayList<Friend> friendArr, IOnClickFriend inter) {
        this.context = context;
        this.friendArr = friendArr;
        this.inter = inter;
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false);
        return new FriendHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (friendArr == null)
            return 0;
        else
            return friendArr.size();
    }

    class FriendHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nameFriend)
        TextView nameFriend;
        @BindView(R.id.ivAvatar)
        ImageView ivAvatar;
        @BindView(R.id.ivCheck)
        ImageView ivCheck;

        public FriendHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int pos) {
            Friend friend = friendArr.get(pos);
            nameFriend.setText(friend.getName());
            Glide.with(context).load(friend.getAvatar()).into(ivAvatar);

            friend.setChecked(!friend.isChecked());

            itemView.setOnClickListener(v -> {
                if (friend.isChecked()) {
                    friend.setChecked(false);
                    ivCheck.setImageResource(R.drawable.ic_uncheck);
                } else {
                    ivCheck.setImageResource(R.drawable.ic_checked);
                    friend.setChecked(true);
                }

                inter.onClickFiend(pos, friend);
                notifyItemChanged(pos);
            });
        }
    }

    public interface IOnClickFriend {
        void onClickFiend(int pos, Friend friend);
    }
}
