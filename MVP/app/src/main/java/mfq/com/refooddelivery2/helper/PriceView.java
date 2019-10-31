package mfq.com.refooddelivery2.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.product_accessories.Price;

public class PriceView extends LinearLayout {

    private Price mPrice;
    private TextView mPriceTextView;

    public TextView getPriceTextView() {
        return mPriceTextView;
    }

    public void setPrice(Price price) {
        mPrice = price;
        mPriceTextView.setText(mPrice.getFormattedValue());
    }

    public PriceView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(getContext(), R.layout.price_view_layout, this);
        mPriceTextView = findViewById(R.id.price);
        appendStyle(context, attrs);
    }

    private void appendStyle(Context context, AttributeSet attrs) {
        if (attrs == null)
            return;
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PriceView,
                0, 0);

        try {
            mPriceTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,a.getDimensionPixelSize(R.styleable.PriceView_priceTextSize, 14));
            mPriceTextView.setTextColor(a.getColor(R.styleable.PriceView_priceTextColor, ContextCompat.getColor(context, R.color.primaryTextColor)));
        } finally {
            a.recycle();
        }
    }

    public PriceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public PriceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }


}
