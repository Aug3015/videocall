package com.leo.videocall.ui.selectfriend;

import android.os.Parcel;
import android.os.Parcelable;

public class Friend implements Parcelable {
    private String name;
    private boolean isChecked;
    private String avatar;

    public Friend(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    protected Friend(Parcel in) {
        name = in.readString();
        isChecked = in.readByte() != 0;
        avatar = in.readString();
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (isChecked ? 1 : 0));
        dest.writeString(avatar);
    }
}
