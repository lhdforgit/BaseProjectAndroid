/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.presentation.payoutaccountdetail.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hahalolo.android.wallet.R;
import com.hahalolo.android.wallet.data.common.utils.Strings;

import java.util.Arrays;
import java.util.List;

public class SpinnerAdapter<T extends SpinnerAdapter.SpinnerItem> extends ArrayAdapter<T> {
    private int source = -1;
    private int dropResource = -1;

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
        this.source = resource;
    }
//    setDropDownViewResource


    @Override
    public void setDropDownViewResource(int resource) {
        super.setDropDownViewResource(resource);
        this.dropResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(dropResource == -1 ? source : dropResource, parent, false);
        }
        TextView textView = view.findViewById(android.R.id.text1);
        if (textView != null) {
            textView.setText(getText(position));
            textView.setSelected(true);
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (spinnerListener != null) {
//                        Strings.log("getStringArray onItemSelected " + getText(position));
//                        spinnerListener.onItemSelected(getItem(position));
//                    }
//                }
//            });
        }
        return view;
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    private String getText(int position) {
        T item = getItem(position);
        return item != null ? item.getText() : "";
    }

    private SpinnerListener<T> spinnerListener;

    public void setSpinnerListener(SpinnerListener<T> SpinnerListener) {
        this.spinnerListener = SpinnerListener;
    }

    public interface SpinnerListener<T> {
        void onItemSelected(T t);
    }

    public interface SpinnerItem<T> {
        String getText();

        T getModel();
    }
}
