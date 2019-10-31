package com.mfq.foodle.coustemviews;

import android.content.Context;
import android.support.design.button.MaterialButton;
import android.util.AttributeSet;

import com.mfq.foodle.R;

public class BrandShapedButton extends MaterialButton {
    public BrandShapedButton(Context context) {
        super(context);
        setShapedBrandDrawable();
    }

    public BrandShapedButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setShapedBrandDrawable();
    }

    public BrandShapedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setShapedBrandDrawable();
    }

    private void setShapedBrandDrawable() {
        setBackgroundResource(R.drawable.btn_background);
    }


}
