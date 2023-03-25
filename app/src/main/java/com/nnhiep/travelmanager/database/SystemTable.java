package com.nnhiep.travelmanager.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Bảng thông tin hệ thống
 * @author nnhiep 19.03.2023
 */
public class SystemTable {
    // region Thông tin bảng dữ liệu
    private static final String TABLE_NAME = "system";
    private static final String COLUMN_ID = "system_id";
    private static final String COLUMN_ISLOGIN = "system_is_login";
    // endregion

    /**
     * Hàm tạo mới bảng hệ thống
     * @param db - cơ sở dữ liệu của chương trình
     * @author nnhiep 18.03.2023
     */
    public void createTableSystem(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ISLOGIN + " BOOLEAN);";
        db.execSQL(query);
    }
}
