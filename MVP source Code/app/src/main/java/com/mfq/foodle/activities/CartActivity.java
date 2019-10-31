package com.mfq.foodle.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mfq.foodle.R;
import com.mfq.foodle.cart.CartFragment;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportFragmentManager().beginTransaction().replace(R.id.cartFragment, new CartFragment(), CartFragment.class.getSimpleName()).commit();

    }

}
