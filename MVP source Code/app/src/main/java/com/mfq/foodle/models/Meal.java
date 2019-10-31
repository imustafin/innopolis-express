package com.mfq.foodle.models;

import com.mfq.foodle.R;

import java.util.ArrayList;
import java.util.List;

public class Meal {

    public final static int[] foodImgs = {
            R.drawable.food1,
            R.drawable.food2,
            R.drawable.food3,
            R.drawable.food4,
            R.drawable.food5,
            R.drawable.food6,
            R.drawable.food7,
            R.drawable.food8,
            R.drawable.food9,
            R.drawable.food10
    };
    public final static String[] foodText = {
            "Freekah",
            "Ozy",
            "Bryani",
            "Zerb",
            "Zerbyan",
            "Qedrah",
            "Mathbi",
            "Maklobah",
            "Mandi",
            "Mansaf"
    };



    public final static int[] desserImgs = {
            R.drawable.dessert1,
            R.drawable.dessert2,
            R.drawable.dessert3,
            R.drawable.dessert4,
            R.drawable.dessert5,
            R.drawable.dessert6,
            R.drawable.dessert7,
    };


    public final static String[] desserText = {
            "Nescafe Cake",
            "Cheese Cake",
            "RoseBerry Cake",
            "BlueBerry Cake",
            "Despacito",
            "Layers Cake",
            "Chocolate Cake",
    };


    private String text;
    private int Img;

    public Meal(String text, int img) {
        this.text = text;
        Img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    public static List<Meal> buildMeals() {
        List<Meal> mealList = new ArrayList<>();

        for (int i = 0; i < foodImgs.length; i++) {
            Meal meal = new Meal(foodText[i], foodImgs[i]);
            mealList.add(meal);
        }

        return mealList;
    }

    public static List<Meal> buildDessert() {
        List<Meal> mealList = new ArrayList<>();

        for (int i = 0; i < desserImgs.length; i++) {
            Meal meal = new Meal(desserText[i], desserImgs[i]);
            mealList.add(meal);
        }

        return mealList;
    }
}
