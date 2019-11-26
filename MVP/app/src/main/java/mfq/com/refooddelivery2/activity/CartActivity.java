package mfq.com.refooddelivery2.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.fragments.CartFragment;


public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        CartFragment fragment = new CartFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment, CartFragment.class.getSimpleName()).commit();
    }

}
