package com.mfq.foodle.staggeredgridlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;

import com.bumptech.glide.Glide;
import com.mfq.foodle.R;
import com.mfq.foodle.adapters.viewholder.FoodViewHolder;
import com.mfq.foodle.events.SelectedProductEvent;
import com.mfq.foodle.models.product.Product;

import org.greenrobot.eventbus.EventBus;

public class StaggeredProductCardViewHolder extends FoodViewHolder {


    private final CheckBox mCheckBox;
    private final Context mContext;

    public StaggeredProductCardViewHolder(@NonNull View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mCheckBox = itemView.findViewById(R.id.checkBox);
        itemView.setOnClickListener(this);
    }

    public void bind(Product product) {
        mCheckBox.setChecked(product.isSelected());
        mProduct = product;
        mNameTextView.setText(product.getName());
        mPriceTextView.setPrice(product.getPrice());
        Glide.with(mContext).load(product.getImgUrl()).into(mImg);

    }

    @Override
    public void onClick(View view) {
        mProduct.setSelected(!mProduct.isSelected());
        mCheckBox.setChecked(mProduct.isSelected());

        EventBus.getDefault().post(new SelectedProductEvent(mProduct));

    }
}
