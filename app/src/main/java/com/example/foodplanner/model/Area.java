package com.example.foodplanner.model;

import com.google.gson.annotations.SerializedName;

public class Area {
    @SerializedName("strArea")
    private String countryName;

    public Area() {
    }

    public Area(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
