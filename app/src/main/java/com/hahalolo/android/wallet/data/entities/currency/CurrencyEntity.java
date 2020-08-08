/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.entities.currency;

import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.hahalolo.android.wallet.constant.HaloConfig.VND_CURRENCY;

/**
 * @author ndn
 * Created by ndn
 * Created on 10/16/18.
 */
@Entity(tableName = "currency", primaryKeys = "id")
public class CurrencyEntity implements Parcelable {

    @SerializedName("dl146")
    @Expose
    public String dl146;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("alias")
    @Expose
    public String alias;

    @SerializedName("code")
    @Expose
    public String code;

    @SerializedName("price")
    @Expose
    public Double price = 1.0;

    @SerializedName("date")
    @Expose
    public String date;

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("id")
    @Expose
    @NonNull
    public String id = "";

    // For UI
    public boolean checked = false;

    public static CurrencyEntity vnd() {
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setCode(VND_CURRENCY);
        return currencyEntity;
    }

    public CurrencyEntity() {
    }

    public String getDl146() {
        return dl146;
    }

    public String getName() {
        return name;
    }

    public boolean condition() {
        return !TextUtils.isEmpty(name) && !TextUtils.isEmpty(code);
    }

    public String getAlias() {
        return alias;
    }

    public String getCode() {
        return code;
    }

    public Double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void copyCurrency(@NonNull CurrencyEntity newEntity) {
        this.dl146 = newEntity.dl146;
        this.name = newEntity.name;
        this.alias = newEntity.alias;
        this.code = newEntity.code;
        this.price = newEntity.price;
        this.date = newEntity.date;
        this.status = newEntity.status;
        this.id = newEntity.id;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dl146);
        dest.writeString(this.name);
        dest.writeString(this.alias);
        dest.writeString(this.code);
        dest.writeDouble(this.price);
        dest.writeString(this.date);
        dest.writeString(this.status);
        dest.writeString(this.id);
    }

    protected CurrencyEntity(Parcel in) {
        this.dl146 = in.readString();
        this.name = in.readString();
        this.alias = in.readString();
        this.code = in.readString();
        this.price = in.readDouble();
        this.date = in.readString();
        this.status = in.readString();
        this.id = in.readString();
    }

    public static final Creator<CurrencyEntity> CREATOR = new Creator<CurrencyEntity>() {
        @Override
        public CurrencyEntity createFromParcel(Parcel source) {
            return new CurrencyEntity(source);
        }

        @Override
        public CurrencyEntity[] newArray(int size) {
            return new CurrencyEntity[size];
        }
    };

    @Override
    public String toString() {
        return "CurrencyEntity{" +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                '}';
    }
}
