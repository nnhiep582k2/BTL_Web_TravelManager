package com.nnhiep.travelmanager.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.Date;

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
    private static final String COLUMN_CREATED_DATE = "employee_created_date";
    private static final String COLUMN_CREATED_BY = "employee_created_by";
    private static final String COLUMN_MODIFIED_DATE = "employee_modified_date";
    private static final String COLUMN_MODIFIED_BY = "employee_modified_by";
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

    /**
     * Thêm mới schedule
     * @author nnhiep 17.03.2023
     */
    public long insertARecord(SQLiteDatabase db, String tour_id, String location, String hotel, String weather, int vehicle, String description, String createdBy) {
        ContentValues cv = new ContentValues();
        Date now = new Date();

        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_WEATHER, weather);
        cv.put(COLUMN_VEHICLE, vehicle);
        cv.put(COLUMN_TOUR_ID, tour_id);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_HOTEL, hotel);
        cv.put(COLUMN_CREATED_DATE, String.valueOf(now));
        cv.put(COLUMN_CREATED_BY, createdBy);
        cv.put(COLUMN_MODIFIED_DATE, String.valueOf(now));
        cv.put(COLUMN_MODIFIED_BY, createdBy);

        return db.insert(TABLE_NAME, null, cv);
    }

    /**
     * Hàm lấy danh sách lịch trình
     * @return cursor
     * @author nnhiep 17.03.2023
     */
    public Cursor getFilterData(SQLiteDatabase db) {
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /**
     * Hàm cập nhật thông tin lịch trình
     * @author nnhiep 18.03.2023
     */
    public long updateData(SQLiteDatabase db, String tour_id, String row_id, String location, String hotel, String weather, int vehicle, String description, String modifiedBy) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_WEATHER, weather);
        cv.put(COLUMN_TOUR_ID, tour_id);
        cv.put(COLUMN_VEHICLE, vehicle);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_HOTEL, hotel);
        cv.put(COLUMN_MODIFIED_DATE, String.valueOf(new Date()));
        cv.put(COLUMN_MODIFIED_BY, modifiedBy);

        return db.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{row_id});
    }

    /**
     * Hàm xóa dữ liệu lịch trình
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
