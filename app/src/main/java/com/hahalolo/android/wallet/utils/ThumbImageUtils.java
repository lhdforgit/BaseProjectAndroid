/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.utils;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.text.TextUtils;
import android.webkit.URLUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.MalformedURLException;
import java.net.URL;

import static com.hahalolo.android.wallet.constant.MediaHost.MEDIA_DOMAIN;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.Quality.HIGH;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.Quality.LOW;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.Size.AVATAR_LARGE;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.Size.AVATAR_NORMAL;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.Size.AVATAR_SMALL;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.Size.AVATAR_WALL;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.Size.MEDIA_1_2_WIDTH;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.Size.MEDIA_1_3_WIDTH;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.Size.MEDIA_2_3_WIDTH;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.Size.MEDIA_FULL_WIDTH_1080;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.Size.MEDIA_FULL_WIDTH_1920;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.Size.MEDIA_FULL_WIDTH_640;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.Size.MEDIA_FULL_WIDTH_720;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.Size.MEDIA_THUMB_LOADING;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.TypeSize._16_9;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.TypeSize._1_1;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.TypeSize._1_2;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.TypeSize._2_1;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.TypeSize._3_2;
import static com.hahalolo.android.wallet.utils.ThumbImageUtils.TypeSize._AUTO;

/**
 * @author ngannd
 * Create by ngannd on 21/12/2018
 */
final public class ThumbImageUtils {


    @IntDef({AVATAR_SMALL, AVATAR_NORMAL, AVATAR_LARGE, AVATAR_WALL, MEDIA_FULL_WIDTH_720, MEDIA_FULL_WIDTH_1080, MEDIA_FULL_WIDTH_1920,
            MEDIA_THUMB_LOADING, MEDIA_FULL_WIDTH_640, MEDIA_1_2_WIDTH, MEDIA_1_3_WIDTH, MEDIA_2_3_WIDTH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Size {
        int AVATAR_SMALL = 96;
        int AVATAR_NORMAL = 128;
        int AVATAR_LARGE = 192;
        int AVATAR_WALL = 224;

        int MEDIA_THUMB_LOADING = 16;
        int MEDIA_FULL_WIDTH_640 = 640;
        int MEDIA_FULL_WIDTH_720 = 720;
        int MEDIA_FULL_WIDTH_1080 = 1080;
        int MEDIA_FULL_WIDTH_1920 = 1920;
        int MEDIA_1_2_WIDTH = 360;
        int MEDIA_1_3_WIDTH = 240;
        int MEDIA_2_3_WIDTH = 480;
    }

    @StringDef({LOW, HIGH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Quality {
        String LOW = "low";
        String HIGH = "high";
    }

    @IntDef({_16_9, _1_1, _2_1, _1_2, _3_2, _AUTO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TypeSize {
        int _16_9 = 0;
        int _1_1 = 1;
        int _2_1 = 2;
        int _1_2 = 3;
        int _3_2 = 4;
        int _AUTO = 5;
    }

    /**
     * Get thumb image url for view
     *
     * @param width    width view have set url image
     * @param url      url path of image
     * @param typeSize {@link TypeSize} type of size thumb
     * @return String thumb url
     */
    @Nullable
    public static String thumb(@Size int width, String url, @TypeSize int typeSize) {
        if (url != null) {
            String size = getSize(width, typeSize);
            String quality = (width == MEDIA_THUMB_LOADING) ? LOW : getQuality();
            String prefix = getPrefixThumb(url, size, quality);

            // get thumb of video
            String extension = FilenameUtils.getExtension(url);
            if (isVideo(extension.toLowerCase())) {
                extension = "jpg";
            }

            // Fix bug domain error
            return replaceDomain(prefix + "." + extension);
        }
        return "";
    }

    public static String getPrefixThumb(String url, String size, String quality) {
        return FilenameUtils.getPrefixExtension(replaceThumbUrl(url)) + "_" + size + "_" + quality;
    }

    public static String replaceDomain(String fullUrl) {
        try {
            if (URLUtil.isValidUrl(fullUrl)) {
                URL url = new URL(fullUrl);
                String host = url.getHost();
                if (URLUtil.isValidUrl(fullUrl.replace(host, MEDIA_DOMAIN))) {
                    fullUrl = fullUrl.replace(host, MEDIA_DOMAIN);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return fullUrl;
    }

    /**
     * Fix bug replace old link image with regex \_>*?\.
     *
     * @param url url thumb error
     * @return url path
     */
    public static String replaceThumbUrl(String url) {
        return url != null ? url.replaceAll("\\_.*?\\.", ".") : "";
    }

    public static String getSize(int width, @TypeSize int typeSize) {
        try {
            String height = "auto";
            String size;
            switch (typeSize) {
                case _16_9:
                    height = String.valueOf(width * 9 / 16);
                    break;
                case _1_1:
                    height = String.valueOf(width);
                    break;
                case _2_1:
                    height = String.valueOf(width / 2);
                    break;
                case _1_2:
                    height = String.valueOf(width * 2);
                    break;
                case _3_2:
                    height = String.valueOf(width * 2 / 3);
                    break;
                case _AUTO:
                    height = "auto";
                    break;
            }
            size = width + "x" + height;
            return size;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getQuality() {
        try {
            return NetworkUtils.isConnectedFast() ? Quality.HIGH : LOW;
        } catch (Exception e) {
            e.printStackTrace();
            return LOW;
        }
    }

    public static boolean isGif(String url) {
        if (url == null) {
            return false;
        }
        if (!TextUtils.isEmpty(url) && url.contains(CHARACTER_EXTERSION)) {
            url = FilenameUtils.getExtension(url);
        }
        return TextUtils.equals(url.toLowerCase(), "gif");
    }

    public static boolean urlIsImage(String url) {
        if (url == null) {
            return false;
        }
        if (!TextUtils.isEmpty(url) && url.contains(CHARACTER_EXTERSION)) {
            url = FilenameUtils.getExtension(url);
        }

        return isImage(url);
    }

    public static boolean isImage(String extension) {
        if (extension == null) {
            return false;
        }
        if (!TextUtils.isEmpty(extension) && extension.contains(CHARACTER_EXTERSION)) {
            extension = FilenameUtils.getExtension(extension);
        }

        return TextUtils.equals(extension, "png")
                || TextUtils.equals(extension, "PNG")
                || TextUtils.equals(extension, "jpg")
                || TextUtils.equals(extension, "jpeg")
                || TextUtils.equals(extension, "JGP")
                || TextUtils.equals(extension, "JPEG")
                || TextUtils.equals(extension, "webp");
    }

    public static boolean urlIsVideo(String url) {
        if (url == null) {
            return false;
        }
        if (!TextUtils.isEmpty(url) && url.contains(CHARACTER_EXTERSION)) {
            url = FilenameUtils.getExtension(url);
        }
        return isVideo(url);
    }

    public static boolean isVideo(String extension) {
        if (extension == null) {
            return false;
        }
        if (!TextUtils.isEmpty(extension) && extension.contains(CHARACTER_EXTERSION)) {
            extension = FilenameUtils.getExtension(extension);
        }
        return TextUtils.equals(extension, "webm")
                || TextUtils.equals(extension, "ogg")
                || TextUtils.equals(extension, "3gp")
                || TextUtils.equals(extension, "mov")
                || TextUtils.equals(extension, "mpd")
                || TextUtils.equals(extension, "ism")
                || TextUtils.equals(extension, "m3u8")
                || TextUtils.equals(extension, "aac")
                || TextUtils.equals(extension, "mkv")
                || TextUtils.equals(extension, "ogg")
                || TextUtils.equals(extension, "mp4");

    }

    public static final String CHARACTER_EXTERSION = ".";
}
