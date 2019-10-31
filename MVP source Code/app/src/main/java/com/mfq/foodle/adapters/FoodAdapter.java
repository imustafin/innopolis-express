package com.mfq.foodle.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfq.foodle.R;
import com.mfq.foodle.adapters.viewholder.FoodViewHolder;
import com.mfq.foodle.models.product.Product;

import java.util.ArrayList;
import java.util.List;


public class FoodAdapter extends RecyclerView.Adapter {

    List<Product> mProducts = new ArrayList<>();

    public void setProductList(List<Product> mealList) {
        mProducts = mealList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FoodViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_card_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((FoodViewHolder) viewHolder).bind(mProducts.get(i));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
