package com.example.myproject;

public class RecyclerViewItem {

    private String name;
    private String type;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public RecyclerViewItem(String name, String type, String location) {
        this.name = name;
        this.type = type;
        this.location = location;
    }

}
