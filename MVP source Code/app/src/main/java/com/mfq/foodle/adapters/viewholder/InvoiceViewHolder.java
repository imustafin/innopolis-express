package com.mfq.foodle.adapters.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.mfq.foodle.R;
import com.mfq.foodle.models.product.Product;

public class InvoiceViewHolder extends FoodViewHolder {


    private final TextView mQtyTextView;
    private final TextView mPriceTextViews;

    public InvoiceViewHolder(@NonNull View itemView) {
        super(itemView);
        mQtyTextView = itemView.findViewById(R.id.product_item_qty);
        mPriceTextViews = itemView.findViewById(R.id.product_item_price_text);


    }

    public void bind(Product product) {
        mProduct = product;
        mNameTextView.setText(product.getName());
        mPriceTextViews.setText(product.getPrice().getFormattedValue());
        mQtyTextView.setText(String.valueOf(product.getQuantity()));
    }


}
