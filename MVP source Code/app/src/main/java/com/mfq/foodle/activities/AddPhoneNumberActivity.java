package com.mfq.foodle.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mfq.foodle.R;

public class AddPhoneNumberActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        findViewById(R.id.fab).setOnClickListener(v -> startActivity(new Intent(this, SmsVerificationActivity.class)));
        findViewById(R.id.close).setOnClickListener(v -> onBackPressed());
    }

}
