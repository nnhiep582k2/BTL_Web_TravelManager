package com.nnhiep.travelmanager.models;

import java.util.Date;

/**
 * Model dùng chung
 * @author nnhiep 18.03.2023
 */
public class BaseModel {
    // Người tạo, người sửa gần nhất
    private String createdBy, modifiedBy;
    // Thời gian tạo, thời gian sửa gần nhất
    private Date createdDate, modifiedDate;

    public BaseModel() {}

    public BaseModel(String createdBy, String modifiedBy, Date createdDate, Date modifiedDate) {
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
