package com.example.foodplanner.model;

public class IngredientDetails {
    private String name;
    private String measures;

    public IngredientDetails() {
    }

    public IngredientDetails(String name, String measures) {
        this.name = name;
        this.measures=measures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }
}
