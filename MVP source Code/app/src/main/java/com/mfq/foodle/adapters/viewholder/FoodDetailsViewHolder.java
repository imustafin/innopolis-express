package com.mfq.foodle.adapters.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mfq.foodle.R;
import com.mfq.foodle.adapters.MealPreferencesAdapter;
import com.mfq.foodle.models.product.Product;

public class FoodDetailsViewHolder extends RecyclerView.ViewHolder {


    private final TextView mFoodNameText;
    private final RecyclerView mPreferenceRecycler;
    private final MealPreferencesAdapter mAdapter;

    public FoodDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        mFoodNameText = itemView.findViewById(R.id.details_meal_header_text);
        mPreferenceRecycler = itemView.findViewById(R.id.preference_recycler);
        mPreferenceRecycler.setLayoutManager(new LinearLayoutManager(itemView.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mPreferenceRecycler.setHasFixedSize(true);
        mAdapter = new MealPreferencesAdapter();
        mPreferenceRecycler.setAdapter(mAdapter);
        mPreferenceRecycler.setNestedScrollingEnabled(false);


    }

    public void bind(Product product) {
        mAdapter.setMealPreferences(product.getMealPreferences());
    }
}
