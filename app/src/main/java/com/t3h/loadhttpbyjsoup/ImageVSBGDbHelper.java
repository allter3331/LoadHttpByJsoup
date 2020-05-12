package com.t3h.loadhttpbyjsoup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ImageVSBGDbHelper extends SQLiteOpenHelper {

    private static final String TAG = "ImageVSBGDbHelper";
    private static final String DATABASE_NAME = "myproduct.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_Image_VSBG = "ImageVSBG";


    public ImageVSBGDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Phương thức này tự động gọi nếu storage chưa có DATABASE_NAME
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(TAG, "Create table");
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS " + TABLE_Image_VSBG + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR (255) NOT NULL, " +
                "urlPath VARCHAR (255) NOT NULL" +
                ")";

        db.execSQL(queryCreateTable);
    }

    //Phương thức này tự động gọi khi đã có DB trên Storage, nhưng phiên bản khác
    //với DATABASE_VERSION
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Image_VSBG);
        //Tiến hành tạo bảng mới
        onCreate(db);
    }

    public List<ImageVSBG> getAllImageVSBG() {

        List<ImageVSBG> imageVSBGList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, urlPath from " + TABLE_Image_VSBG, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(0);
            String urlPath = cursor.getString(1);

            imageVSBGList.add(new ImageVSBG(name, urlPath));
            cursor.moveToNext();
        }

        cursor.close();

        return imageVSBGList;
    }

    public void insertImageVSBG(ImageVSBG imageVSBG) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_Image_VSBG + " WHERE urlPath = ?", new String[]{imageVSBG.getUrlPath()});
        if (!(cursor != null && cursor.getCount() > 0)) {
            db.execSQL("INSERT INTO ImageVSBG (name, urlPath) VALUES (?,?)",
                    new String[]{imageVSBG.getName(), imageVSBG.getUrlPath()});
        }
    }


    public void deleteImageVSBG(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM product where name = ?", new String[]{name});
    }

}
