package mfq.com.refooddelivery2.helper;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.material.button.MaterialButton;

import mfq.com.refooddelivery2.R;

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
