package com.uprayzner.mediator.fragments.common;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.enterprayz.controller.beans.interfaces.IPlanCard;
import com.enterprayz.controller.beans.interfaces.IPlanStandCard;
import com.uprayzner.mediator.R;
import com.uprayzner.mediator.views.MapHintView;
import com.uprayzner.mediator.views.MapPin.MapPinView;
import com.qozix.tileview.TileView;
import com.qozix.tileview.markers.MarkerLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import enterprayz.megatools.Tools;

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
public abstract class MapFragment extends ActionFragment {
    private final double IMAGE_QUALITY = 0.6;
    private final List<MapPinView> pinViews = new ArrayList<>();

    private final float MIN_SCALE = 0.5f;
    private final float MAX_SCALE = 2f;

    private MapHintView hintView;
    private MapPinView lastPin;
    private int h;
    private int w;
    private Rect imageBounds;
    private Rect parentBounds;

    private boolean restoreHint;
    private String lastHintData;
    private int lastHintX;
    private int lastHintY;

    @Override
    public void onPause() {
        if (hintView != null) {
            getTileView().removeMarker(hintView);
            hintView = null;
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (lastHintData != null && restoreHint) {
            restoreHint = false;
            showHint(lastHintData, lastHintX, lastHintY);
        }
    }

    public void saveHint() {
        restoreHint = true;
    }

    protected abstract TileView getTileView();

    public void onChoiceMapItem(MapPinView mapPinView, boolean isChecked, boolean fromTouch) {

    }

    public void slideToCenter() {
        if (getTileView() != null) {
            getTileView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getTileView().slideToAndCenterWithScale(w / 2, h / 2, MIN_SCALE);
                }
            }, 20);
        }
    }

    public void setupMapOverlay(Bitmap bitmap, IPlanCard<IPlanStandCard> data) {
        w = bitmap.getWidth();
        h = bitmap.getHeight();

        RelativeLayout relativeLayout = new RelativeLayout(getActivity());
        ImageView logo = new ImageView(getActivity());

        Glide.with(getActivity())
                .load(R.drawable.map)
                .override((int) (w * IMAGE_QUALITY), (int) (h * IMAGE_QUALITY))
                .crossFade(1000)
                .into(logo);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w, h);
        logo.setLayoutParams(params);

        RelativeLayout.LayoutParams logoLayoutParams = new RelativeLayout.LayoutParams(w, h);
        logoLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);


        relativeLayout.addView(logo, logoLayoutParams);

        int scaledW = (int) (w / MIN_SCALE);
        int scaledH = (int) (h / MIN_SCALE);

        parentBounds = new Rect(0, 0, scaledW, scaledH);

        int imageW = scaledW / 2;
        int imageH = scaledH / 2;

        imageBounds = new Rect(
                -((scaledW / 2) - (imageW / 2)),
                -((scaledH / 2) - (imageH / 2)),
                (scaledW / 2) + (imageW / 2),
                (scaledH / 2) + (imageH / 2));

        getTileView().setScaleLimits(MIN_SCALE, MAX_SCALE);
        getTileView().addScalingViewGroup(relativeLayout);
        getTileView().setSize(parentBounds.width(), parentBounds.height());
        getTileView().defineBounds(imageBounds.left, imageBounds.top, imageBounds.right, imageBounds.bottom);
        getTileView().setMarkerAnchorPoints(-0.5f, -0.5f);
        getTileView().setMarkerTapListener(mMarkerTapListener);
        slideToCenter();
        int pos = 0;
        for (IPlanStandCard standCard : data.getPlanStandCard()) {
            addPin(standCard, pos);
            pos++;
        }
    }

    private void addPin(final IPlanStandCard card, final int pos) {
        getTileView().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getActivity() == null) {
                    return;
                }
                final MapPinView pinView = new MapPinView(getActivity());
                pinViews.add(pinView);
                pinView.setCard(card, pos);
                pinView.setListener(mapViewListener);
                getTileView().addMarker(pinView, card.getX(), card.getY(), null, null);
                pinView.animateEntry();
            }
        }, Tools.getRandomInteger(1, 10, new Random()) * 200);
    }

    public void frameTo(final int x, final int y) {
        final TileView tile = getTileView();
        tile.post(new Runnable() {
            @Override
            public void run() {
                tile.slideToAndCenterWithScale(
                        (0 - imageBounds.left * (int) MAX_SCALE) + (x * (int) MAX_SCALE),
                        (0 - imageBounds.top * (int) MAX_SCALE) + (y * (int) MAX_SCALE),
                        MAX_SCALE);
            }
        });
    }

    private void showHint(final String title, final int x, final int y) {
        lastHintData = title;
        lastHintX = x;
        lastHintY = y;
        if (hintView == null) {
            hintView = new MapHintView(getActivity());
            hintView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hintView.show(title);
                    getTileView().addMarker(hintView, x, y, -0.50f, -1.6f);
                }
            }, 100);
        } else {
            hintView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hintView.show(title);
                    getTileView().moveMarker(hintView, x, y);
                }
            }, 100);
        }
    }


    private final MapPinView.OnMapViewListener mapViewListener = new MapPinView.OnMapViewListener() {
        @Override
        public void onChecked(MapPinView mapPinView, boolean isChecked, boolean fromTouch) {
            showHint(mapPinView.getCard().getTitle(), mapPinView.getCard().getX(), mapPinView.getCard().getY());
            onChoiceMapItem(mapPinView, isChecked, fromTouch);
        }
    };

    private final MarkerLayout.MarkerTapListener mMarkerTapListener = new MarkerLayout.MarkerTapListener() {
        @Override
        public void onMarkerTap(View v, int x, int y) {
            if (v instanceof MapPinView) {
                if (lastPin != null) {
                    lastPin.setCheck(false, true, false);
                }

                lastPin = (MapPinView) v;
                lastPin.setCheck(true, true, true);
                frameTo(lastPin.getCard().getX(), lastPin.getCard().getY());
            }
        }
    };
}
