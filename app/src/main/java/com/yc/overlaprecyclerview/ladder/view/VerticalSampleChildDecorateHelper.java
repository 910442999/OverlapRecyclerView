package com.yc.overlaprecyclerview.ladder.view;

import android.support.v4.view.ViewCompat;
import android.view.View;


/**
 * Created by thunderpunch on 2017/4/4
 * Description:
 */

public class VerticalSampleChildDecorateHelper implements LadderLayoutManager.ChildDecorateHelper {
    private float mElevation;

    public VerticalSampleChildDecorateHelper(float maxElevation) {
        mElevation = maxElevation;
    }

    @Override
    public void decorateChild(View child, float posOffsetPercent, float layoutPercent, boolean isBottom) {
        VerticalSampleItemLayout v = (VerticalSampleItemLayout) child;
        if (isBottom) {
            ViewCompat.setAlpha(v, posOffsetPercent);
        } else {
            ViewCompat.setAlpha(v, 1);
        }
        v.setElevation((float) (layoutPercent * mElevation * 0.7 + mElevation * 0.3));
    }
}
