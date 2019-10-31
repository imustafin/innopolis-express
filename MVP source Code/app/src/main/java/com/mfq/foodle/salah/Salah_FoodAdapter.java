package com.mfq.foodle.salah;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfq.foodle.R;
import com.mfq.foodle.models.Meal;

import java.util.ArrayList;
import java.util.List;


public class Salah_FoodAdapter extends RecyclerView.Adapter {

    List<Meal> mMealList = new ArrayList<>();

    public void setMealList(List<Meal> mealList) {
        mMealList = mealList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Salah_FoodViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_card_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((Salah_FoodViewHolder) viewHolder).bind(mMealList.get(i));
    }

    @Override
    public int getItemCount() {
        return mMealList.size();
    }
}
