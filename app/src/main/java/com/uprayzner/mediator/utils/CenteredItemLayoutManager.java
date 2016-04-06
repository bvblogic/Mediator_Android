package com.uprayzner.mediator.utils;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
public class CenteredItemLayoutManager extends LinearLayoutManager {

    private int centeredItemOffset;

    public CenteredItemLayoutManager(Context context) {
        super(context, LinearLayoutManager.HORIZONTAL, false);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller = new Scroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    public void setCenteredItemOffset(int centeredItemOffset) {
        this.centeredItemOffset = centeredItemOffset;
    }

    private class Scroller extends LinearSmoothScroller {

        public Scroller(Context context) {
            super(context);
        }

        @Override
        public PointF computeScrollVectorForPosition(int targetPosition) {
            return CenteredItemLayoutManager.this.computeScrollVectorForPosition(targetPosition);
        }

        @Override
        public int calculateDxToMakeVisible(View view, int snapPreference) {
            return super.calculateDxToMakeVisible(view, SNAP_TO_START) + centeredItemOffset;
        }
    }
}