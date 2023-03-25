package com.nnhiep.travelmanager.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Bảng nhân viên
 * @author nnhiep 17.03.2023
 */
public class EmployeeTable {
    // region Thông tin bảng dữ liệu
    private static final String TABLE_NAME = "employee";
    private static final String COLUMN_ID = "employee_id";
    private static final String COLUMN_NAME = "employee_name";
    private static final String COLUMN_AGE = "employee_age";
    private static final String COLUMN_GENDER = "employee_gender";
    private static final String COLUMN_PHONE = "employee_phone";
    private static final String COLUMN_GMAIL = "employee_gmail";
    private static final String COLUMN_AVATAR = "employee_avatar";
    private static final String COLUMN_CREATED_DATE = "employee_created_date";
    private static final String COLUMN_MODIFIED_DATE = "employee_modified_date";
    // endregion

    /**
     * Hàm tạo mới bảng nhân viên
     * @param db - cơ sở dữ liệu của chương trình
     * @author nnhiep 18.03.2023
     */
    public void createTableEmployee(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " NVARCHAR(200) NOT NULL, " + COLUMN_AGE + " INTEGER NOT NULL, " + COLUMN_GENDER + " INTEGER, " +
                COLUMN_PHONE + " VARCHAR(50) NOT NULL, " + COLUMN_GMAIL + " NVARCHAR(200) NOT NULL, " + COLUMN_AVATAR + " BLOB, " + COLUMN_CREATED_DATE +
                " DATETIME, " + COLUMN_MODIFIED_DATE + " DATETIME);";
        db.execSQL(query);
    }
}
