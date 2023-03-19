package com.nnhiep.travelmanager.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Bảng lịch trình
 * @author nnhiep 17.03.2023
 */
public class Schedule {
    // region Thông tin bảng dữ liệu
    private static final String TABLE_NAME = "schedule";
    private static final String COLUMN_ID = "schedule_id";
    private static final String COLUMN_LOCATION = "schedule_location";
    private static final String COLUMN_HOTEL = "schedule_hotel";
    private static final String COLUMN_TOUR_ID = "tour_tour_id";
    private static final String COLUMN_WEATHER = "schedule_weather";
    private static final String COLUMN_VEHICLE = "schedule_vehicle";
    private static final String COLUMN_DESCRIPTION = "schedule_description";
    private static final String COLUMN_CREATED_DATE = "schedule_created_date";
    private static final String COLUMN_CREATED_BY = "schedule_created_by";
    private static final String COLUMN_MODIFIED_DATE = "schedule_modified_date";
    private static final String COLUMN_MODIFIED_BY = "schedule_modified_by";
    // endregion

    /**
     * Hàm tạo mới bảng lịch trình
     * @param db - cơ sở dữ liệu của chương trình
     * @author nnhiep 18.03.2023
     */
    public void createTableSchedule(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON;");
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LOCATION + " TEXT NOT NULL, " + COLUMN_HOTEL + " NVARCHAR(200) NOT NULL, " + COLUMN_WEATHER + " TEXT, " +
                COLUMN_VEHICLE + " INTEGER NOT NULL, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_CREATED_DATE +
                " DATETIME, " + COLUMN_CREATED_BY + " NVARCHAR(200), " + COLUMN_MODIFIED_DATE + " DATETIME, " +
                COLUMN_MODIFIED_BY + " NVARCHAR(200), " + COLUMN_TOUR_ID + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_TOUR_ID + ") REFERENCES tour(tour_id));";
        db.execSQL(query);
    }
}
