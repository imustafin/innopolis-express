package com.mfq.foodle.helper;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.view.View;

public class ColorHelper {


    public static void changeViewColor(View view, @ColorInt int initialColor , @ColorInt int finalColor  ) {
        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        anim.addUpdateListener(animation -> {
            // Use animation position to blend colors.
            float position = animation.getAnimatedFraction();
            int blended = blendColors(initialColor, finalColor, position);

            // Apply blended color to the view.
            view.setBackgroundColor(blended);
        });
        anim.setDuration(300).start();

    }

    private static int blendColors(int from, int to, float ratio) {
        final float inverseRatio = 1f - ratio;

        final float r = Color.red(to) * ratio + Color.red(from) * inverseRatio;
        final float g = Color.green(to) * ratio + Color.green(from) * inverseRatio;
        final float b = Color.blue(to) * ratio + Color.blue(from) * inverseRatio;

        return Color.rgb((int) r, (int) g, (int) b);
    }
}
