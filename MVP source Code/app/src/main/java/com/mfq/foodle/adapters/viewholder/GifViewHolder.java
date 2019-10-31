package com.mfq.foodle.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mfq.foodle.BuildConfig;
import com.mfq.foodle.R;

public class GifViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = GifViewHolder.class.getSimpleName();
    private final ImageView mImageView;

    public GifViewHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.gifImg);
    }


    public void bind(String imgUrl){
        if (BuildConfig.DEBUG)
            Log.d(TAG, "bind() called with: imgUrl = [" + imgUrl + "]");
        Glide.with(mImageView.getContext()).asGif().load(imgUrl).into(mImageView);
    }
}
