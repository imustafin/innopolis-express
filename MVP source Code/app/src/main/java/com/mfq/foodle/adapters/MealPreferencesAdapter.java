package com.mfq.foodle.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfq.foodle.R;
import com.mfq.foodle.adapters.viewholder.MealPreferencesViewHolder;
import com.mfq.foodle.models.product.MealPreference;

import java.util.ArrayList;
import java.util.List;


public class MealPreferencesAdapter extends RecyclerView.Adapter {


    List<MealPreference> mMealPreferences = new ArrayList<>();

    public void setMealPreferences(List<MealPreference> mealPreferences) {
        mMealPreferences = mealPreferences;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MealPreferencesViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_details_preference_item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((MealPreferencesViewHolder) viewHolder).bind(mMealPreferences.get(i));
    }

    @Override
    public int getItemCount() {
        return mMealPreferences.size();
    }
}
