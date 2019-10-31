package com.mfq.foodle.staggeredgridlayout;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfq.foodle.R;
import com.mfq.foodle.models.product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter used to show an asymmetric grid of products, with 2 items in the first column, and 1
 * item in the second column, and so on.
 */
public class StaggeredFoodAdapter extends RecyclerView.Adapter<StaggeredProductCardViewHolder> {

    private List<Product> productList = new ArrayList<>();

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    public StaggeredFoodAdapter() {
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

    @NonNull
    @Override
    public StaggeredProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = R.layout.staggered_product_card_first;
        if (viewType == 1) {
            layoutId = R.layout.staggered_product_card_second;
        } else if (viewType == 2) {
            layoutId = R.layout.staggered_product_card_third;
        }

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new StaggeredProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull StaggeredProductCardViewHolder holder, int position) {
        holder.bind(productList.get(position));


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
