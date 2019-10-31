package com.mfq.foodle.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfq.foodle.R;
import com.mfq.foodle.adapters.viewholder.FoodDetailsViewHolder;
import com.mfq.foodle.models.product.Product;


public class FoodDetailsAdapter extends RecyclerView.Adapter {

    // FIXME Behavior of the recycler not implement right
    // FIXME Add to cart button


    private int mQty = 1;

    private Product mProduct;

    public void setProduct(Product product) {
        mProduct = product;
        notifyDataSetChanged();
    }

    public void addItem(int qty) {
        mQty = qty;
        notifyItemInserted(mQty);
    }

    public void removeItem(int qty) {
        mQty = qty;
        notifyItemRemoved(0);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FoodDetailsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.details_meals_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((FoodDetailsViewHolder) viewHolder).bind(mProduct);
    }

    @Override
    public int getItemCount() {
        return mQty;
    }
}
