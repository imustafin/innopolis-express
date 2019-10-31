package com.mfq.foodle.salah;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfq.foodle.R;
import com.mfq.foodle.models.Meal;

public class Salah_FoodViewHolder extends RecyclerView.ViewHolder {

    private final TextView mFoodNameText;
    private final ImageView mFoodNameImg;

    public Salah_FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        mFoodNameText = itemView.findViewById(R.id.product_item_name);
        mFoodNameImg = itemView.findViewById(R.id.product_item_img);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void bind(Meal meal) {
        mFoodNameImg.setImageResource(meal.getImg());
        mFoodNameText.setText(meal.getText());
    }
}
