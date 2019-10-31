package com.mfq.foodle.activities;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.mfq.foodle.R;

public class SplashScreen extends AppCompatActivity {

    private View mLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mLogo = findViewById(R.id.logo);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            new Handler().postDelayed(() -> revealAnimation(mLogo), 1000);
        } else {
            mLogo.setVisibility(View.VISIBLE);
            new Handler().postDelayed(() -> startActivity(goToOnBoarding()), 1000);
        }

    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void revealAnimation(View logo) {
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(logo, logo.getWidth() / 2,
                logo.getHeight() / 2, 0,
                (float) Math.hypot(logo.getWidth(), logo.getHeight()));
        logo.setVisibility(View.VISIBLE);
        circularReveal.setInterpolator(new AccelerateDecelerateInterpolator());
        circularReveal.setDuration(1000);
        circularReveal.start();
        circularReveal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(goToOnBoarding());
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @NonNull
    private Intent goToOnBoarding() {
        return new Intent(SplashScreen.this, OnBoardingActivity.class);
    }


}
