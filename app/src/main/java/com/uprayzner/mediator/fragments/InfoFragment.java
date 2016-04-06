package com.uprayzner.mediator.fragments;

import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.enterprayz.controller.DataController;
import com.enterprayz.controller.beans.interfaces.ILobbyEventCard;
import com.enterprayz.controller.modules.interfaces.base.CardCallback;
import com.uprayzner.mediator.R;
import com.uprayzner.mediator.fragments.common.ActionFragment;

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
public class InfoFragment extends ActionFragment implements View.OnClickListener {
    private TextView toolBarCenterTitle;
    private TextView tvInfo;
    private View btnDev;
    private ILobbyEventCard data;

    @Override
    protected int getStyle() {
        return R.style.AppTheme_InfoFragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_info;
    }

    protected View prepareUI(View root) {
        iniUI(root);
        setListeners();
        setToolBarTitle();
        return super.prepareUI(root);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }


    @Override
    public void onDestroyView() {
        cardCardCallback.unregister();
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardCardCallback.register();
        loadData();
    }

    @Override
    public void onClick(View view) {
        String url = "";
        switch (view.getId()) {
            case R.id.ll_redirect_info: {
                url = getString(R.string.dev_redirect_url);
                break;
            }
        }
        try {
            Tools.openBrowser(getActivity(), url);
        } catch (ActivityNotFoundException ignored) {

        }
    }

    private void iniUI(View root) {
        toolBarCenterTitle = (TextView) root.findViewById(R.id.toolbar_container).findViewById(R.id.toolbar_center_title);
        tvInfo = (TextView) root.findViewById(R.id.tv_info);
        btnDev = root.findViewById(R.id.ll_redirect_info);
        fillUI();

    }

    private void setListeners() {
        btnDev.setOnClickListener(this);
    }

    private void setToolBarTitle() {
        toolBarCenterTitle.setText(getString(R.string.fragment_info_title));
    }

    private void loadData() {
        DataController.getInstance().feed().getLobbyEvent(true, true).withCallback(cardCardCallback);
    }

    private void fillUI() {
        if (tvInfo != null && data != null) {
            tvInfo.setText(data.getDestination());
        }
    }

    private void alphaIn(final View view) {
        if (view.getVisibility() == View.VISIBLE) {
            return;
        }
        Animation alpha = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in);
        alpha.setDuration(500);
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.setVisibility(View.VISIBLE);
                    }
                }, 1);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(alpha);
    }

    private CardCallback<ILobbyEventCard> cardCardCallback = new CardCallback<ILobbyEventCard>() {
        @Override
        public void onSuccess(boolean preload, ILobbyEventCard data) {
            InfoFragment.this.data = data;
            fillUI();
        }

        @Override
        public void onError(Throwable error) {

        }
    };
}
