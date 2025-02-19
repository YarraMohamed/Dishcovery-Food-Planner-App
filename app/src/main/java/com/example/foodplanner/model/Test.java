package com.example.foodplanner.model;

public class Test {
    String name;
    String Category;
    String Country;
    String measures;

    public Test() {
    }

    public Test(String name, String category, String country) {
        this.name = name;
        Category = category;
        Country = country;
    }

    public Test(String name, String measures) {
        this.name = name;
        this.measures = measures;
    }

    public Test(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }
}
