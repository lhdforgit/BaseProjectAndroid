/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.entities.token;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hahalolo.android.wallet.constant.HaloConfig;

import java.util.UUID;

/**
 * @author ndn
 * Created by ndn
 * Created on 6/11/18.
 * <p>
 */
public class TokenEntity implements Parcelable {

    @SerializedName("pn100")
    @Expose
    @NonNull
    public String userId = UUID.randomUUID().toString();

    @SerializedName("access_token")
    @Expose
    @NonNull
    public String accessToken = UUID.randomUUID().toString();

    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;

    @SerializedName("nexttoken")
    @Expose
    public String nexttoken;

    @SerializedName("token_type")
    @Expose
    public String tokenType;

    @SerializedName("token_date")
    @Expose
    public String tokenDate;

    /*
     *  Expires in for token on server
     *  When token expires, re login with email or password
     */
    @SerializedName("expires_in")
    @Expose
    public long expiresIn;

    @SerializedName("scope")
    @Expose
    public String scope;

    @SerializedName("nv101")
    @Expose
    public String code;

    @SerializedName("nv102")
    @Expose
    public String username;

    @SerializedName("nv103")
    @Expose
    public String firstname;

    @SerializedName("nv104")
    @Expose
    public String lastname;

    @SerializedName("nv105")
    @Expose
    public String fullname;

    @SerializedName("nv107")
    @Expose
    public String gender;

    @SerializedName("nv108")
    @Expose
    public String mail;

    @SerializedName("nv111")
    @Expose
    public String countryCode;

    @SerializedName("nv120")
    @Expose
    public String avatar;

    @SerializedName("nn121")
    @Expose
    public int businessExist;

    @SerializedName("nn122")
    @Expose
    public int walletExist;

    @SerializedName("nn165")
    @Expose
    public int liked;

    @SerializedName("nd106")
    @Expose
    public long nd106;

    @SerializedName("dl148")
    @Expose
    public String dl148;

    @SerializedName("nsms")
    @Expose
    public int nsms = 0;

    @SerializedName("nn130")
    @Expose
    public int nn130 = 0;

    @SerializedName("nn132")
    @Expose
    public int nn132 = 0;

    @SerializedName("nn141")
    @Expose
    public int nn141 = 0;

    @SerializedName("lang")
    @Expose
    public String lang;

    @SerializedName("currency")
    @Expose
    public String currency;

    @SerializedName("ipAddress")
    @Expose
    public String ipAddress;

    @SerializedName("requestHeader")
    @Expose
    public String requestHeader;

    @SerializedName("deviceName")
    @Expose
    public String deviceName;

    @SerializedName("browserName")
    @Expose
    public String browserName;

    @SerializedName("clientOsName")
    @Expose
    public String clientOsName;

    @SerializedName("toolName")
    @Expose
    public String toolName;

    public TokenEntity() {
    }

    @NonNull
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(@NonNull String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void updateExpiresIn() {
        this.expiresIn = (this.expiresIn * 1000) + System.currentTimeMillis();
    }

    /**
     * Check expires of token
     *
     * @return true if token isn't expires
     */
    public boolean expired() {
         return this.expiresIn < System.currentTimeMillis();
    }

    public String getUserId() {
        return Strings.nullToEmpty(userId);
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public boolean isUnactive() {
        return TextUtils.equals(accessToken, HaloConfig.TOKEN_N_ACTIVE);
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getNexttoken() {
        return nexttoken;
    }

    public String getTokenDate() {
        return tokenDate;
    }

    public String getScope() {
        return scope;
    }

    public String getCode() {
        return code;
    }

    public String getUsername() {
        return username;
    }

    public int getBusinessExist() {
        return businessExist;
    }

    public int getWalletExist() {
        return walletExist;
    }

    public long getNd106() {
        return nd106;
    }

    public String getDl148() {
        return dl148;
    }

    public int getNsms() {
        return nsms;
    }

    public int getNn130() {
        return nn130;
    }

    public int getNn132() {
        return nn132;
    }

    public int getNn141() {
        return nn141;
    }

    public String getLang() {
        return lang;
    }

    public String getCurrency() {
        return currency;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getClientOsName() {
        return clientOsName;
    }

    public String getToolName() {
        return toolName;
    }

    public static Creator<TokenEntity> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "TokenEntity{" +
                "userId='" + userId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", nexttoken='" + nexttoken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", tokenDate='" + tokenDate + '\'' +
                ", expiresIn=" + expiresIn +
                ", scope='" + scope + '\'' +
                ", code='" + code + '\'' +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", gender='" + gender + '\'' +
                ", mail='" + mail + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", avatar='" + avatar + '\'' +
                ", businessExist=" + businessExist +
                ", walletExist=" + walletExist +
                ", liked=" + liked +
                ", nd106=" + nd106 +
                ", dl148='" + dl148 + '\'' +
                ", nsms=" + nsms +
                ", nn130=" + nn130 +
                ", nn132=" + nn132 +
                ", nn141=" + nn141 +
                ", lang='" + lang + '\'' +
                ", currency='" + currency + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", requestHeader='" + requestHeader + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", browserName='" + browserName + '\'' +
                ", clientOsName='" + clientOsName + '\'' +
                ", toolName='" + toolName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.accessToken);
        dest.writeString(this.refreshToken);
        dest.writeString(this.nexttoken);
        dest.writeString(this.tokenType);
        dest.writeString(this.tokenDate);
        dest.writeLong(this.expiresIn);
        dest.writeString(this.scope);
        dest.writeString(this.code);
        dest.writeString(this.username);
        dest.writeString(this.firstname);
        dest.writeString(this.lastname);
        dest.writeString(this.fullname);
        dest.writeString(this.gender);
        dest.writeString(this.mail);
        dest.writeString(this.countryCode);
        dest.writeString(this.avatar);
        dest.writeInt(this.businessExist);
        dest.writeInt(this.walletExist);
        dest.writeInt(this.liked);
        dest.writeLong(this.nd106);
        dest.writeString(this.dl148);
        dest.writeInt(this.nsms);
        dest.writeInt(this.nn130);
        dest.writeInt(this.nn132);
        dest.writeInt(this.nn141);
        dest.writeString(this.lang);
        dest.writeString(this.currency);
        dest.writeString(this.ipAddress);
        dest.writeString(this.requestHeader);
        dest.writeString(this.deviceName);
        dest.writeString(this.browserName);
        dest.writeString(this.clientOsName);
        dest.writeString(this.toolName);
    }

    protected TokenEntity(Parcel in) {
        this.userId = in.readString();
        this.accessToken = in.readString();
        this.refreshToken = in.readString();
        this.nexttoken = in.readString();
        this.tokenType = in.readString();
        this.tokenDate = in.readString();
        this.expiresIn = in.readLong();
        this.scope = in.readString();
        this.code = in.readString();
        this.username = in.readString();
        this.firstname = in.readString();
        this.lastname = in.readString();
        this.fullname = in.readString();
        this.gender = in.readString();
        this.mail = in.readString();
        this.countryCode = in.readString();
        this.avatar = in.readString();
        this.businessExist = in.readInt();
        this.walletExist = in.readInt();
        this.liked = in.readInt();
        this.nd106 = in.readLong();
        this.dl148 = in.readString();
        this.nsms = in.readInt();
        this.nn130 = in.readInt();
        this.nn132 = in.readInt();
        this.nn141 = in.readInt();
        this.lang = in.readString();
        this.currency = in.readString();
        this.ipAddress = in.readString();
        this.requestHeader = in.readString();
        this.deviceName = in.readString();
        this.browserName = in.readString();
        this.clientOsName = in.readString();
        this.toolName = in.readString();
    }

    public static final Creator<TokenEntity> CREATOR = new Creator<TokenEntity>() {
        @Override
        public TokenEntity createFromParcel(Parcel source) {
            return new TokenEntity(source);
        }

        @Override
        public TokenEntity[] newArray(int size) {
            return new TokenEntity[size];
        }
    };
}
