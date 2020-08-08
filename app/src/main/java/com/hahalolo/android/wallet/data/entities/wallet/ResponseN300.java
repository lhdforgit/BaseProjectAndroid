/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.data.entities.wallet;

import com.hahalolo.android.wallet.data.entities.common.MongoResponseEntity;
import com.hahalolo.android.wallet.data.entities.common.StatusMongoEntity;

/**
 * @author ngannd
 * Create by ngannd on 19/03/2019
 */
public class ResponseN300 extends MongoResponseEntity<N300> {

    public ResponseN300(StatusMongoEntity status) {
        super(status);
    }

    protected ResponseN300(Class<N300> dataType) {
        super(dataType);
    }

    protected ResponseN300(Class<N300> dataType, StatusMongoEntity status) {
        super(dataType, status);
    }

    public ResponseN300(Class<N300> dataType, Object elements, StatusMongoEntity status) {
        super(dataType, elements, status);
    }

    public ResponseN300() {
        super(N300.class);
    }

    public static ResponseN300 error(int error) {
        return new ResponseN300(StatusMongoEntity.error(error));
    }
}
