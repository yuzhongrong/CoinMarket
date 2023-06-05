package com.zksg.kudoud.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.zksg.kudoud.R;
import com.zksg.kudoud.beans.FileApkItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {


    public static final int TYPE_DOC = 0;

    public static final int TYPE_APK = 1;

    public static final int TYPE_ZIP = 2;

    public static final int TYPE_GZ = 3;


    public static List<FileApkItem> getFilesByType(Context context) {
        List<FileApkItem> items= getApkList(context).stream().
                flatMap(file ->Stream.of(new FileApkItem(file)))
                .collect(Collectors.toList());

        return items;
    }


    public static List<File> getApkList(Context context) {
        List<File> apkFiles = new ArrayList<>();
        String[] projection = {MediaStore.Files.FileColumns.DATA};
        String selection = MediaStore.Files.FileColumns.DATA + " like '%.apk'";
        Cursor cursor = context.getContentResolver().query(MediaStore.Files.getContentUri("external"), projection, selection, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range")
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
                apkFiles.add(new File(path));
            }
            cursor.close();
        }
        return apkFiles;
    }


    private static List<FileApkItem> getFilesByType(Context context,int fileType) {
        List<FileApkItem> files = new ArrayList<>();
        // 扫描files文件库
        Cursor c = null;
        try {
            c = context.getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_id", "_data", "_size","_display_name","title"}, null, null, null);
            int dataindex = c.getColumnIndex(MediaStore.Files.FileColumns.DATA);
            int sizeindex = c.getColumnIndex(MediaStore.Files.FileColumns.SIZE);
            int titleindex = c.getColumnIndex(MediaStore.Files.FileColumns.TITLE);


            while (c.moveToNext()) {
                String path = c.getString(dataindex);
                String title=c.getString(titleindex);

                if (FileUtils.getFileType(path) == fileType) {
                    if (!FileUtils.isExists(path)) {
                        continue;
                    }
                    long size = c.getLong(sizeindex);
                    FileApkItem fileBean = new FileApkItem(path);
                    files.add(fileBean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return files;
    }


    public static void searchForApkFiles(Context context) {
        String[] projection = {
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA
        };
       String selection =
                MediaStore.Files.FileColumns.MIME_TYPE + "=? OR " +
                        MediaStore.Files.FileColumns.MIME_TYPE + "=? OR " +
                        MediaStore.Files.FileColumns.MIME_TYPE + "=?";
        String[] selectionArgs = {
                "application/vnd.android.package-archive",
                "application/java-archive",
                "application/x-archive"
        };
        Uri queryUri = MediaStore.Files.getContentUri("external");

        try (Cursor cursor = context.getContentResolver().query(
                queryUri,
                projection,
                selection,
                selectionArgs,
                null
        )) {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String filePath = cursor.getString(
                            cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
                    File apkFile = new File(filePath);
                    Log.d("----apkFile->",apkFile.getPath());
                    // 处理apk文件
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<FileApkItem> getAllApkFilePaths(Context context,int fileType) {
        List<FileApkItem> files = new ArrayList<>();
        // 扫描files文件库
        Cursor c = null;
        try {
            c = context.getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_id", "_data", "_size","_display_name","title"}, null, null, null);
            int dataindex = c.getColumnIndex(MediaStore.Files.FileColumns.DATA);
            int sizeindex = c.getColumnIndex(MediaStore.Files.FileColumns.SIZE);
            int titleindex = c.getColumnIndex(MediaStore.Files.FileColumns.TITLE);


            while (c.moveToNext()) {
                String path = c.getString(dataindex);
                String title=c.getString(titleindex);

                if (FileUtils.getFileType(path) == fileType) {
                    if (!FileUtils.isExists(path)) {
                        continue;
                    }
                    long size = c.getLong(sizeindex);
                    FileApkItem fileBean = new FileApkItem(path);
                    files.add(fileBean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return files;
    }

    private static int getFileType(String path) {
        path = path.toLowerCase();
        if (path.endsWith(".doc") || path.endsWith(".docx") || path.endsWith(".xls") || path.endsWith(".xlsx")
                || path.endsWith(".ppt") || path.endsWith(".pptx")) {
            return TYPE_DOC;
        }else if (path.endsWith(".apk")) {
            return TYPE_APK;
        }else if (path.endsWith(".zip") || path.endsWith(".rar") || path.endsWith(".tar")) {
            return TYPE_ZIP;
        }else if( path.endsWith(".gz")){
            return TYPE_GZ;
        }
        else{
            return -1;
        }
    }

    public static boolean isExists(String path) {
        File file = new File(path);
        return file.exists();
    }


    private static int getFileIconByPath(String path){

        path = path.toLowerCase();
        int iconId = R.mipmap.unknow_file_icon;
        if (path.endsWith(".txt")){
            iconId = R.mipmap.type_txt;
        }else if(path.endsWith(".doc") || path.endsWith(".docx")){
            iconId = R.mipmap.type_doc;
        }else if(path.endsWith(".xls") || path.endsWith(".xlsx")){
            iconId = R.mipmap.type_xls;
        }else if(path.endsWith(".ppt") || path.endsWith(".pptx")){
            iconId = R.mipmap.type_ppt;
        }else if(path.endsWith(".xml")){
            iconId = R.mipmap.type_xml;
        }else if(path.endsWith(".htm") || path.endsWith(".html")){
            iconId = R.mipmap.type_html;
        }else if(path.endsWith(".gz")){
            iconId = R.mipmap.type_gz;
        }
        return iconId;
    }


}
