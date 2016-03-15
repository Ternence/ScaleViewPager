package com.tern.ui.scaleviewpager;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class ScalePageTransformer implements ViewPager.PageTransformer {

    private static final String TAG = "ScalePageTransformer";

    public static final float BASE_SCALE = 0.7f;    // BASE_SCALE + f(X/Y)  = 1
    public static final float MIN_X_SCALE = 0.4f;
    private static final float MIN_Y_SCALE = -0.3f;

    @Override
    public void transformPage(View page, float position) {

        if (position < -1) {
            position = -1;
        } else if (position > 1) {
            position = 1;
        }

        float tempScale = position < 0 ? 1 + position : 1 - position;

        float scaleXValue = BASE_SCALE + tempScale * MIN_X_SCALE;
        float scaleYValue = BASE_SCALE + tempScale * MIN_Y_SCALE;
        ViewHelper.setScaleX(page, scaleXValue);
        ViewHelper.setScaleY(page, scaleYValue);
        ViewHelper.setAlpha(page, scaleXValue);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            page.getParent().requestLayout();
        }
    }
}
