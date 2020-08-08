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

import java.util.List;

/**
 * @author ngannd
 * Create by ngannd on 18/05/2019
 */
public class N300 implements Parcelable {

    @SerializedName("ph000")
    @Expose
    private String ph000;

    @SerializedName("pn100")
    @Expose
    private String pn100;

    @SerializedName("objPn100")
    @Expose
    private String objPn100;

    // Name
    @SerializedName("nv301")
    @Expose
    private String nv301;

    // Current available
    @SerializedName("nn302")
    @Expose
    private double nn302;

    // Income Waiting
    @SerializedName("nn303")
    @Expose
    private int nn303;

    // Income Pending
    @SerializedName("nn304")
    @Expose
    private int nn304;

    // Income Holding
    @SerializedName("nn305")
    @Expose
    private int nn305;

    // Outcome Waiting
    @SerializedName("nn306")
    @Expose
    private int nn306;

    // Outcome Pending
    @SerializedName("nn307")
    @Expose
    private int nn307;

    // Outcome Holding
    @SerializedName("nn308")
    @Expose
    private int nn308;

    // Extra Holding
    @SerializedName("nn309")
    @Expose
    private int nn309;

    // Canceled/Refunded
    @SerializedName("nn310")
    @Expose
    private int nn310;

    // Paid Pending
    @SerializedName("nn311")
    @Expose
    private int nn311;

    // Paid
    @SerializedName("nn312")
    @Expose
    private int nn312;

    // Note
    @SerializedName("nv313")
    @Expose
    private String nv313;

    // Code Id User
    @SerializedName("nv315")
    @Expose
    private String nv315;

    // (0: ví cá nhân, 1: ví doanh nghiệp)
    @SerializedName("nn317")
    @Expose
    private int nn317;

    @SerializedName("nn321")
    @Expose
    private int nn321;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("currency")
    @Expose
    private String currency;

    // Embedded list payment account.
    @SerializedName("fn450")
    @Expose
    private List<N450> fn450;

    public String getPh000() {
        return ph000;
    }

    public String getPn100() {
        return pn100;
    }

    public String getObjPn100() {
        return objPn100;
    }

    public String getNv301() {
        return nv301;
    }

    public double getNn302() {
        return nn302;
    }

    public int getNn303() {
        return nn303;
    }

    public int getNn304() {
        return nn304;
    }

    public int getNn305() {
        return nn305;
    }

    public int getNn306() {
        return nn306;
    }

    public int getNn307() {
        return nn307;
    }

    public int getNn308() {
        return nn308;
    }

    public int getNn309() {
        return nn309;
    }

    public int getNn310() {
        return nn310;
    }

    public int getNn311() {
        return nn311;
    }

    public int getNn312() {
        return nn312;
    }

    public String getNv313() {
        return nv313;
    }

    public String getNv315() {
        return nv315;
    }

    public int getNn317() {
        return nn317;
    }

    public int getNn321() {
        return nn321;
    }

    public List<N450> getFn450() {
        return fn450;
    }

    public String getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public N300() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ph000);
        dest.writeString(this.pn100);
        dest.writeString(this.objPn100);
        dest.writeString(this.nv301);
        dest.writeDouble(this.nn302);
        dest.writeInt(this.nn303);
        dest.writeInt(this.nn304);
        dest.writeInt(this.nn305);
        dest.writeInt(this.nn306);
        dest.writeInt(this.nn307);
        dest.writeInt(this.nn308);
        dest.writeInt(this.nn309);
        dest.writeInt(this.nn310);
        dest.writeInt(this.nn311);
        dest.writeInt(this.nn312);
        dest.writeString(this.nv313);
        dest.writeString(this.nv315);
        dest.writeInt(this.nn317);
        dest.writeInt(this.nn321);
        dest.writeString(this.id);
        dest.writeString(this.currency);
        dest.writeTypedList(this.fn450);
    }

    protected N300(Parcel in) {
        this.ph000 = in.readString();
        this.pn100 = in.readString();
        this.objPn100 = in.readString();
        this.nv301 = in.readString();
        this.nn302 = in.readDouble();
        this.nn303 = in.readInt();
        this.nn304 = in.readInt();
        this.nn305 = in.readInt();
        this.nn306 = in.readInt();
        this.nn307 = in.readInt();
        this.nn308 = in.readInt();
        this.nn309 = in.readInt();
        this.nn310 = in.readInt();
        this.nn311 = in.readInt();
        this.nn312 = in.readInt();
        this.nv313 = in.readString();
        this.nv315 = in.readString();
        this.nn317 = in.readInt();
        this.nn321 = in.readInt();
        this.id = in.readString();
        this.currency = in.readString();
        this.fn450 = in.createTypedArrayList(N450.CREATOR);
    }

    @Override
    public String toString() {
        return "N300{" +
                "ph000='" + ph000 + '\'' +
                ", pn100='" + pn100 + '\'' +
                ", objPn100='" + objPn100 + '\'' +
                ", nv301='" + nv301 + '\'' +
                ", nn302=" + nn302 +
                ", nn303=" + nn303 +
                ", nn304=" + nn304 +
                ", nn305=" + nn305 +
                ", nn306=" + nn306 +
                ", nn307=" + nn307 +
                ", nn308=" + nn308 +
                ", nn309=" + nn309 +
                ", nn310=" + nn310 +
                ", nn311=" + nn311 +
                ", nn312=" + nn312 +
                ", nv313='" + nv313 + '\'' +
                ", nv315='" + nv315 + '\'' +
                ", nn317=" + nn317 +
                ", nn321=" + nn321 +
                ", id='" + id + '\'' +
                ", currency='" + currency + '\'' +
                ", fn450=" + fn450 +
                '}';
    }

    public static final Creator<N300> CREATOR = new Creator<N300>() {
        @Override
        public N300 createFromParcel(Parcel source) {
            return new N300(source);
        }

        @Override
        public N300[] newArray(int size) {
            return new N300[size];
        }
    };
}
