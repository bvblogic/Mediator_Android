package com.uprayzner.mediator.views.MapPin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
public class MapPinView extends LinearLayout {
    private IPlanStandCard card;
    private ImageView bg;

    private int pinSize;
    private float pinScaleSize;

    private int position;

    private OnMapViewListener listener;

    private boolean isSelected;

    public MapPinView(Context context) {
        super(context);
        ini();
    }

    public MapPinView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ini();
    }

    public MapPinView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini();
    }

    private void ini() {
        if (isInEditMode()) {
            return;
        }
        pinSize = (int) getResources().getDimension(R.dimen.map_pin_min_size);
        pinScaleSize = getResources().getDimension(R.dimen.map_pin_max_size) / getResources().getDimension(R.dimen.map_pin_min_size);

        bg = new ImageView(getContext());
        bg.setAdjustViewBounds(true);
        addView(bg);
    }

    public void animateEntry() {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(600);
        fadeIn.setFillAfter(true);
        setAnimation(fadeIn);
        startAnimation(fadeIn);
    }

    public int getPosition() {
        return position;
    }

    public IPlanStandCard getCard() {
        return card;
    }

    public void setCard(IPlanStandCard card, int position) {
        this.position = position;
        this.card = card;
        createColoredPoint(card.getColor());
    }

    public void setCheck(boolean isSelected, boolean isTouch, boolean notify) {
        if (isSelected == this.isSelected) {
            return;
        }
        this.isSelected = isSelected;
        animateScale(isSelected);
        if (listener != null && notify) {
            listener.onChecked(this, isSelected, isTouch);
        }
    }

    private void animateScale(boolean isScaleIn) {
        ScaleAnimation grow = new ScaleAnimation(
                isScaleIn ? 1 : pinScaleSize,
                isScaleIn ? pinScaleSize : 1,
                isScaleIn ? 1 : pinScaleSize,
                isScaleIn ? pinScaleSize : 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        AnimationSet animSet = new AnimationSet(true);
        animSet.setDuration(500);
        animSet.setFillEnabled(true);
        animSet.setFillAfter(true);
        animSet.addAnimation(grow);
        startAnimation(animSet);
    }

    private void createColoredPoint(int color) {
        Bitmap bmp = MapPinCache.getInstiance().getImage(color);
        if (bmp == null) {
            int shadowRadius = (pinSize / 2) + 8;
            int borderRadius = pinSize / 2;
            int bodyRadius = borderRadius - 2;

            int cx = shadowRadius;
            int cy = shadowRadius;

            Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
            bmp = Bitmap.createBitmap(shadowRadius * 2, shadowRadius * 2, conf); // this creates a MUTABLE bitmap
            Canvas canvas = new Canvas(bmp);

            drawRadialGradient(canvas, true, shadowRadius, shadowRadius, cx, cy, color, 0x00bababa);
            drawOval(canvas, borderRadius, cx, cy, color);
            drawRadialGradient(canvas, false, bodyRadius, bodyRadius + 3, cx, cy, Color.WHITE, color);
            MapPinCache.getInstiance().addPin(color, bmp);
        }
        Drawable drawable = new BitmapDrawable(getResources(), bmp);
        bg.setImageDrawable(drawable);
    }

    private void drawRadialGradient(Canvas c, boolean blur, int radius, int gradientRadius, int cx, int cy, int startColor, int endColor) {
        RadialGradient gradient = new RadialGradient(cx, cy, gradientRadius, startColor,
                endColor, Shader.TileMode.CLAMP);
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        if (blur) {
            p.setMaskFilter(new BlurMaskFilter(30, BlurMaskFilter.Blur.SOLID));
        }
        p.setAntiAlias(true);
        p.setDither(true);
        p.setShader(gradient);
        c.drawCircle(cx, cy, radius, p);
    }

    private void drawOval(Canvas c, int radius, int cx, int cy, int color) {
        Paint myPaint = new Paint();
        myPaint.setColor(color);
        myPaint.setMaskFilter(new BlurMaskFilter(5, BlurMaskFilter.Blur.INNER));
        c.drawCircle(cx, cy, radius, myPaint);
    }

    public void setListener(OnMapViewListener listener) {
        this.listener = listener;
    }

    public interface OnMapViewListener {
        void onChecked(MapPinView mapPinView, boolean isChecked, boolean fromTouch);
    }

}
