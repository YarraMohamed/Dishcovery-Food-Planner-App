package com.example.foodplanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Area {
    @SerializedName("strArea")
    private String countryName;
    private String thumbnail;
    private Map<String,String> countryMap = new HashMap<>();

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

    public static void countryMap(HashMap<String, String> map) {
        map.put("American", "https://flagcdn.com/w320/us.png");
        map.put("British", "https://flagcdn.com/w320/gb.png");
        map.put("Canadian", "https://flagcdn.com/w320/ca.png");
        map.put("Chinese", "https://flagcdn.com/w320/cn.png");
        map.put("Croatian", "https://flagcdn.com/w320/hr.png");
        map.put("Dutch", "https://flagcdn.com/w320/nl.png");
        map.put("Egyptian", "https://flagcdn.com/w320/eg.png");
        map.put("Filipino", "https://flagcdn.com/w320/ph.png");
        map.put("French", "https://flagcdn.com/w320/fr.png");
        map.put("Greek", "https://flagcdn.com/w320/gr.png");
        map.put("Indian", "https://flagcdn.com/w320/in.png");
        map.put("Irish", "https://flagcdn.com/w320/ie.png");
        map.put("Italian", "https://flagcdn.com/w320/it.png");
        map.put("Jamaican", "https://flagcdn.com/w320/jm.png");
        map.put("Japanese", "https://flagcdn.com/w320/jp.png");
        map.put("Kenyan", "https://flagcdn.com/w320/ke.png");
        map.put("Malaysian", "https://flagcdn.com/w320/my.png");
        map.put("Mexican", "https://flagcdn.com/w320/mx.png");
        map.put("Moroccan", "https://flagcdn.com/w320/ma.png");
        map.put("Norwegian", "https://flagcdn.com/w320/no.png");
        map.put("Polish", "https://flagcdn.com/w320/pl.png");
        map.put("Portuguese", "https://flagcdn.com/w320/pt.png");
        map.put("Russian", "https://flagcdn.com/w320/ru.png");
        map.put("Spanish", "https://flagcdn.com/w320/es.png");
        map.put("Thai", "https://flagcdn.com/w320/th.png");
        map.put("Tunisian", "https://flagcdn.com/w320/tn.png");
        map.put("Turkish", "https://flagcdn.com/w320/tr.png");
        map.put("Ukrainian", "https://flagcdn.com/w320/ua.png");
        map.put("Uruguayan", "https://flagcdn.com/w320/uy.png");
        map.put("Vietnamese", "https://flagcdn.com/w320/vn.png");
    }


}
