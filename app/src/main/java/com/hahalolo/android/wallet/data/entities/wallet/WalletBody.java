/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.entities.wallet;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hahalolo.android.wallet.data.entities.common.BaseEntity;

/**
 * @author ngannd
 * Create by ngannd on 18/05/2019
 */
public class WalletBody extends BaseEntity {

    // tiền tệ(not null)
    @SerializedName("currency")
    @Expose
    private String currency;

    // Loại ví (0: ví cá nhân, 1: ví doanh nghiệp)(not null)
    @SerializedName("nn317")
    @Expose
    private int nn317;

    // Name wallet (not null)
    @SerializedName("nv301")
    @Expose
    private String nv301;

    // business id(case ví doanh nghiệp thì not null)
    @SerializedName("pb100")
    @Expose
    private int pb100;

    public WalletBody(String currency, int nn317, String nv301, int pb100) {
        this.currency = currency;
        this.nn317 = nn317;
        this.nv301 = nv301;
        this.pb100 = pb100;
    }

    public String getCurrency() {
        return currency;
    }

    public int getNn317() {
        return nn317;
    }

    public String getNv301() {
        return nv301;
    }

    public int getPb100() {
        return pb100;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setNn317(int nn317) {
        this.nn317 = nn317;
    }

    public void setNv301(String nv301) {
        this.nv301 = nv301;
    }

    public void setPb100(int pb100) {
        this.pb100 = pb100;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.currency);
        dest.writeInt(this.nn317);
        dest.writeString(this.nv301);
        dest.writeInt(this.pb100);
    }

    public WalletBody() {
    }

    protected WalletBody(Parcel in) {
        super(in);
        this.currency = in.readString();
        this.nn317 = in.readInt();
        this.nv301 = in.readString();
        this.pb100 = in.readInt();
    }

    public static final Parcelable.Creator<WalletBody> CREATOR = new Parcelable.Creator<WalletBody>() {
        @Override
        public WalletBody createFromParcel(Parcel source) {
            return new WalletBody(source);
        }

        @Override
        public WalletBody[] newArray(int size) {
            return new WalletBody[size];
        }
    };
}
