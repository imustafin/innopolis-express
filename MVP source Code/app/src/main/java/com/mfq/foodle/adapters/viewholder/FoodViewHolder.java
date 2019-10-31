package com.mfq.foodle.adapters.viewholder;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mfq.foodle.R;
import com.mfq.foodle.activities.FoodDetails;
import com.mfq.foodle.coustemviews.PriceView;
import com.mfq.foodle.events.SelectedProductEvent;
import com.mfq.foodle.models.product.Product;

import org.greenrobot.eventbus.EventBus;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected final TextView mNameTextView;
    protected final ImageView mImg;
    protected final PriceView mPriceTextView;
    protected final TextView mDescriptionTextView;
    private boolean mIsMoofi;
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

    public FoodViewHolder(@NonNull View itemView, boolean isMoofi) {
        this(itemView);
        mIsMoofi = isMoofi;
    }

    public void bind(Product product) {
        mProduct = product;
        // FIXME use glide here and API
        mNameTextView.setText(product.getName());
        mPriceTextView.setPrice(product.getPrice());
        mDescriptionTextView.setText(product.getDescription());
        RequestOptions requestOptions = RequestOptions.placeholderOf(R.drawable.freakah).dontTransform();

        Glide.with(mContext).load(product.getImgUrl()).apply(requestOptions).into(mImg);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        if (!mIsMoofi) {
            Intent detailsIntent = FoodDetails.getDetailsIntent(view.getContext(), mProduct, getLayoutPosition());
            transitionTo(detailsIntent, view.getContext(),mImg);
        } else {
            EventBus.getDefault().post(new SelectedProductEvent(mProduct));
        }


    }


    void transitionTo(Intent i, Context c, View shared) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            c.startActivity(i,
                    ActivityOptions.makeSceneTransitionAnimation((AppCompatActivity) c, shared,
                            shared.getTransitionName()).toBundle());
        } else {
            c.startActivity(i);
        }
    }
}
