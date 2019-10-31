package com.mfq.foodle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.mfq.foodle.R;
import com.mfq.foodle.models.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class MealSettings extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    String[] food = {"Spicy Food",
            "Pepper",
            "Nuts",
            "Meat",
            "Tomato",
            "Potato",
            "Salad",
            "Yogurt",
            "Rice"
    };
    String[] dessert = {"Chocolate",
            "Vanilla",
            "Milk",
            "Tofi",
            "Heavy Cream",
            "Banana",
            "Jelly",
            "Biscuit",
            "Custard"
    };

    String[] chicken = {"Thigh", "Breast", "Wings"};
    private ChipGroup mFoodGroup;
    private ChipGroup mDessertGroup;
    private ChipGroup mChickenGroup;
    private View mChickenTextView;
    private Switch mVegetarianSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_settings);
        mFoodGroup = findViewById(R.id.food);
        mChickenTextView = findViewById(R.id.chickenText);
        mDessertGroup = findViewById(R.id.dessert);
        mChickenGroup = findViewById(R.id.chicken);
        mVegetarianSwitch = findViewById(R.id.vegetarianSwitch);
        View doneButton = findViewById(R.id.buttonDone);

        mVegetarianSwitch.setOnCheckedChangeListener(this);
        doneButton.setOnClickListener(this);
        setStaticData(false);
    }

    private void setStaticData(boolean isChecked) {
        List<Ingredients> foodList, dessertList, chickenList;
        foodList = new ArrayList<>();
        dessertList = new ArrayList<>();
        chickenList = new ArrayList<>();
        for (String s : food) {
            Ingredients ingredients = new Ingredients(s);
            if (s.equalsIgnoreCase("meat") || s.equalsIgnoreCase("Yogurt"))
                ingredients.setVegetarian(false);
            foodList.add(ingredients);
        }

        for (String s : dessert) {
            Ingredients ingredients = new Ingredients(s);
            dessertList.add(ingredients);
        }

        for (String s : chicken) {
            Ingredients ingredients = new Ingredients(s);
            chickenList.add(ingredients);
        }

        inValidateChips(mFoodGroup, true, foodList, isChecked);
        inValidateChips(mDessertGroup, true, dessertList, isChecked);
        inValidateChips(mChickenGroup, false, chickenList, isChecked);

        mChickenGroup.setVisibility(isChecked ? View.GONE : View.VISIBLE);
        mChickenTextView.setVisibility(isChecked ? View.GONE : View.VISIBLE);
    }


    @LayoutRes
    protected int getChipGroupItem(boolean singleSelection) {
        return singleSelection
                ? R.layout.cat_chip_group_item_filter
                : R.layout.cat_chip_group_item_choice;
    }

    private void inValidateChips(ChipGroup chipGroup, boolean isFilter, List<Ingredients> ingredientsList, boolean isShowVegetarianEnabled) {
        chipGroup.removeAllViews();

        for (Ingredients ingredient : ingredientsList) {
            Chip chip =
                    (Chip) getLayoutInflater().inflate(getChipGroupItem(isFilter), chipGroup, false);
            chip.setChipText(ingredient.getText());
            chip.setVisibility(isShowVegetarianEnabled ? ingredient.isVegetarian() ? View.VISIBLE : View.GONE : View.VISIBLE);

            chip.setChecked(ingredient.isSelected());
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            chipGroup.addView(chip);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        setStaticData(isChecked);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, MainActivity.class));

    }
}
