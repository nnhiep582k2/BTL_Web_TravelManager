package com.nnhiep.travelmanager.models;

public class User_Contact {
    private String image;
    private String name;
    private String phone;
    private String address;
    private String gmail;
    public User_Contact(){
    }

    public User_Contact(String image, String name, String phone,String address,String gmail){
        this.image = image;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.gmail = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
