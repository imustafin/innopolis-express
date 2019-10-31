package com.mfq.foodle.coustemviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mfq.foodle.R;

public class QuantityPicker extends LinearLayout implements View.OnClickListener, TextWatcher {

    private int mQty = 1;
    private EditText mQtyEditText;
    private OnQtyChangedListener mOnQtyChangedListener;


    public interface OnQtyChangedListener {

        void onQtyChanged(int qty);

        void onQtyIncreased(int qty);

        void onQtyDecreased(int qty);

    }

    public void setOnQtyChangedListener(OnQtyChangedListener onQtyChangedListener) {
        mOnQtyChangedListener = onQtyChangedListener;
    }

    public QuantityPicker(Context context) {
        super(context);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
        setBackgroundResource(R.drawable.qty_brand_shape);
        inflate(getContext(), R.layout.quantity_layout, this);
        mQtyEditText = findViewById(R.id.qty_edit_text);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.remove).setOnClickListener(this);
        mQtyEditText.addTextChangedListener(this);
    }

    public QuantityPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public QuantityPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }


    @Override
    public void onClick(View view) {
        mQty = view.getId() == R.id.add ? increment() : decrement();
        mQtyEditText.setText(String.valueOf(mQty));

    }

    private int decrement() {
        int qty = --mQty;
        if (qty == 0) qty = 1;
        if ( mOnQtyChangedListener != null) mOnQtyChangedListener.onQtyDecreased(qty);
        return qty;
    }

    private int increment() {
        int qty = ++mQty;
        if (mOnQtyChangedListener != null) {
            mOnQtyChangedListener.onQtyIncreased(qty);
        }
        return qty;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (mOnQtyChangedListener != null) {
            mOnQtyChangedListener.onQtyChanged(Integer.valueOf(charSequence.toString()));
        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
