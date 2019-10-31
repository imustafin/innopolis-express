package com.mfq.foodle.backdrop;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Interpolator;

import com.mfq.foodle.R;

/**
 * {@link android.view.View.OnClickListener} used to translate the product grid sheet downward on
 * the Y-axis when the navigation icon in the toolbar is pressed.
 */
public class BackdropManger {

    private final AnimatorSet animatorSet = new AnimatorSet();
    private final View viewToHide;
    private Context context;
    private View sheet;
    private Interpolator interpolator;
    private int height;
    private boolean backdropShown = false;
    private Drawable openIcon;
    private Drawable closeIcon;


    public boolean isBackdropShown() {
        return backdropShown;
    }

    BackdropManger(Context context, View sheet) {
        this(context, sheet, null);
    }

    BackdropManger(Context context, View sheet, @Nullable Interpolator interpolator) {
        this(context, sheet, interpolator, null, null);
    }

    public BackdropManger(
            Context context, View sheet, @Nullable Interpolator interpolator,
            @Nullable View viewToHide, @Nullable Drawable closeIcon) {
        this.context = context;
        this.sheet = sheet;
        this.interpolator = interpolator;
        this.viewToHide = viewToHide;
        this.closeIcon = closeIcon;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;


    }


    public void showHideBackDrop() {
        backdropShown = !backdropShown;

        // Cancel the existing animations
        animatorSet.removeAllListeners();
        animatorSet.end();
        animatorSet.cancel();

        updateBackground();

        final int translateY = height - context.getResources().getDimensionPixelSize(R.dimen.backdrop_reveal_height);

        ObjectAnimator animator = ObjectAnimator.ofFloat(sheet, "translationY", backdropShown ? translateY : 0);
        animator.setDuration(500);
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        animatorSet.play(animator);
        animator.start();


    }

    private void updateBackground() {
        if (backdropShown) {
            sheet.setBackgroundResource(R.drawable.backdrop_shape);
        } else {
            sheet.setBackgroundColor(ContextCompat.getColor(context, R.color.white));

        }
    }

}
