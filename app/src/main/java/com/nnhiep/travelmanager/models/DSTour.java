package com.nnhiep.travelmanager.models;

import java.util.Comparator;

/**
 * Model Tour
 *
 * @author nnhiep 18.03.2023
 */
public class DSTour {
    private int id;
    private String date;
    private byte[] image;
    private String day, month, year;
    private String star, price, title;

    public DSTour(int id, String price, String title, String star, String date, byte[] image) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.star = star;
        this.date = date;
        String[] day = date.split("/");
        this.day = day[0];
        this.month = day[1];
        this.year = day[2];
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static class PriceDes implements Comparator<DSTour> {
        public int compare(DSTour a, DSTour b) {
            if (a.getMonth().equals(b.getMonth())) {
                return a.getDate().compareTo(b.getDate());
            } else {
                return a.getMonth().compareTo(b.getMonth());
            }
        }
    }
}