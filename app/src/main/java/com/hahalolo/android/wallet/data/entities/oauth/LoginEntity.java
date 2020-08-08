/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.entities.oauth;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ndn
 * Created by ndn
 * Created on 10/24/18.
 */
public class LoginEntity implements Parcelable {

    private String username;
    private String password;

    public LoginEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
    }

    protected LoginEntity(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
    }

    public static final Creator<LoginEntity> CREATOR = new Creator<LoginEntity>() {
        @Override
        public LoginEntity createFromParcel(Parcel source) {
            return new LoginEntity(source);
        }

        @Override
        public LoginEntity[] newArray(int size) {
            return new LoginEntity[size];
        }
    };
}
