package mfq.com.refooddelivery2.recycler;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.activity.FoodDetails;
import mfq.com.refooddelivery2.helper.PriceView;
import mfq.com.refooddelivery2.models.Product;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected final TextView mNameTextView;
    protected final ImageView mImg;
    protected final PriceView mPriceTextView;
    protected final TextView mDescriptionTextView;
    private final Context mContext;
    protected Product mProduct;

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mNameTextView = itemView.findViewById(R.id.product_item_name);
        mImg = itemView.findViewById(R.id.product_item_img);
        mPriceTextView = itemView.findViewById(R.id.product_item_price);
        mDescriptionTextView = itemView.findViewById(R.id.product_item_description);
        itemView.setOnClickListener(this);

    }



    public void bind(Product product) {
        mProduct = product;
        mNameTextView.setText(product.getName());
        mPriceTextView.setPrice(product.getPrice());
        mDescriptionTextView.setText(product.getDescription());
        RequestOptions requestOptions = RequestOptions.placeholderOf(R.drawable.freakah).dontTransform();
        Glide.with(mContext).load(product.getImgUrl()).apply(requestOptions).into(mImg);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        mContext.startActivity( FoodDetails.buildIntent(view.getContext(),mProduct));

    }

}
