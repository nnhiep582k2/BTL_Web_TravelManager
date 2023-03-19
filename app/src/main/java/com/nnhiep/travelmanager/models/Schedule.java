package com.nnhiep.travelmanager.models;

import java.util.Date;

/**
 * Model lịch trình
 * @author nnhiep 18.03.2023
 */
public class Schedule extends BaseModel {
    // Địa điểm, khách sạn, thời tiết, mô tả
    private String location, hotel, weather, description;
    // ID, ID tour, phương tiện
    private int id, tour_id, vehicle;

    public Schedule() {}

    public Schedule(String createdBy, String modifiedBy, Date createdDate, Date modifiedDate, String location, String hotel, String weather, String description, int id, int tour_id, int vehicle) {
        super(createdBy, modifiedBy, createdDate, modifiedDate);
        this.location = location;
        this.hotel = hotel;
        this.weather = weather;
        this.description = description;
        this.id = id;
        this.tour_id = tour_id;
        this.vehicle = vehicle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public int getVehicle() {
        return vehicle;
    }

    public void setVehicle(int vehicle) {
        this.vehicle = vehicle;
    }
}
