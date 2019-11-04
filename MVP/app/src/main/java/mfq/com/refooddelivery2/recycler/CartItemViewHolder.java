package mfq.com.refooddelivery2.recycler;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.models.Product;

public class CartItemViewHolder extends FoodViewHolder {

    private final TextView mQtyTextView;
    private final Context mContext;

    public CartItemViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mQtyTextView = itemView.findViewById(R.id.cart_item_qty);
        itemView.findViewById(R.id.cart_item_delete).setOnClickListener(this);
        itemView.findViewById(R.id.cart_item_edit).setOnClickListener(this);

    }

    public void bind(Product product) {
        mQtyTextView.setText(itemView.getContext().getString(R.string.cart_qty, product.getQuantity()));
        mPriceTextView.setPrice(product.getPrice());
        mNameTextView.setText(product.getName());
        Glide.with(mContext).load(product.getImgUrl()).into(mImg);
    }

    @Override
    public void onClick(View view) {

    }
}
