package com.tern.ui.scaleviewpager;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class ScalePageTransformer implements ViewPager.PageTransformer {

    public static final float MAX_SCALE = 1.2f;
    public static final float MIN_SCALE = 0.6f;
    private static final String TAG = "ScalePageTransformer";

    @Override
    public void transformPage(View page, float position) {

        if (position < -1) {
            position = -1;
        } else if (position > 1) {
            position = 1;
        }

        float tempScale = position < 0 ? 1 + position : 1 - position;

        float slope = (MAX_SCALE - MIN_SCALE) / 1;

        float scaleValue = MIN_SCALE + tempScale * slope;
        Log.i(TAG, "transformPage: scale = " + scaleValue);
        Log.i(TAG, "transformPage: tempScale = " + tempScale);
        Log.i(TAG, "transformPage: slope = " + slope);
        ViewHelper.setScaleX(page, scaleValue);
        ViewHelper.setScaleY(page, scaleValue);
        ViewHelper.setAlpha(page, scaleValue);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            page.getParent().requestLayout();
        }
    }
}
