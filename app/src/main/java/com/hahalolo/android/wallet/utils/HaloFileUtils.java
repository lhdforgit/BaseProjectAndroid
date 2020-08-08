/*
 *
 *  * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *  *
 *  * https://help.hahalolo.com/commercial_terms/
 *
 */

package com.hahalolo.android.wallet.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import java.io.File;

public class HaloFileUtils {

    public static Uri addImageToGallery(final String filePath, final Context context) {
        if (!TextUtils.isEmpty(filePath)) {
            if (ThumbImageUtils.isImage(filePath) || ThumbImageUtils.isGif(filePath)) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/" + FilenameUtils.getExtension(filePath));
                values.put(MediaStore.MediaColumns.DATA, filePath);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else if (ThumbImageUtils.isVideo(filePath)) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Video.Media.DATE_TAKEN, System.currentTimeMillis());
                values.put(MediaStore.Video.Media.MIME_TYPE, "video/" + FilenameUtils.getExtension(filePath));
                values.put(MediaStore.MediaColumns.DATA, filePath);
                return context.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
            }
        }
        return null;
    }

    private static final String currentFolder =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
                    + File.separator + "Hahalolo";


    public static boolean isDownLoaded(String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            return new File(
                    currentFolder
                            + File.separator + fileName).exists();
        }
        return false;
    }

    public static String getLocalMedia(String url) {
        return Environment.DIRECTORY_DOWNLOADS + File.separator + "Hahalolo" + File.separator + FilenameUtils.getName(url);
    }

    public static String getPathLocalMedia(String url) {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "Hahalolo" + File.separator + FilenameUtils.getName(url);
    }


    public static void clearFile(String fileName) {
        File file = new File(
                currentFolder
                        + File.separator + fileName);
        if (file.exists()) {
            file.delete();
        }
    }


    public static File createFolderDownload() {
        return new File(
                currentFolder
                        + File.separator);
    }

    public static File createFileDownload(String fileName) {
        return new File(
                currentFolder
                        + File.separator + fileName);
    }

    public static void openFolderDownload(Context context, String fileName, String typeFile) {
        try {
            String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(FilenameUtils.getExtension(fileName));

            openFile(context, currentFolder + File.separator + fileName);

        } catch (Exception e) {
        }
    }

    private static void openFile(Context context, String url) {

        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
            // Word document
            intent.setDataAndType(uri, "application/msword");
        } else if (url.toString().contains(".pdf")) {
            // PDF file
            intent.setDataAndType(uri, "application/pdf");
        } else if (url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
            // Powerpoint file
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        } else if (url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
            // Excel file
            intent.setDataAndType(uri, "application/vnd.ms-excel");
        } else if (url.toString().contains(".zip") || url.toString().contains(".rar")) {
            // WAV audio file
            intent.setDataAndType(uri, "application/x-wav");
        } else if (url.toString().contains(".rtf")) {
            // RTF file
            intent.setDataAndType(uri, "application/rtf");
        } else if (url.toString().contains(".wav") || url.toString().contains(".mp3")) {
            // WAV audio file
            intent.setDataAndType(uri, "audio/x-wav");
        } else if (url.toString().contains(".gif")) {
            // GIF file
            intent.setDataAndType(uri, "image/gif");
        } else if (url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
            // JPG file
            intent.setDataAndType(uri, "image/jpeg");
        } else if (url.toString().contains(".txt")) {
            // Text file
            intent.setDataAndType(uri, "text/plain");
        } else if (url.toString().contains(".3gp") || url.toString().contains(".mpg") || url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
            // Video files
            intent.setDataAndType(uri, "video/*");
        } else {
            //if you want you can also define the intent type for any other file
            //additionally use else clause below, to manage other unknown extensions
            //in this case, Android will show all applications installed on the device
            //so you can choose which application to use
            intent.setDataAndType(uri, "*/*");
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }


}
