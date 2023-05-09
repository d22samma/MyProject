package com.example.myproject;

import com.google.gson.annotations.SerializedName;

public class Birds {
    private String name;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String company;
    @SerializedName("category")
    private String size;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    public Birds(String name, String type, String company, String size, String location) {
        this.name = name;
        this.type = type;
        this.company = company;
        this.size = size;
        this.location = location;
    }
}
