package com.happy.bwiesample.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 蔡华铎 on 2017/12/22.
 */

public class SearchDB extends SQLiteOpenHelper {

    public SearchDB(Context context) {
        super(context, "searchDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table search (id integer primary key autoincrement,name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
