package com.nnhiep.travelmanager.models;

/**
 * Người dùng
 * @author nnhiep 18.03.2023
 */
public class Employee {
    // ID, tên, số điện thoại, tài khoản, mật khẩu
    private String id, name, phone, account, password;
    // Tuổi, giới tính
    private int age, gender;
    // Ảnh đại diện
    private byte[] avatar;

    public Employee() {}

    public Employee(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Employee(String id, String name, String phone, String account, String password, int age, int gender, byte[] avatar) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.account = account;
        this.password = password;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
}
