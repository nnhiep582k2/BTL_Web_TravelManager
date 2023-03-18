package com.nnhiep.travelmanager.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
                COLUMN_NAME + " NVARCHAR(200) NOT NULL, " + COLUMN_AGE + " INTEGER, " + COLUMN_GENDER +
                " INTEGER, " + COLUMN_ACCOUNT + " VARCHAR(200) NOT NULL, " + COLUMN_PASSWORD + " VARCHAR(200) NOT NULL, "
                + COLUMN_PHONE + " VARCHAR(50), " + COLUMN_AVATAR + " BLOB);";
        db.execSQL(query);
    }

    /**
     * Thêm mới employee
     * @author nnhiep 17.03.2023
     */
    public long insertARecord(SQLiteDatabase db, String name, int age, int gender, String account, String pasword, String phone, String avatar) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AGE, age);
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_ACCOUNT, account);
        cv.put(COLUMN_PASSWORD, pasword);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_AVATAR, avatar);

        return db.insert(TABLE_NAME, null, cv);
    }

    /**
     * Hàm lấy thông tin người dùng
     * @return cursor
     * @author nnhiep 17.03.2023
     */
    public Cursor getData(SQLiteDatabase db) {
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = null;

        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /**
     * Hàm cập nhật thông tin người dùng
     * @author nnhiep 18.03.2023
     */
    public long updateData(SQLiteDatabase db, String row_id, String name, int age, int gender, String account, String pasword, String phone, String avatar) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AGE, age);
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_ACCOUNT, account);
        cv.put(COLUMN_PASSWORD, pasword);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_AVATAR, avatar);

        return db.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{row_id});
    }

    /**
     * Hàm xóa dữ liệu người dùng
     * @author nnhiep 18.03.2023
     */
    public long deleteARecord(SQLiteDatabase db, String row_id) {
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{row_id});
    }
}
