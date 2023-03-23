package com.nnhiep.travelmanager.enums;

/**
 * Enum lưu giới tính
 * @author nnhiep 17.03.2023
 */
public enum Gender {
    // Nữ
    Female(0),
    // Nam
    Male(1),
    // Khác
    Other(2);

    Gender(int item) {
        this.sex = item;
    }

    public int getGender() {
        return sex;
    }

    private int sex;
}
