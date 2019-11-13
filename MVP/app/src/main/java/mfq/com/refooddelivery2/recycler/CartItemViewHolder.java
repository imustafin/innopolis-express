package mfq.com.refooddelivery2.recycler;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.models.Cart;
import mfq.com.refooddelivery2.models.Product;

public class CartItemViewHolder extends FoodViewHolder {

    private final TextView mQtyTextView;
    private final Context mContext;

    public CartItemViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mQtyTextView = itemView.findViewById(R.id.cart_item_qty);
    }

    public void bind(Product product, CartItemsAdapter.CartUpdateInterface cartUpdateInterface) {
        mQtyTextView.setText(itemView.getContext().getString(R.string.cart_qty, product.getQuantity()));
        mPriceTextView.setPrice(product.getPrice());
        mNameTextView.setText(product.getName());
        Glide.with(mContext).load(product.getImgUrl()).into(mImg);

        View.OnClickListener oclBtnDelete = v -> {
            Cart.getInstance().deleteProduct(product);
            cartUpdateInterface.updateCart();
        };

        itemView.findViewById(R.id.cart_item_delete).setOnClickListener(oclBtnDelete);
        itemView.findViewById(R.id.cart_item_edit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
