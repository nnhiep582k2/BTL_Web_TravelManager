package com.nnhiep.travelmanager.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

import com.nnhiep.travelmanager.R;

/**
 * Cơ sở dữ liệu của chương trình
 * @author nnhiep 18.03.2023
 */
public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TravelManager.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    Employee employee;
    Schedule schedule;
    Tour tour;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        employee = new Employee();
        schedule = new Schedule();
        tour = new Tour();
        employee.createTableEmployee(db);
        schedule.createTableSchedule(db);
        tour.createTableTour(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS employee");
        db.execSQL("DROP TABLE IF EXISTS schedule");
        db.execSQL("DROP TABLE IF EXISTS tour");
        onCreate(db);
    }

    /**
     * Hàm truy vấn tự định nghĩa
     * @author nnhiep 18.03.2023
     */
    public Cursor customQuery(String query, Boolean isSelect) {
        SQLiteDatabase database;
        Cursor cursor = null;
        if(isSelect) {
            database = getReadableDatabase();
            database.execSQL(query);
            database.close();
        } else {
            database = getWritableDatabase();
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }


    // region Get
    /**
     * Lấy dữ liệu người dùng
     * @author nnhiep 18.03.2023
     */
    public Cursor getDataEmployee() {
        SQLiteDatabase db = this.getReadableDatabase();
        return  employee.getData(db);
    }

    /**
     * Lấy dữ liệu lịch trình
     * @author nnhiep 18.03.2023
     */
    public Cursor getDataFilterSchedule() {
        SQLiteDatabase db = this.getReadableDatabase();
        return schedule.getFilterData(db);
    }

    /**
     * Lấy dữ liệu tour
     * @author nnhiep 18.03.2023
     */
    public Cursor getDataFilterTour() {
        SQLiteDatabase db = this.getReadableDatabase();
        return tour.getFilterData(db);
    }
    // endregion


    // region Insert
    /**
     * Thêm mới một người dùng
     * @author nnhiep 18.03.2023
     */
    public void insertAnEmployee(String name, int age, int gender, String account, String pasword, String phone, byte[] avatar) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = employee.insertARecord(db, name, age, gender, account, pasword, phone, avatar);

        if(result == -1) {
            Toast.makeText(context, context.getResources().getString(R.string.insert_employee_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.insert_employee_success), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Thêm mới một lịch trình
     * @author nnhiep 18.03.2023
     */
    public void insertASchedule(int tour_id, String location, String hotel, String weather, int vehicle, String description, String createdBy) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = schedule.insertARecord(db, tour_id, location, hotel, weather, vehicle, description, createdBy);

        if(result == -1) {
            Toast.makeText(context, context.getResources().getString(R.string.insert_schedule_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.insert_schedule_success), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Thêm mới một tour
     * @author nnhiep 18.03.2023
     */
    public void insertATour(String title, double price, String start_date, String end_date, boolean isFavor, boolean isChecked, String createdBy, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = tour.insertARecord(db, title, price, start_date, end_date, isFavor, isChecked, createdBy, image);

        if(result == -1) {
            Toast.makeText(context, context.getResources().getString(R.string.insert_tour_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.insert_tour_success), Toast.LENGTH_SHORT).show();
        }
    }
    // endregion


    // region Update
    /**
     * Sửa thông tin người dùng
     * @author nnhiep 18.03.2023
     */
    public void updateAnEmployee(String row_id, String name, int age, int gender, String account, String pasword, String phone, byte[] avatar) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = employee.updateData(db, row_id, name, age, gender, account, pasword, phone, avatar);

        if(result == -1) {
            Toast.makeText(context, context.getResources().getString(R.string.update_employee_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.update_employee_success), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Sửa thông tin lịch trình
     * @author nnhiep 18.03.2023
     */
    public void updateASchedule(int tour_id, String row_id, String location, String hotel, String weather, int vehicle, String description, String modifiedBy) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = schedule.updateData(db, tour_id, row_id, location, hotel, weather, vehicle, description, modifiedBy);

        if(result == -1) {
            Toast.makeText(context, context.getResources().getString(R.string.update_schedule_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.update_schedule_success), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Sửa thông tin tour
     * @author nnhiep 18.03.2023
     */
    public void updateATour(String row_id, String title, double price, String start_date, String end_date, boolean isFavor, boolean isChecked, String modifiedBy, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = tour.updateData(db, row_id, title, price, start_date, end_date, isFavor, isChecked, modifiedBy, image);

        if(result == -1) {
            Toast.makeText(context, context.getResources().getString(R.string.update_tour_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.update_tour_success), Toast.LENGTH_SHORT).show();
        }
    }
    // endregion


    // region Delete
    /**
     * Hàm xóa thông tin người dùng
     * @author nnhiep 18.03.2023
     */
    public void deleteEmployee(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = employee.deleteARecord(db, row_id);
        if(result == -1) {
            Toast.makeText(context, context.getResources().getString(R.string.delete_employee_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.delete_employee_success), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Hàm xóa một lịch trình
     * @author nnhiep 18.03.2023
     */
    public void deleteASchedule(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = schedule.deleteARecord(db, row_id);
        if(result == -1) {
            Toast.makeText(context, context.getResources().getString(R.string.delete_schedule_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.delete_schedule_success), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Hàm xóa một tour
     * @author nnhiep 18.03.2023
     */
    public void deleteATour(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = tour.deleteARecord(db, row_id);
        if(result == -1) {
            Toast.makeText(context, context.getResources().getString(R.string.delete_tour_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.delete_tour_success), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Hàm xóa tất cả lịch trình
     * @author nnhiep 18.03.2023
     */
    public void deleteAllSchedule() {
        SQLiteDatabase db = this.getWritableDatabase();
        schedule.deleteAllRecord(db);
    }

    /**
     * Hàm xóa tất cả tour
     * @author nnhiep 18.03.2023
     */
    public void deleteAllTour() {
        SQLiteDatabase db = this.getWritableDatabase();
        tour.deleteAllRecord(db);
    }
    // endregion
}
