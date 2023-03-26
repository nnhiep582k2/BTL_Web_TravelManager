package com.nnhiep.travelmanager.models;

public class UserListTeam {
    public String name;
    public String lastMessage;
    public String lastMsgTime;
    String phone;
    String country;
    public int immgeeId;

    public UserListTeam(String name, String lastMessage, String lastMsgTime, String phone, String country, int immgeeId) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.lastMsgTime = lastMsgTime;
        this.phone = phone;
        this.country = country;
        this.immgeeId = immgeeId;
    }
}
