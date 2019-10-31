package com.mfq.foodle.salah;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mfq.foodle.models.product.Product;

public class Salah_FoodDetailsViewHolder extends RecyclerView.ViewHolder {



    public Salah_FoodDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
//        mFoodNameText = itemView.findViewById(R.id.list_item_name);
//        mFoodNameImg = itemView.findViewById(R.id.list_item_img);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void bind(Product meal) {

    }
}
