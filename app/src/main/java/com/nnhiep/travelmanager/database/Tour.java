package com.nnhiep.travelmanager.database;

import android.database.sqlite.SQLiteDatabase;

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
    private static final String COLUMN_CREATED_DATE = "tour_created_date";
    private static final String COLUMN_CREATED_BY = "tour_created_by";
    private static final String COLUMN_MODIFIED_DATE = "tour_modified_date";
    private static final String COLUMN_MODIFIED_BY = "tour_modified_by";
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
                " DATETIME NOT NULL, " + COLUMN_ISFAVOR + " BOOLEAN, " + COLUMN_CREATED_DATE + " DATETIME, "
                + COLUMN_CREATED_BY + " NVARCHAR(200), " + COLUMN_MODIFIED_DATE + " DATETIME, " +
                COLUMN_MODIFIED_BY + " NVARCHAR(200));";
        db.execSQL(query);
    }
}
