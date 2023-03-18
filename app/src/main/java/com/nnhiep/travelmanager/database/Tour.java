package com.nnhiep.travelmanager.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.Date;

/**
 * Bảng tour du lịch
 * @author nnhiep 17.03.2023
 */
public class Tour {
    // region Thông tin bảng dữ liệu
    private static final String TABLE_NAME = "tour";
    private static final String COLUMN_ID = "tour_id";
    private static final String COLUMN_TITLE = "tour_title";
    private static final String COLUMN_IMAGE = "tour_image";
    private static final String COLUMN_PRICE = "tour_price";
    private static final String COLUMN_START_DATE = "start_date";
    private static final String COLUMN_END_DATE = "end_date";
    private static final String COLUMN_ISFAVOR = "tour_isFavor";
    private static final String COLUMN_ISCHECKED = "tour_isChecked";
    private static final String COLUMN_CREATED_DATE = "employee_created_date";
    private static final String COLUMN_CREATED_BY = "employee_created_by";
    private static final String COLUMN_MODIFIED_DATE = "employee_modified_date";
    private static final String COLUMN_MODIFIED_BY = "employee_modified_by";
    // endregion

    /**
     * Hàm tạo mới bảng tour
     * @param db - cơ sở dữ liệu của chương trình
     * @author nnhiep 18.03.2023
     */
    public void createTableTour(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " NVARCHAR(200) NOT NULL, " + COLUMN_IMAGE + " BLOB, " + COLUMN_PRICE + " DOUBLE NOT NULL, " +
                COLUMN_START_DATE + " DATETIME NOT NULL, " + COLUMN_END_DATE +
                " DATETIME NOT NULL, " + COLUMN_ISFAVOR + " BOOLEAN, " + COLUMN_ISCHECKED + " BOOLEAN, "
                + COLUMN_CREATED_DATE + " DATETIME, " + COLUMN_CREATED_BY +
                " NVARCHAR(200), " + COLUMN_MODIFIED_DATE + " DATETIME, " + COLUMN_MODIFIED_BY + " NVARCHAR(200));";
        db.execSQL(query);
    }

    /**
     * Thêm mới tour
     * @author nnhiep 17.03.2023
     */
    public long insertARecord(SQLiteDatabase db, String title, double price, String start_date, String end_date, boolean isFavor, boolean isChecked, String createdBy, byte[] image) {
        ContentValues cv = new ContentValues();
        Date now = new Date();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_IMAGE, image);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_START_DATE, start_date);
        cv.put(COLUMN_END_DATE, end_date);
        cv.put(COLUMN_ISFAVOR, isFavor);
        cv.put(COLUMN_ISCHECKED, isChecked);
        cv.put(COLUMN_CREATED_DATE, String.valueOf(now));
        cv.put(COLUMN_CREATED_BY, createdBy);
        cv.put(COLUMN_MODIFIED_DATE, String.valueOf(now));
        cv.put(COLUMN_MODIFIED_BY, createdBy);

        return db.insert(TABLE_NAME, null, cv);
    }

    /**
     * Hàm lấy danh sách tour
     * @return cursor
     * @author nnhiep 17.03.2023
     */
    Cursor getFilterData(SQLiteDatabase db) {
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /**
     * Hàm cập nhật thông tin tour
     * @author nnhiep 18.03.2023
     */
    public long updateData(SQLiteDatabase db, String row_id, String title, double price, String start_date, String end_date, boolean isFavor, boolean isChecked, String modifiedBy, byte[] image) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_IMAGE, image);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_START_DATE, start_date);
        cv.put(COLUMN_END_DATE, end_date);
        cv.put(COLUMN_ISFAVOR, isFavor);
        cv.put(COLUMN_ISCHECKED, isChecked);
        cv.put(COLUMN_MODIFIED_DATE, String.valueOf(new Date()));
        cv.put(COLUMN_MODIFIED_BY, modifiedBy);

        return db.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{row_id});
    }

    /**
     * Hàm xóa dữ liệu tour
     * @author nnhiep 18.03.2023
     */
    public long deleteARecord(SQLiteDatabase db, String row_id) {
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{row_id});
    }

    /**
     * Hàm xóa tất cả dữ liệu
     * @author nnhiep 18.03.2023
     */
    public void deleteAllRecord(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
