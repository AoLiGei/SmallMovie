package com.happy.bwiesample.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 蔡华铎 on 2017/12/22.
 */

public class SearchDBHelper {

    private SQLiteDatabase db;
    @Inject
    public SearchDBHelper(Context context) {
        SearchDB searchDB = new SearchDB(context);
        db = searchDB.getWritableDatabase();
    }

    //添加数据
    public void addData(String str) {
        if (str != null && !str.equals("")){
            List<String> list = queryData();
            if (!list.contains(str)){
                ContentValues values = new ContentValues();
                values.put("name", str);
                db.insert("search", null, values);
            }
        }

    }

    //查询数据出来
    public List<String> queryData(){
        List<String> list = new ArrayList<>();
            Cursor query = db.query("search", null, null, null, null, null, null);
            while (query.moveToNext()){
                String name = query.getString(query.getColumnIndex("name"));
                list.add(name);
            }
        return list;
    }

    public boolean dbIsEntry(){
        return db.query("search", null, null, null, null, null, null).moveToNext();
    }

    //删除数据
    public int deleteData(){
        int count = db.delete("search", null, null);
        return count;
    }

}
