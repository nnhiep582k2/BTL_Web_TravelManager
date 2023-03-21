package com.nnhiep.travelmanager.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import javax.xml.namespace.QName;

/**
 * Bảng nhân viên - người dùng
 * @author nnhiep 17.03.2023
 */
public class Employee {
    // region Thông tin bảng dữ liệu
    private static final String TABLE_NAME = "employee";
    private static final String COLUMN_ID = "employee_id";
    private static final String COLUMN_NAME = "employee_name";
    private static final String COLUMN_AGE = "employee_age";
    private static final String COLUMN_GENDER = "employee_gender";
    private static final String COLUMN_PHONE = "employee_phone";
    private static final String COLUMN_ACCOUNT = "employee_account";
    private static final String COLUMN_PASSWORD = "employee_password";
    private static final String COLUMN_AVATAR = "employee_avatar";
    // endregion

    /**
     * Hàm tạo mới bảng người dùng
     * @param db - cơ sở dữ liệu của chương trình
     * @author nnhiep 18.03.2023
     */
    public void createTableEmployee(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " NVARCHAR(200), " + COLUMN_AGE + " INTEGER, " + COLUMN_GENDER +
                " INTEGER, " + COLUMN_ACCOUNT + " VARCHAR(200) NOT NULL, " + COLUMN_PASSWORD + " VARCHAR(200) NOT NULL, "
                + COLUMN_PHONE + " VARCHAR(50), " + COLUMN_AVATAR + " BLOB);";
        db.execSQL(query);
    }
}


