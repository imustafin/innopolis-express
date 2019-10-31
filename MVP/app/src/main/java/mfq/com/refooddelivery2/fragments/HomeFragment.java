package mfq.com.refooddelivery2.fragments;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.transition.Slide;
import androidx.viewpager.widget.ViewPager;
import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.helper.ColorHelper;
import mfq.com.refooddelivery2.product_accessories.Meal;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener, KenBurnsView.TransitionListener {

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


    public HomeFragment() {
        // Required empty public constructor
    }

    private void animate() {
        Drawable drawable = mHeaderIcon.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mViewPager = root.findViewById(R.id.viewPager);
        mScrimView = root.findViewById(R.id.scrimView);
        mTapLayout = root.findViewById(R.id.tabLayout);
        mHeaderIcon = root.findViewById(R.id.headerIcon);
        mKenBurnsView = root.findViewById(R.id.kenBurnsView);
        mBottomBar = getActivity().findViewById(R.id.bottomBar);
        mCollapsingToolbarLayout = root.findViewById(R.id.collapsingToolbarLayout);


        mViewPager.setAdapter(new HomePagerAdapter(getChildFragmentManager()));
        mTapLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(this);

        mKenBurnsView.setTransitionListener(this);
        mKenBurnImagesArray = Meal.foodImgs;
        mScrimView.setOnClickListener(v -> animate());
        return root;
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
        fadeOut.setDuration(200);
        fadeIn.setDuration(300);
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

    private void changeHeaderIcon(boolean isToMeal) {
        mHeaderIcon.animate().setDuration(200).alpha(0).scaleX(0).scaleY(0).withEndAction(() -> {
                    mHeaderIcon.setImageResource(isToMeal ? R.drawable.ic_round_local_dining : R.drawable.ic_round_cake);
                    mHeaderIcon.animate().setDuration(300).alpha(1).scaleX(1).scaleY(1).start();
                    mHeaderIcon.setBackground(isToMeal ? ContextCompat.getDrawable(getActivity(), R.drawable.home_food_shape):ContextCompat.getDrawable(getActivity(), R.drawable.home_dessert_shape));

                }
        ).start();
    }

    /**
     * getActivity() function to change the Header UI & Bottom App Bar to primaryColor when Meals Tab is selected
     */
    private void changeUIToMeals() {
        changeHeaderIcon(true);
        ColorHelper.changeViewColor(mScrimView, ContextCompat.getColor(getActivity(), R.color.scrimBackground), ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        mCollapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        mBottomBar.setBackgroundTint(ContextCompat.getColorStateList(getActivity(), R.color.colorPrimary));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        }
    }


    /**
     * getActivity() function to change the Header UI & Bottom App Bar to AccentColor_pink when Meals Tab is selected
     */
    private void changeUIToDessert() {
        changeHeaderIcon(false);
        ColorHelper.changeViewColor(mScrimView, ContextCompat.getColor(getActivity(), R.color.colorPrimary), ContextCompat.getColor(getActivity(), R.color.secondaryColor));
        mBottomBar.setBackgroundTint(ContextCompat.getColorStateList(getActivity(), R.color.secondaryColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.secondaryDarkColor));
        }
    }


    private static class HomePagerAdapter extends FragmentStatePagerAdapter {


        public HomePagerAdapter(FragmentManager fm) {

            super(fm);
        }

        private Explode buildExitTransition() {
            Explode enterTransition = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                enterTransition = new Explode();
            }
            enterTransition.setDuration(5000);
            return enterTransition;
        }

        private Slide buildExitTransitionSlide() {
            Slide enterTransition = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                enterTransition = new Slide();
            }
            enterTransition.setSlideEdge(Gravity.LEFT);
            enterTransition.setDuration(5000);
            return enterTransition;
        }


        @Override
        public Fragment getItem(int position) {
            mPosition = position;
            Fragment fragment = new MealsFragment();
            fragment.setExitTransition(buildExitTransitionSlide());
            fragment.setEnterTransition(buildExitTransitionSlide());
            switch (position) {
                case MEALS:
                    return fragment;
                default:
                case DESSERT:
                    return new MealsFragment();
            }
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                default:
                case MEALS:
                    return "Meals";

            }
        }
    }


}
