/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author ngannd
 * Create by ngannd on 13/04/2019
 */
public final class RecyclerViewUtils {

    /**
     * Save state RecyclerView before refresh
     *
     * @param recyclerView {@link RecyclerView}
     */
    public static void saveState(RecyclerView recyclerView, Parcelable recyclerViewState) {
        recyclerViewState = recyclerView.getLayoutManager() != null
                ? recyclerView.getLayoutManager().onSaveInstanceState() : null;
    }

    /**
     * Restore state RecyclerView after refresh
     *
     * @param recyclerView {@link RecyclerView}
     */
    public static void restoreState(RecyclerView recyclerView, Parcelable recyclerViewState) {
        if (recyclerViewState != null && recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        }
    }

    /**
     * Performance scrolling recyclerview
     * https://stackoverflow.com/questions/27188536/recyclerview-scrolling-performance
     * https://medium.com/@kkamilbekar/recyclerview-scrolling-performance-ff05a3a79262
     * <p>
     * TODO: https://medium.com/@programmerr47/recyclerview-item-optimizations-cae1aed0c321
     *
     * @param recyclerView {@link RecyclerView}
     */
    @SuppressLint("ClickableViewAccessibility")
    public static void optimization(RecyclerView recyclerView, Activity activity) {
        // Use the default RecyclerView drawing cache methods and tweak them according to your case.
        // You don't need third party library to do so:
        // Chổ này nếu mình Cache Size lớn, thì sẽ sãy ra lỗi OutOffMemory. Nên việc cache không nên thực hiện.
        recyclerView.setItemViewCacheSize(0);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        // Cancel animation for recyclerview
        recyclerView.setAnimation(null);

        /*
         * TODO: Make all elements of the RecyclerView with the same height
         * recyclerView.setHasFixedSize(true); made a BIG difference for me.
         * Note that it does not require to have items of the same size!
         * However, it does requires the RecyclerView to have fixed width and height (i.e. not wrap_content).
         * recyclerView.setHasFixedSize(true);
         */

        // Ẩn bàn phím khi người dùng thực hiện hành động cuộn feed
        recyclerView.setOnTouchListener((v, event) -> {
            try {
                if (activity != null) {
                    KeyboardUtils.hideSoftInput(activity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    public static boolean recIsAtTop(RecyclerView recyclerView) {
        boolean isAtTop = true;
        try {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (linearLayoutManager != null) {
                int pos = linearLayoutManager.findFirstVisibleItemPosition();
                View view = linearLayoutManager.findViewByPosition(pos);
                if (view != null && (view.getTop() > 0 || pos > 0)) {
                    isAtTop = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAtTop;
    }
}
