package com.uprayzner.mediator.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enterprayz.controller.beans.interfaces.IPlanStandCard;
import com.uprayzner.mediator.R;

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
public class MapHintView extends LinearLayout {
    private IPlanStandCard card;
    private View hintContainer;
    private TextView hintTitle;

    public MapHintView(Context context) {
        super(context);
        ini();
    }

    public MapHintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ini();
    }

    public MapHintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini();
    }

    private void ini() {
        if (isInEditMode()) {
            return;
        }
        LayoutInflater.from(getContext()).inflate(R.layout.view_map_view_hint, this, true);
        iniUi();
    }

    private void iniUi() {
        hintTitle = (TextView) findViewById(R.id.tv_hint_title);
        hintContainer = findViewById(R.id.ll_hint_container);
    }

    public void animateEntry() {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(600);
        fadeIn.setFillAfter(true);
        setAnimation(fadeIn);
        startAnimation(fadeIn);
    }

    public void hide() {
        hintContainer.setVisibility(GONE);
    }

    public void show(String title) {
        hintContainer.setVisibility(VISIBLE);
        hintTitle.setText(title);

    }
}
