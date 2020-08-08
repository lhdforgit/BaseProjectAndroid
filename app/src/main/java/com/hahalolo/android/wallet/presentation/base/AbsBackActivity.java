/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

/**
 * @author ndn
 * Created by ndn
 * Created on 10/1/18
 */
public abstract class AbsBackActivity extends AbsActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract void initActionBar();

}