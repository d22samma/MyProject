package com.example.myproject;

import com.google.gson.annotations.SerializedName;

public class Birds {
    private String name;
    private String company;
    @SerializedName("category")
    private String size;
    private String location;
}
