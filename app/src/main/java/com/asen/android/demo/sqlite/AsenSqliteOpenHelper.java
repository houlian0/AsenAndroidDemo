package com.asen.android.demo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.asen.android.demo.sqlite.bean.PersonInfo;
import com.asen.android.lib.base.core.sqlite.SDSQLiteOpenHelper;
import com.asen.android.lib.base.core.sqlite.utils.TableUtils;
import com.asen.android.lib.base.global.AppPath;

import java.sql.SQLException;

/**
 * 数据库示例操作代码
 *
 * @author Asen
 * @version v1.0
 * @date 2016/9/21 10:32
 */
class AsenSqliteOpenHelper extends SDSQLiteOpenHelper {

    private static final String SQLITE_NAME = "sqlite.db";

    private static final int SQLITE_VERSION = 1;

    private static AsenSqliteOpenHelper helper = null;

    /**
     * 初始化一个SDSQLiteOpenHelper对象.
     *
     * @param context 应用Context
     * @param path    要放到SDCard下的文件夹路径
     */
    private AsenSqliteOpenHelper(Context context, String path) {
        super(context, path, SQLITE_NAME, null, SQLITE_VERSION);
    }

    public static AsenSqliteOpenHelper getInstance(Context context) {
        if (null == helper) {
            synchronized (AsenSqliteOpenHelper.class) {
                if (null == helper) {
                    helper = new AsenSqliteOpenHelper(context, AppPath.getAppSqliteFile(context).getPath());
                }
            }
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            // 根据对象创建表，同时TableUtils中支持删表等操作
            TableUtils.createTableIfNotExists(db, PersonInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
