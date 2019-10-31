package com.mfq.foodle.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;
import com.mfq.foodle.R;
import com.mfq.foodle.home.DessertFragment;
import com.mfq.foodle.home.MealsFragment;
import com.mfq.foodle.models.Meal;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, KenBurnsView.TransitionListener {
    static int mPosition;
    private static final int MEALS = 0;
    private static final int DESSERT = 1;
    private ViewPager mViewPager;
    private View mScrimView;
    private TabLayout mTapLayout;
    private ImageView mHeaderIcon;
    private KenBurnsView mKenBurnsView;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private BottomAppBar mBottomBar;
    private int mTransitionCount = 1;
    private int[] mKenBurnImagesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mViewPager = findViewById(R.id.viewPager);
        mScrimView = findViewById(R.id.scrimView);
        mTapLayout = findViewById(R.id.tabLayout);
        mHeaderIcon = findViewById(R.id.headerIcon);
        mKenBurnsView = findViewById(R.id.kenBurnsView);
        mBottomBar = findViewById(R.id.bottomBar);
        mCollapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);


        mViewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager()));
        mTapLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(this);

        setSupportActionBar(mBottomBar);
        mKenBurnsView.setTransitionListener(this);
        mKenBurnImagesArray = Meal.foodImgs;


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return true;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

        switch (i) {
            case MEALS:
                changeUIToMeals();
                mKenBurnImagesArray = Meal.foodImgs;
                break;
            case DESSERT:
                changeUIToDessert();
                mKenBurnImagesArray = Meal.desserImgs;
                break;
        }

        mKenBurnsView.setImageResource(mKenBurnImagesArray[0]);
        mTransitionCount = 0;

    }

    /**
     * This function to change the Header UI & Bottom App Bar to primaryColor when Meals Tab is selected
     */
    private void changeUIToMeals() {
        mCollapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mHeaderIcon.setBackground(ContextCompat.getDrawable(this, R.drawable.home_food_shape));
        mHeaderIcon.setImageResource(R.drawable.ic_round_local_dining);
        mScrimView.setBackgroundResource(R.color.colorPrimary);
        mBottomBar.setBackgroundTint(ContextCompat.getColorStateList(this, R.color.colorPrimary));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
    }


    /**
     * This function to change the Header UI & Bottom App Bar to AccentColor_pink when Meals Tab is selected
     */
    private void changeUIToDessert() {
        mCollapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.secondaryColor));
        mHeaderIcon.setBackground(ContextCompat.getDrawable(this, R.drawable.home_dessert_shape));
        mHeaderIcon.setImageResource(R.drawable.ic_round_cake);
        mScrimView.setBackgroundResource(R.color.secondaryColor);
        mBottomBar.setBackgroundTint(ContextCompat.getColorStateList(this, R.color.secondaryColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.secondaryDarkColor));
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
    @Override
    public void onTransitionStart(Transition transition) {


    }

    @Override
    public void onTransitionEnd(Transition transition) {
        mTransitionCount++;
        if (mTransitionCount == mKenBurnImagesArray.length)
            mTransitionCount = 0;

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mKenBurnsView, "alpha", 1f, 0f);
        final ObjectAnimator fadeIn = ObjectAnimator.ofFloat(mKenBurnsView, "alpha", 0f, 1f);
        fadeOut.setDuration(500);
        fadeIn.setDuration(400);
        fadeOut.start();
        fadeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mKenBurnsView.setImageResource(mKenBurnImagesArray[mTransitionCount]);
                fadeIn.start();
            }
        });

    }


    private static class HomePagerAdapter extends FragmentStatePagerAdapter {


        public HomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            mPosition = position;
            switch (position) {
                case MEALS:
                    return new MealsFragment();
                default:
                case DESSERT:
                    return new DessertFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case MEALS:
                    return "Meals";
                default:
                case DESSERT:
                    return "Dessert";
            }
        }
    }


}
