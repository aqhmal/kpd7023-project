package com.aqhmal.tempahanmakanan.model;

public class Food {
    public int foodImage;
    public String foodName;

    public Food(String foodName, int foodImage) {
        this.foodName =  foodName;
        this.foodImage = foodImage;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}