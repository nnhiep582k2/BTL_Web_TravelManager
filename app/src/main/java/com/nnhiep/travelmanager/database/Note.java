package com.nnhiep.travelmanager.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Bảng ghi chú
 * @author nnhiep 17.03.2023
 */
public class Note {
    // region Thông tin bảng dữ liệu
    private static final String TABLE_NAME = "note";
    private static final String COLUMN_ID = "note_id";
    private static final String COLUMN_TITLE = "note_title";
    private static final String COLUMN_DESCRIPTION = "note_description";
    // endregion

    /**
     * Hàm tạo mới bảng ghi chú
     * @param db - cơ sở dữ liệu của chương trình
     * @author nnhiep 18.03.2023
     */
    public void createTableNote(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " NVARCHAR(200), " + COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(query);
    }
}
