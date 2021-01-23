package com.leo.videocall.ui.selectfriend;

import android.os.Parcel;
import android.os.Parcelable;

public class Friend implements Parcelable {
    private String name;
    private boolean isChecked = false;
    private String avatar;
    private int id;

    public Friend(String name, String avatar, int id) {
        this.name = name;
        this.avatar = avatar;
        this.id = id;
    }

    protected Friend(Parcel in) {
        name = in.readString();
        isChecked = in.readByte() != 0;
        avatar = in.readString();
        id = in.readInt();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        dest.writeInt(id);
    }
}
