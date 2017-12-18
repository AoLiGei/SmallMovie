package com.happy.bwiesample.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Db extends SQLiteOpenHelper {

    private static final String TAG = "Db";
    /**
     * 数据库名
     */
    private String mDbName;

    /**
     * 版本
     */
    private int mVersion;

    /**
     * 表名
     */
    private String mTblName;

    /**
     * 创建表命令
     */
    private String mCommand;

    /**
     * 构造方法：初始化上下文环境、数据库名、版本、表名、创建表命令
     *
     * @param context 上下文环境
     * @param dbName  数据库名
     * @param version 版本
     * @param tblName 表名
     * @param command 创建表命令
     */
    public Db(Context context, String dbName, int version, String tblName, String command) {
        super(context, dbName, null, version);

        mDbName = dbName;
        mVersion = version;
        mTblName = tblName;
        mCommand = command;

        Log.d(TAG, "constructor dbName: " + getDbName() + ", version: " + getVersion() + ", tblName: " + getTblName() + ", command: " + getCommand());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(mCommand);

        Log.d(TAG, "onCreate command: " + mCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + mTblName);
        onCreate(db);

        Log.d(TAG, "onUpgrade command: DROP TABLE IF EXISTS " + mTblName + ", oldVersion: " + oldVersion + ", newVersion: " + newVersion);
    }

    /**
     * 执行一条SQL
     *
     * @param sql SQL Command
     */
    public void execSQL(String sql) {
        SQLiteDatabase db;

        db = getWritableDatabase();
        db.execSQL(sql);
        db.close();

        Log.d(TAG, "execSQL command: " + sql);
    }

    /**
     * 执行一条SQL
     *
     * @param sql      SQL Command
     * @param bindArgs a Object Array
     */
    public void execSQL(String sql, Object[] bindArgs) {
        SQLiteDatabase db;

        db = getWritableDatabase();
        db.execSQL(sql, bindArgs);
        db.close();

        Log.d(TAG, "execSQL command: " + sql + ", bindArgs: " + joinString(bindArgs));
    }

    /**
     * 插入一条记录
     * <pre>
     * ContentValues values = new ContentValues();
     * values.put("title", "标题");
     * values.put("content", "内容");
     * values.put("dt_created", "2015-10-18");
     * Db db = new Db(context, DB_NAME, VERSION, TABLE_NAME, COMMAND);
     * long lastInsertId = db.insert(null, values);
     * </pre>
     *
     * @param nullColumnHack 空列
     * @param values         行数据集合
     * @return 最后一次插入行的Id，如果出错，返回-1
     */
    public long insert(String nullColumnHack, ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        long lastInsertId = db.insert(getTblName(), nullColumnHack, values);
        db.close();

        Log.d(TAG, "insert tblName: " + getTblName() + ", nullColumnHack: " + nullColumnHack + ", values: " + values);
        return lastInsertId;
    }

    /**
     * 更新记录
     *
     * @param values      行数据集合
     * @param whereClause 更新条件
     * @param whereArgs   条件参数，a String Array
     * @return 更新操作影响的行数
     */
    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = getWritableDatabase();
        int rowCount = db.update(getTblName(), values, whereClause, whereArgs);
        db.close();

        Log.d(TAG, "update tblName: " + getTblName() + ", values: " + values + ", whereClause: " + whereClause + ", whereArgs: " + joinString(whereArgs));
        return rowCount;
    }

    /**
     * 删除记录
     *
     * @param whereClause 删除条件
     * @param whereArgs   条件参数，a String Array
     * @return 删除操作影响的行数
     */
    public int delete(String whereClause, String[] whereArgs) {
        SQLiteDatabase db = getWritableDatabase();
        int rowCount = db.delete(getTblName(), whereClause, whereArgs);
        db.close();

        Log.d(TAG, "delete tblName: " + getTblName() + ", whereClause: " + whereClause + ", whereArgs: " + joinString(whereArgs));
        return rowCount;
    }

    /**
     * 获取数据库名
     *
     * @return a String, or null
     */
    public String getDbName() {
        return mDbName;
    }

    /**
     * 获取版本
     *
     * @return int
     */
    public int getVersion() {
        return mVersion;
    }

    /**
     * 获取表名
     *
     * @return a String, or null
     */
    public String getTblName() {
        return mTblName;
    }

    /**
     * 获取创建表命令
     *
     * @return a String, or null
     */
    public String getCommand() {
        return mCommand;
    }

    /**
     * 将对象数组拼接成字符串
     *
     * @param params a Object Array
     * @return a String
     */
    public String joinString(Object[] params) {
        StringBuilder data = new StringBuilder();

        String joinStr = "";
        for (Object value : params) {
            data.append(joinStr).append(value);
            joinStr = ", ";
        }

        return data.toString();
    }

}
