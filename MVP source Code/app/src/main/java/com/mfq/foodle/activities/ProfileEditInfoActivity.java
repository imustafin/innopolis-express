package com.mfq.foodle.activities;

import android.app.Activity;
import android.os.Bundle;

import com.mfq.foodle.R;

public class ProfileEditInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_info);
        findViewById(R.id.close).setOnClickListener(v -> onBackPressed());

    }

}
