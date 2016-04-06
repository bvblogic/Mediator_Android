package com.uprayzner.mediator.utils;

import android.graphics.Rect;
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
public class VerticalSpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int topSpace;
    private int bottomSpace;

    public VerticalSpacesItemDecoration(int topSpace, int bottomSpace) {
        this.topSpace = topSpace;
        this.bottomSpace = bottomSpace;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        final int itemPosition = parent.getChildAdapterPosition(view);
        final int itemCount = state.getItemCount();

        /** first position */
        if (itemPosition == 0) {
            outRect.top = topSpace;
        }
        /** last position */
        else if (itemCount > 0 && itemPosition == itemCount - 1) {
            outRect.bottom = bottomSpace;
        }
        /** positions between first and last */
        else {

        }
    }
}