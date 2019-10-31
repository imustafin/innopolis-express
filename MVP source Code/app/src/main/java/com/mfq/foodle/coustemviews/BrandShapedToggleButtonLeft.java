package com.mfq.foodle.coustemviews;

import android.content.Context;
import android.support.design.button.MaterialButton;
import android.util.AttributeSet;

import com.mfq.foodle.R;

public class BrandShapedToggleButtonLeft extends MaterialButton {
    public BrandShapedToggleButtonLeft(Context context) {
        super(context);
        setShapedBrandDrawable();
    }

    public BrandShapedToggleButtonLeft(Context context, AttributeSet attrs) {
        super(context, attrs);
        setShapedBrandDrawable();
    }

    public BrandShapedToggleButtonLeft(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setShapedBrandDrawable();
    }

    private void setShapedBrandDrawable() {
        setBackgroundResource(R.drawable.toggle_btn_background_left);
    }


}
