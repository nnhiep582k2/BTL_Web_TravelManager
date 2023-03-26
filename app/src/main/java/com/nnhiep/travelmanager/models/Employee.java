package com.nnhiep.travelmanager.models;

import android.graphics.Bitmap;
import java.util.Date;

/**
 * Nhân viên
 * @author nnhiep 18.03.2023
 */
public class Employee extends BaseModel {
    // ID, tên, số điện thoại, gmail
    private String id, name, phone, gmail;
    // Tuổi, giới tính
    private int age, gender;
    // Ảnh đại diện
    private Bitmap avatar;

    public Employee() {}

    public Employee(String id, String name, String phone, String gmail, int age, int gender, Bitmap avatar) {
        super("nnhiep", "nnhiep", new Date(), new Date());
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.gmail = gmail;
        this.age = age;
        this.gender = gender;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }
}
