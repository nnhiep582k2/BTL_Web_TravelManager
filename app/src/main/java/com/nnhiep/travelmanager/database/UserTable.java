package com.nnhiep.travelmanager.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Bảng người dùng
 * @author nnhiep 17.03.2023
 */
public class UserTable {
    // region Thông tin bảng dữ liệu
    private static final String TABLE_NAME = "user";
    private static final String COLUMN_ID = "user_id";
    private static final String COLUMN_NAME = "user_name";
    private static final String COLUMN_AGE = "user_age";
    private static final String COLUMN_GENDER = "user_gender";
    private static final String COLUMN_PHONE = "user_phone";
    private static final String COLUMN_ACCOUNT = "user_account";
    private static final String COLUMN_PASSWORD = "user_password";
    private static final String COLUMN_AVATAR = "user_avatar";
    // endregion

    /**
     * Hàm tạo mới bảng người dùng
     * @param db - cơ sở dữ liệu của chương trình
     * @author nnhiep 18.03.2023
     */
    public void createTableUser(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " NVARCHAR(200), " + COLUMN_AGE + " INTEGER, " + COLUMN_GENDER +
                " INTEGER, " + COLUMN_ACCOUNT + " VARCHAR(200) NOT NULL, " + COLUMN_PASSWORD + " VARCHAR(200) NOT NULL, "
                + COLUMN_PHONE + " VARCHAR(50), " + COLUMN_AVATAR + " BLOB);";
        db.execSQL(query);
    }
}
