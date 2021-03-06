package com.yc.overlaprecyclerview.ladder.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.yc.overlaprecyclerview.R;


/**
 * Created by thunderpunch on 2017/4/4
 * Description:
 */

public class VerticalSampleItemLayout extends FrameLayout {
    private CardView removeV;
    private CardView contentV;

    public VerticalSampleItemLayout(@NonNull Context context) {
        super(context);
    }

    public VerticalSampleItemLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalSampleItemLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentV = (CardView) findViewById(R.id.content);
        removeV = (CardView) findViewById(R.id.remove);
        if (contentV == null || removeV == null) {
            throw new IllegalArgumentException("Not find view with resourceId R.id.content||R.id.add by VerticalSampleItemLayout");
        }
    }

    public void setElevation(float elevation) {
        ViewCompat.setElevation(contentV, elevation);
        ViewCompat.setElevation(removeV, elevation);
    }

    public void setStrokeColor(int color) {
        contentV.setCardBackgroundColor(color);
        removeV.setCardBackgroundColor(color);
    }
}
