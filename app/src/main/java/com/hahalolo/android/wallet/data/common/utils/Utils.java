/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.common.utils;

import java.util.List;

/**
 * @author ndn
 * Created by ndn
 * Created on 7/2/18
 */
public final class Utils {

    private Utils() {

    }

    public static String makeSort(List<Object> objects) {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < objects.size(); i++) {
            Object o = objects.get(i);
            if (o instanceof Long) {
                stringBuilder.append((long) o);
            } else if (o instanceof Double) {
                stringBuilder.append((double) o);
            } else if (o instanceof String) {
                stringBuilder.append("\"");
                stringBuilder.append(o);
                stringBuilder.append("\"");
            }
            if (i < objects.size() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
