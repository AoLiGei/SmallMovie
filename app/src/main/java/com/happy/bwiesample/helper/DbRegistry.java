package com.happy.bwiesample.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public final class DbRegistry {
    /**
     * 用于寄存全局数据的DB名称
     */
    private static final String DB_NAME = "mvp_db_registry_setting";

    /**
     * 版本
     */
    private static final int VERSION = 1;

    /**
     * 用于寄存全局数据的表名称
     */
    private static final String TABLE_NAME = "setting";

    /**
     * 创建表命令
     */
    private static final String COMMAND = "CREATE TABLE IF NOT EXISTS setting (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, setting_key VARCHAR(50) UNIQUE NOT NULL, setting_value TEXT NOT NULL);";

    /**
     * 寄存主键的键名
     */
    private static final String SETTING_PK = "id";

    /**
     * 寄存Key的键名
     */
    private static final String SETTING_KEY = "setting_key";

    /**
     * 寄存Value的键名
     */
    private static final String SETTING_VALUE = "setting_value";

    /**
     * 上下文环境
     */
    private final Context mAppContext;

    /**
     * 用于寄存全局数据的DB对象
     */
    private Db mDb;

    /**
     * 构造方法：初始化上下文环境、DB对象
     */
    public DbRegistry(Context context) {
        mAppContext = context;
        mDb = new Db(getAppContext(), DB_NAME, VERSION, TABLE_NAME, COMMAND);
    }


    /**
     * 通过名称获取整数值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public int getInt(String key, int defaultValue) {
        String value = getString(key, null);
        return (value == null) ? defaultValue : Integer.valueOf(value);
    }

    /**
     * 设置名称和整数值
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns True, or False
     */
    public boolean putInt(String key, int value) {
        return putString(key, String.valueOf(value));
    }

    /**
     * 通过名称获取长整数值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public long getLong(String key, long defaultValue) {
        String value = getString(key, null);
        return (value == null) ? defaultValue : Long.valueOf(value);
    }

    /**
     * 设置名称和长整数值
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns True, or False
     */
    public boolean putLong(String key, long value) {
        return putString(key, String.valueOf(value));
    }

    /**
     * 通过名称获取浮点值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public float getFloat(String key, float defaultValue) {
        String value = getString(key, null);
        return (value == null) ? defaultValue : Float.valueOf(value);
    }

    /**
     * 设置名称和浮点值
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns True, or False
     */
    public boolean putFloat(String key, float value) {
        return putString(key, String.valueOf(value));
    }

    /**
     * 通过名称获取布尔值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return (getInt(key, (defaultValue ? 1 : 0)) == 1);
    }

    /**
     * 设置名称和布尔值
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns True, or False
     */
    public boolean putBoolean(String key, boolean value) {
        return putInt(key, (value ? 1 : 0));
    }

    /**
     * 通过名称获取字符串值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public String getString(String key, String defaultValue) {
        String value = defaultValue;

        if (key == null || "".equals(key)) {
            return value;
        }

        SQLiteDatabase dbReadable = mDb.getReadableDatabase();
        Cursor cursor = dbReadable.query(TABLE_NAME, new String[]{SETTING_VALUE}, SETTING_KEY + " = ?", new String[]{key}, null, null, null);
        if (cursor.moveToNext()) {
            value = cursor.getString(cursor.getColumnIndex(SETTING_VALUE));
        }

        cursor.close();
        dbReadable.close();
        return value;
    }

    /**
     * 设置名称和字符串值
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns True, or False
     */
    public boolean putString(String key, String value) {
        if (key == null || "".equals(key) || value == null) {
            return false;
        }

        ContentValues values = new ContentValues();
        values.put(SETTING_KEY, key);
        values.put(SETTING_VALUE, value);

        boolean isNewRecord = (getString(key, null) == null);

        if (isNewRecord) {
            long lastInsertId = mDb.insert(null, values);
            if (lastInsertId > 0) {
                return true;
            }
        } else {
            int rowCount = mDb.update(values, SETTING_KEY + " = ?", new String[]{key});
            if (rowCount > 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * 通过名称删除数据
     *
     * @param key The name of the preference to remove.
     * @return Returns True, or False
     */
    public boolean remove(String key) {
        if (key == null || "".equals(key)) {
            return false;
        }

        int rowCount = mDb.delete(SETTING_KEY + " = ?", new String[]{key});
        return (rowCount > 0);
    }

    /**
     * 获取上下文环境
     *
     * @return a Context
     */
    public Context getAppContext() {
        return mAppContext;
    }

}
