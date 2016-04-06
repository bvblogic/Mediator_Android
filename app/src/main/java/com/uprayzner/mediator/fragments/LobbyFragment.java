package com.uprayzner.mediator.fragments;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.enterprayz.controller.DataController;
import com.enterprayz.controller.beans.interfaces.ILobbyNewsCard;
import com.enterprayz.controller.modules.interfaces.base.CardListCallback;
import com.uprayzner.mediator.R;
import com.uprayzner.mediator.fragments.common.ActionFragment;
import com.uprayzner.mediator.views.LobbyNewsCardView;
import com.uprayzner.mediator.views.VideoView;

import java.util.List;

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
public class LobbyFragment extends ActionFragment {
    private VideoView videoViewIntro;
    private View shadow;
    private View bg;
    private HorizontalScrollView hsvCardList;
    private List<ILobbyNewsCard> newsCards;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    private int lastColor = Color.WHITE;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_lobby;
    }

    @Override
    protected View prepareUI(View root) {
        iniUI(root);
        setListeners();
        iniVideo(videoViewIntro);
        return super.prepareUI(root);
    }


    @Override
    public void onPause() {
        super.onPause();
        videoViewIntro.pause();
    }

    @Override
    public void onDetach() {
        handler.removeMessages(1);
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        videoViewIntro.play();
        handler.removeMessages(1);
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    public void onDestroyView() {
        newsCardCardListCallback.unregister();
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsCardCardListCallback.register();
        iniNewsCardList();
    }

    private void iniUI(View root) {
        View bgContainer = root.findViewById(R.id.container_bg);
        shadow = bgContainer.findViewById(R.id.ll_shadow);
        bg = root.findViewById(R.id.iv_background);
        videoViewIntro = (VideoView) bgContainer.findViewById(R.id.vv_intro);
        hsvCardList = (HorizontalScrollView) root.findViewById(R.id.hsv_card_list);
    }

    private void setListeners() {

    }

    private void iniVideo(VideoView view) {
        Uri path = Tools.ResourceToUri(getActivity(), R.raw.intro);
        view.setDataSource(getContext(), path);
        view.setLooping(true);
    }

    private void iniNewsCardList() {
        DataController.getInstance().feed().getLobbyNews(true, true).withCallback(newsCardCardListCallback);
    }

    private void updateUI(final List<ILobbyNewsCard> data) {
        if (newsCards != null && hsvCardList != null) {
            hsvCardList.removeAllViews();
            LinearLayout container = new LinearLayout(getContext());
            for (final ILobbyNewsCard card : data) {
                LobbyNewsCardView view = new LobbyNewsCardView(getActivity());
                view.setCard(card);
                ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(
                        (int) getResources().getDimension(R.dimen.news_card_width),
                        (int) getResources().getDimension(R.dimen.news_card_height)
                );
                container.addView(view, marginParams);
            }
            hsvCardList.addView(container);

            for (int i = 0; i < container.getChildCount(); i++) {
                View view = container.getChildAt(i);
                ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                marginParams.setMargins(
                        i == 0 ? (int) getResources().getDimension(R.dimen.content_horizontal_margin) : marginParams.leftMargin,
                        marginParams.topMargin,
                        (int) getResources().getDimension(R.dimen.content_horizontal_margin),
                        marginParams.bottomMargin);
            }
            videoViewIntro.getBitmap();
        }
    }


    private void animateBgColorChanging(final View bg, final View shadow, int from, final int to) {
        final int f = from;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimator.setDuration(100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lastColor = (Integer) argbEvaluator.evaluate(valueAnimator.getAnimatedFraction(), f, to);
                bg.setBackgroundColor(lastColor);

                int[] colors = {Color.TRANSPARENT, lastColor};
                GradientDrawable gd = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM, colors);

                gd.setCornerRadius(0f);
                shadow.setBackground(gd);
            }
        });
        valueAnimator.start();
    }

    CardListCallback<ILobbyNewsCard> newsCardCardListCallback = new CardListCallback<ILobbyNewsCard>() {
        @Override
        public void onSuccess(List<ILobbyNewsCard> data) {
            LobbyFragment.this.newsCards = data;
            updateUI(data);
        }

        @Override
        public void onError(Throwable error) {

        }
    };

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            handler.sendEmptyMessageDelayed(1, 100);
            Palette.from(videoViewIntro.getBitmap()).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    animateBgColorChanging(bg, shadow, lastColor, palette.getDarkVibrantColor(lastColor));
                }
            });
            return false;
        }
    });
}
