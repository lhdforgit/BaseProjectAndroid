/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.cache.serializer;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author ndn
 * Created by ndn
 * Created on 5/14/18.
 * <p>
 * Json Serializer/Deserializer.
 */
@Singleton
public class Serializer {

    private final Gson gson = new Gson();

    @Inject
    Serializer() {
    }

    /**
     * Serialize an object to Json.
     *
     * @param object to serialize.
     */
    public String serialize(Object object, Class clazz) {
        return gson.toJson(object, clazz);
    }

    /**
     * Deserialize a json representation of an object.
     *
     * @param string A json string to deserialize.
     */
    public <T> T deserialize(String string, Class<T> clazz) {
        return gson.fromJson(string, clazz);
    }
}
