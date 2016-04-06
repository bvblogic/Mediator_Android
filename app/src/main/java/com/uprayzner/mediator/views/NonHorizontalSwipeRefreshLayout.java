package com.uprayzner.mediator.views;

import android.content.Context;
import android.support.v4.widget.SkeletonRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Copyright 2016 U.Prayzner
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class NonHorizontalSwipeRefreshLayout extends SkeletonRefreshLayout {

    private int mTouchSlop;
    private float mPrevX;

    public NonHorizontalSwipeRefreshLayout(Context context) {
        super(context);
        if (!isInEditMode()) {
            mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        }
    }

    public NonHorizontalSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPrevX = MotionEvent.obtain(event).getX();
                break;

            case MotionEvent.ACTION_MOVE:
                final float eventX = event.getX();
                float xDiff = Math.abs(eventX - mPrevX);

                if (xDiff > mTouchSlop) {
                    return false;
                }
        }

        return super.onInterceptTouchEvent(event);
    }
}
