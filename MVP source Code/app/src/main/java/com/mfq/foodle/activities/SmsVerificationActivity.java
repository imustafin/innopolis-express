package com.mfq.foodle.activities;

import android.app.Activity;
import android.os.Bundle;

import com.mfq.foodle.R;

public class SmsVerificationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_verification);

        findViewById(R.id.close).setOnClickListener(v -> onBackPressed());
    }

}
