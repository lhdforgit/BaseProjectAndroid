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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity;

/**
 * @author ndn
 * Created by ndn
 * Created on 10/8/18
 */
public class LogoutResponseEntity implements Parcelable {

    @SerializedName("status")
    @Expose
    private StatusMongoEntity status;

    public StatusMongoEntity getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return status != null && status.isSuccess();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.status, flags);
    }

    public LogoutResponseEntity() {

    }

    protected LogoutResponseEntity(Parcel in) {
        this.status = in.readParcelable(StatusMongoEntity.class.getClassLoader());
    }

    public static final Creator<LogoutResponseEntity> CREATOR = new Creator<LogoutResponseEntity>() {
        @Override
        public LogoutResponseEntity createFromParcel(Parcel source) {
            return new LogoutResponseEntity(source);
        }

        @Override
        public LogoutResponseEntity[] newArray(int size) {
            return new LogoutResponseEntity[size];
        }
    };

    @Override
    public String toString() {
        return "LogoutResponseEntity{" +
                "statusNetwork=" + status +
                '}';
    }
}
