package com.nnhiep.travelmanager.models;

import java.util.Date;

/**
 * Model Tour
 * @author nnhiep 18.03.2023
 */
public class Tour extends BaseModel {
    // ID
    private int id;
    // Giá
    private double price;
    // Tiêu đề
    private String title;
    // Ảnh
    private byte[] image;
    // Thời gian bắt đầu, thời gian kết thúc
    private Date startDate, endDate;
    // Có trong danh sách yêu thích không, có đang được chọn không
    private boolean isFavor, isChecked;

    public Tour() {}

    public Tour(String createdBy, String modifiedBy, Date createdDate, Date modifiedDate, int id, double price, String title, byte[] image, Date startDate, Date endDate, boolean isFavor, boolean isChecked) {
        super(createdBy, modifiedBy, createdDate, modifiedDate);
        this.id = id;
        this.price = price;
        this.title = title;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isFavor = isFavor;
        this.isChecked = isChecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isFavor() {
        return isFavor;
    }

    public void setFavor(boolean favor) {
        isFavor = favor;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
