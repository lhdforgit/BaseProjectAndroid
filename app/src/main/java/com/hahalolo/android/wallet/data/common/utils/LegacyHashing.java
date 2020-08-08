/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.common.utils;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * @author ndn
 * Created by ndn
 * Created on 8/1/18
 * Provides an md5 and sha1 hash function without producing deprecation warnings.
 */
public class LegacyHashing {

    @SuppressWarnings("deprecation")
    public static HashFunction md5() {
        return Hashing.md5();
    }

    @SuppressWarnings("deprecation")
    public static HashFunction sha1() {
        return Hashing.sha1();
    }
}
