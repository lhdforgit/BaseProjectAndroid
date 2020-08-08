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

/**
 * @author ngannd
 * Create by ngannd on 18/05/2019
 */
public class N450 implements Parcelable {

    // Id object
    @SerializedName("ph100")
    @Expose
    private String ph100;

    // Id user
    @SerializedName("pn100")
    @Expose
    private String pn100;

    // Id wallet
    @SerializedName("pn300")
    @Expose
    private String pn300;

    // Country name
    @SerializedName("nv451")
    @Expose
    private String nv451;

    // Country code
    @SerializedName("countryCode")
    @Expose
    private String countryCode;

    // Country name
    @SerializedName("countryName")
    @Expose
    private String countryName;

    // Country code pay
    @SerializedName("countryCodePay")
    @Expose
    private String countryCodePay;

    // Country name pay
    @SerializedName("countryNamePay")
    @Expose
    private String countryNamePay;

    // Type account
    @SerializedName("nn452")
    @Expose
    private int nn452;

    // Tax number
    @SerializedName("nv453")
    @Expose
    private String nv453;

    // Type tax id
    @SerializedName("nv454")
    @Expose
    private String nv454;

    // Account name
    @SerializedName("nv455")
    @Expose
    private String nv455;

    // Address
    @SerializedName("nv456")
    @Expose
    private String nv456;

    // Address 2
    @SerializedName("nv457")
    @Expose
    private String nv457;

    // City/Town
    @SerializedName("nv458")
    @Expose
    private String nv458;

    // Zip postal code
    @SerializedName("nv459")
    @Expose
    private String nv459;

    // State province
    @SerializedName("nv460")
    @Expose
    private String nv460;

    // Tax form
    @SerializedName("nv461")
    @Expose
    private String nv461;

    // Payment info
    @SerializedName("nn462")
    @Expose
    private int nn462;

    // Account holder name
    @SerializedName("nv463")
    @Expose
    private String nv463;

    // Bank name
    @SerializedName("nv464")
    @Expose
    private String nv464;

    // Swift code
    @SerializedName("nv465")
    @Expose
    private String nv465;

    // Bank account number
    @SerializedName("nv466")
    @Expose
    private String nv466;

    // Paypal
    @SerializedName("nv467")
    @Expose
    private String nv467;

    // First name
    @SerializedName("nv468")
    @Expose
    private String nv468;

    // Middle name
    @SerializedName("nv469")
    @Expose
    private String nv469;

    // Last name
    @SerializedName("nv470")
    @Expose
    private String nv470;

    // Birthday
    @SerializedName("nd471")
    @Expose
    private String nd471;

    // Check address acount owner
    @SerializedName("nn472")
    @Expose
    private int nn472;

    // Name State/Provice
    @SerializedName("nv473")
    @Expose
    private String nv473;

    // Email
    @SerializedName("nv478")
    @Expose
    private String nv478;

    // Step
    @SerializedName("nn480")
    @Expose
    private int nn480;

    public N450() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ph100);
        dest.writeString(this.pn100);
        dest.writeString(this.pn300);
        dest.writeString(this.nv451);
        dest.writeString(this.countryCode);
        dest.writeString(this.countryName);
        dest.writeString(this.countryCodePay);
        dest.writeString(this.countryNamePay);
        dest.writeInt(this.nn452);
        dest.writeString(this.nv453);
        dest.writeString(this.nv454);
        dest.writeString(this.nv455);
        dest.writeString(this.nv456);
        dest.writeString(this.nv457);
        dest.writeString(this.nv458);
        dest.writeString(this.nv459);
        dest.writeString(this.nv460);
        dest.writeString(this.nv461);
        dest.writeInt(this.nn462);
        dest.writeString(this.nv463);
        dest.writeString(this.nv464);
        dest.writeString(this.nv465);
        dest.writeString(this.nv466);
        dest.writeString(this.nv467);
        dest.writeString(this.nv468);
        dest.writeString(this.nv469);
        dest.writeString(this.nv470);
        dest.writeString(this.nd471);
        dest.writeInt(this.nn472);
        dest.writeString(this.nv473);
        dest.writeString(this.nv478);
        dest.writeInt(this.nn480);
    }

    protected N450(Parcel in) {
        this.ph100 = in.readString();
        this.pn100 = in.readString();
        this.pn300 = in.readString();
        this.nv451 = in.readString();
        this.countryCode = in.readString();
        this.countryName = in.readString();
        this.countryCodePay = in.readString();
        this.countryNamePay = in.readString();
        this.nn452 = in.readInt();
        this.nv453 = in.readString();
        this.nv454 = in.readString();
        this.nv455 = in.readString();
        this.nv456 = in.readString();
        this.nv457 = in.readString();
        this.nv458 = in.readString();
        this.nv459 = in.readString();
        this.nv460 = in.readString();
        this.nv461 = in.readString();
        this.nn462 = in.readInt();
        this.nv463 = in.readString();
        this.nv464 = in.readString();
        this.nv465 = in.readString();
        this.nv466 = in.readString();
        this.nv467 = in.readString();
        this.nv468 = in.readString();
        this.nv469 = in.readString();
        this.nv470 = in.readString();
        this.nd471 = in.readString();
        this.nn472 = in.readInt();
        this.nv473 = in.readString();
        this.nv478 = in.readString();
        this.nn480 = in.readInt();
    }

    public static final Creator<N450> CREATOR = new Creator<N450>() {
        @Override
        public N450 createFromParcel(Parcel source) {
            return new N450(source);
        }

        @Override
        public N450[] newArray(int size) {
            return new N450[size];
        }
    };
}
