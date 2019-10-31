package com.mfq.foodle.moofi.adapters.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dgreenhalgh.android.simpleitemdecoration.linear.EndOffsetItemDecoration;
import com.mfq.foodle.R;
import com.mfq.foodle.adapters.HorizaentalFoodAdapter;
import com.mfq.foodle.models.product.Product;

import java.util.List;

public class MoofiProductListViewHolder extends RecyclerView.ViewHolder {


    private final HorizaentalFoodAdapter mAdapter;

    public MoofiProductListViewHolder(View view) {
        super(view);
        RecyclerView recycler = view.findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new HorizaentalFoodAdapter(true);
        recycler.setAdapter(mAdapter);
        recycler.addItemDecoration(new EndOffsetItemDecoration(20));




    }


    public void bind(List<Product> products) {
        mAdapter.setProductList(products);
    }


}
