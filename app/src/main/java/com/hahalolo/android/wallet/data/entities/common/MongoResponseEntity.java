/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.entities.common;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.Collections;
import java.util.List;

/**
 * @author ngannd
 * Create by ngannd on 15/11/2018
 */
public class MongoResponseEntity<T> implements Parcelable {

    private Class<T> dataType;

    @SerializedName("status")
    @Expose
    public StatusMongoEntity status;

    @SerializedName("elements")
    @Expose
    public Object elements = null;

    public MongoResponseEntity(StatusMongoEntity status) {
        this.status = status;
    }

    protected MongoResponseEntity(Class<T> dataType) {
        this.dataType = dataType;
    }

    protected MongoResponseEntity(Class<T> dataType, StatusMongoEntity status) {
        this.dataType = dataType;
        this.status = status;
    }

    public MongoResponseEntity(Class<T> dataType,Object elements, StatusMongoEntity status) {
        this.dataType = dataType;
        this.status = status;
        this.elements = elements;
    }

    public static MongoResponseEntity error(int error) {
        return new MongoResponseEntity(StatusMongoEntity.error(error));
    }

    public T getFirstElement() {
        try {
            if (getElementsEntity() != null && !getElementsEntity().isEmpty()) {
                return getElementsEntity().get(0);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public T getLastElement() {
        try {
            if (getElementsEntity() != null && !getElementsEntity().isEmpty()) {
                return getElementsEntity().get(getElementsEntity().size() - 1);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public List<T> getElementsEntity() {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(this.elements);
            return gson.fromJson(json, TypeToken.getParameterized(List.class, dataType).getType());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public StatusMongoEntity getStatus() {
        return status;
    }

    public Object getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "MongoResponseEntity{" +
                "dataType=" + dataType +
                ", status=" + status +
                ", elements=" + elements +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.status, flags);
        dest.writeValue(this.elements);
    }

    protected MongoResponseEntity(Parcel in) {
        this.status = in.readParcelable(StatusMongoEntity.class.getClassLoader());
        this.elements = in.readValue(Object.class.getClassLoader());
    }

    public static final Creator<MongoResponseEntity> CREATOR = new Creator<MongoResponseEntity>() {
        @Override
        public MongoResponseEntity createFromParcel(Parcel source) {
            return new MongoResponseEntity(source);
        }

        @Override
        public MongoResponseEntity[] newArray(int size) {
            return new MongoResponseEntity[size];
        }
    };
}
