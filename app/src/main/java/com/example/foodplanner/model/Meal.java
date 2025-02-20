package com.example.foodplanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    private String idMeal;
    @SerializedName("strMeal")
    private String mealName;
    @SerializedName("strCategory")
    private String category;
    @SerializedName("strArea")
    private String area;
    @SerializedName("strInstructions")
    private String instructions;
    @SerializedName("strMealThumb")
    private String mealThumb;
    @SerializedName("strYoutube")
    private String youtubeLink;
    @SerializedName("strIngredient1") private String Ingredient1;
    @SerializedName("strIngredient2") private String Ingredient2;
    @SerializedName("strIngredient3") private String Ingredient3;
    @SerializedName("strIngredient4") private String Ingredient4;
    @SerializedName("strIngredient5") private String Ingredient5;
    @SerializedName("strIngredient6") private String Ingredient6;
    @SerializedName("strIngredient7") private String Ingredient7;
    @SerializedName("strIngredient8") private String Ingredient8;
    @SerializedName("strIngredient9") private String Ingredient9;
    @SerializedName("strIngredient10") private String Ingredient10;
    @SerializedName("strIngredient11") private String Ingredient11;
    @SerializedName("strIngredient12") private String Ingredient12;
    @SerializedName("strIngredient13") private String Ingredient13;
    @SerializedName("strIngredient14") private String Ingredient14;
    @SerializedName("strIngredient15") private String Ingredient15;
    @SerializedName("strIngredient16") private String Ingredient16;
    @SerializedName("strIngredient17") private String Ingredient17;
    @SerializedName("strIngredient18") private String Ingredient18;
    @SerializedName("strIngredient19") private String Ingredient19;
    @SerializedName("strIngredient20") private String Ingredient20;

    @SerializedName("strMeasure1") private String Measure1;
    @SerializedName("strMeasure2") private String Measure2;
    @SerializedName("strMeasure3") private String Measure3;
    @SerializedName("strMeasure4") private String Measure4;
    @SerializedName("strMeasure5") private String Measure5;
    @SerializedName("strMeasure6") private String Measure6;
    @SerializedName("strMeasure7") private String Measure7;
    @SerializedName("strMeasure8") private String Measure8;
    @SerializedName("strMeasure9") private String Measure9;
    @SerializedName("strMeasure10") private String Measure10;
    @SerializedName("strMeasure11") private String Measure11;
    @SerializedName("strMeasure12") private String Measure12;
    @SerializedName("strMeasure13") private String Measure13;
    @SerializedName("strMeasure14") private String Measure14;
    @SerializedName("strMeasure15") private String Measure15;
    @SerializedName("strMeasure16") private String Measure16;
    @SerializedName("strMeasure17") private String Measure17;
    @SerializedName("strMeasure18") private String Measure18;
    @SerializedName("strMeasure19") private String Measure19;
    @SerializedName("strMeasure20") private String Measure20;

    public Meal(){

    }

    public Meal(String idMeal, String mealName, String category, String area, String instructions, String mealThumb, String youtubeLink) {
        this.idMeal = idMeal;
        this.mealName = mealName;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.mealThumb = mealThumb;
        this.youtubeLink = youtubeLink;
    }

    public Meal(String idMeal, String mealName, String category, String area, String instructions,
                String mealThumb, String youtubeLink, String ingredient1, String ingredient2,
                String ingredient3, String ingredient4, String ingredient5, String ingredient6,
                String ingredient7, String ingredient8, String ingredient9, String ingredient10,
                String ingredient11, String ingredient12, String ingredient13, String ingredient14,
                String ingredient15, String ingredient16, String ingredient17, String ingredient18,
                String ingredient19, String ingredient20, String measure1, String measure2, String measure3,
                String measure4, String measure5, String measure6, String measure7, String measure8, String measure9,
                String measure10, String measure11, String measure12, String measure13, String measure14, String measure15,
                String measure16, String measure17, String measure18, String measure19, String measure20) {

        this.idMeal = idMeal;
        this.mealName = mealName;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.mealThumb = mealThumb;
        this.youtubeLink = youtubeLink;
        Ingredient1 = ingredient1;
        Ingredient2 = ingredient2;
        Ingredient3 = ingredient3;
        Ingredient4 = ingredient4;
        Ingredient5 = ingredient5;
        Ingredient6 = ingredient6;
        Ingredient7 = ingredient7;
        Ingredient8 = ingredient8;
        Ingredient9 = ingredient9;
        Ingredient10 = ingredient10;
        Ingredient11 = ingredient11;
        Ingredient12 = ingredient12;
        Ingredient13 = ingredient13;
        Ingredient14 = ingredient14;
        Ingredient15 = ingredient15;
        Ingredient16 = ingredient16;
        Ingredient17 = ingredient17;
        Ingredient18 = ingredient18;
        Ingredient19 = ingredient19;
        Ingredient20 = ingredient20;
        Measure1 = measure1;
        Measure2 = measure2;
        Measure3 = measure3;
        Measure4 = measure4;
        Measure5 = measure5;
        Measure6 = measure6;
        Measure7 = measure7;
        Measure8 = measure8;
        Measure9 = measure9;
        Measure10 = measure10;
        Measure11 = measure11;
        Measure12 = measure12;
        Measure13 = measure13;
        Measure14 = measure14;
        Measure15 = measure15;
        Measure16 = measure16;
        Measure17 = measure17;
        Measure18 = measure18;
        Measure19 = measure19;
        Measure20 = measure20;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getMealThumb() {
        return mealThumb;
    }

    public void setMealThumb(String mealThumb) {
        this.mealThumb = mealThumb;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getIngredient1() {
        return Ingredient1;
    }

    public void setIngredient1(String ingredient1) {
        Ingredient1 = ingredient1;
    }

    public String getIngredient2() {
        return Ingredient2;
    }

    public void setIngredient2(String ingredient2) {
        Ingredient2 = ingredient2;
    }

    public String getIngredient3() {
        return Ingredient3;
    }

    public void setIngredient3(String ingredient3) {
        Ingredient3 = ingredient3;
    }

    public String getIngredient4() {
        return Ingredient4;
    }

    public void setIngredient4(String ingredient4) {
        Ingredient4 = ingredient4;
    }

    public String getIngredient5() {
        return Ingredient5;
    }

    public void setIngredient5(String ingredient5) {
        Ingredient5 = ingredient5;
    }

    public String getIngredient6() {
        return Ingredient6;
    }

    public void setIngredient6(String ingredient6) {
        Ingredient6 = ingredient6;
    }

    public String getIngredient7() {
        return Ingredient7;
    }

    public void setIngredient7(String ingredient7) {
        Ingredient7 = ingredient7;
    }

    public String getIngredient8() {
        return Ingredient8;
    }

    public void setIngredient8(String ingredient8) {
        Ingredient8 = ingredient8;
    }

    public String getIngredient9() {
        return Ingredient9;
    }

    public void setIngredient9(String ingredient9) {
        Ingredient9 = ingredient9;
    }

    public String getIngredient10() {
        return Ingredient10;
    }

    public void setIngredient10(String ingredient10) {
        Ingredient10 = ingredient10;
    }

    public String getIngredient11() {
        return Ingredient11;
    }

    public void setIngredient11(String ingredient11) {
        Ingredient11 = ingredient11;
    }

    public String getIngredient12() {
        return Ingredient12;
    }

    public void setIngredient12(String ingredient12) {
        Ingredient12 = ingredient12;
    }

    public String getIngredient13() {
        return Ingredient13;
    }

    public void setIngredient13(String ingredient13) {
        Ingredient13 = ingredient13;
    }

    public String getIngredient14() {
        return Ingredient14;
    }

    public void setIngredient14(String ingredient14) {
        Ingredient14 = ingredient14;
    }

    public String getIngredient15() {
        return Ingredient15;
    }

    public void setIngredient15(String ingredient15) {
        Ingredient15 = ingredient15;
    }

    public String getIngredient16() {
        return Ingredient16;
    }

    public void setIngredient16(String ingredient16) {
        Ingredient16 = ingredient16;
    }

    public String getIngredient17() {
        return Ingredient17;
    }

    public void setIngredient17(String ingredient17) {
        Ingredient17 = ingredient17;
    }

    public String getIngredient18() {
        return Ingredient18;
    }

    public void setIngredient18(String ingredient18) {
        Ingredient18 = ingredient18;
    }

    public String getIngredient19() {
        return Ingredient19;
    }

    public void setIngredient19(String ingredient19) {
        Ingredient19 = ingredient19;
    }

    public String getIngredient20() {
        return Ingredient20;
    }

    public void setIngredient20(String ingredient20) {
        Ingredient20 = ingredient20;
    }

    public String getMeasure1() {
        return Measure1;
    }

    public void setMeasure1(String measure1) {
        Measure1 = measure1;
    }

    public String getMeasure2() {
        return Measure2;
    }

    public void setMeasure2(String measure2) {
        Measure2 = measure2;
    }

    public String getMeasure3() {
        return Measure3;
    }

    public void setMeasure3(String measure3) {
        Measure3 = measure3;
    }

    public String getMeasure4() {
        return Measure4;
    }

    public void setMeasure4(String measure4) {
        Measure4 = measure4;
    }

    public String getMeasure5() {
        return Measure5;
    }

    public void setMeasure5(String measure5) {
        Measure5 = measure5;
    }

    public String getMeasure6() {
        return Measure6;
    }

    public void setMeasure6(String measure6) {
        Measure6 = measure6;
    }

    public String getMeasure7() {
        return Measure7;
    }

    public void setMeasure7(String measure7) {
        Measure7 = measure7;
    }

    public String getMeasure8() {
        return Measure8;
    }

    public void setMeasure8(String measure8) {
        Measure8 = measure8;
    }

    public String getMeasure9() {
        return Measure9;
    }

    public void setMeasure9(String measure9) {
        Measure9 = measure9;
    }

    public String getMeasure10() {
        return Measure10;
    }

    public void setMeasure10(String measure10) {
        Measure10 = measure10;
    }

    public String getMeasure11() {
        return Measure11;
    }

    public void setMeasure11(String measure11) {
        Measure11 = measure11;
    }

    public String getMeasure12() {
        return Measure12;
    }

    public void setMeasure12(String measure12) {
        Measure12 = measure12;
    }

    public String getMeasure13() {
        return Measure13;
    }

    public void setMeasure13(String measure13) {
        Measure13 = measure13;
    }

    public String getMeasure14() {
        return Measure14;
    }

    public void setMeasure14(String measure14) {
        Measure14 = measure14;
    }

    public String getMeasure15() {
        return Measure15;
    }

    public void setMeasure15(String measure15) {
        Measure15 = measure15;
    }

    public String getMeasure16() {
        return Measure16;
    }

    public void setMeasure16(String measure16) {
        Measure16 = measure16;
    }

    public String getMeasure17() {
        return Measure17;
    }

    public void setMeasure17(String measure17) {
        Measure17 = measure17;
    }

    public String getMeasure18() {
        return Measure18;
    }

    public void setMeasure18(String measure18) {
        Measure18 = measure18;
    }

    public String getMeasure19() {
        return Measure19;
    }

    public void setMeasure19(String measure19) {
        Measure19 = measure19;
    }

    public String getMeasure20() {
        return Measure20;
    }

    public void setMeasure20(String measure20) {
        Measure20 = measure20;
    }
}
