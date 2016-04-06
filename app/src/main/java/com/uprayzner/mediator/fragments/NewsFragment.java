package com.uprayzner.mediator.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.enterprayz.controller.beans.interfaces.ILobbyNewsCard;
import com.uprayzner.mediator.R;
import com.uprayzner.mediator.activities.EventActivity;
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
public class NewsFragment extends ActionFragment {
    public static final String BUNDLE_CARD_DATA_OBJ = "card";
    private WebView myWebView;
    private Toolbar toolbar;

    private ILobbyNewsCard eventNewsCard;

    public static NewsFragment getInstance(ILobbyNewsCard object) {
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_CARD_DATA_OBJ, object);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (getArguments() != null) {
            this.eventNewsCard = (ILobbyNewsCard) getArguments().getSerializable(BUNDLE_CARD_DATA_OBJ);
        }
        super.onCreate(savedInstanceState);
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    protected View prepareUI(View root) {
        iniUI(root);
        prepareToolBar();
        fillWebView();
        return super.prepareUI(root);
    }


    private void iniUI(View root) {
        myWebView = (WebView) root.findViewById(R.id.wv_content);
        toolbar = (Toolbar) root.findViewById(R.id.toolbar);
    }

    private void prepareToolBar() {
        toolbar.setTitle(eventNewsCard.getEventTitle());
        ((EventActivity) getActivity()).setSupportActionBar(toolbar);
        ((EventActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((EventActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void fillWebView() {
        String mime = "text/html";
        String encoding = "utf-8";

        String cssStyle = Tools.readText(getActivity(), R.raw.news_css_style);

        cssStyle = cssStyle.replace(getString(R.string.html_body_key), eventNewsCard.getHtmlContent());
        myWebView.getSettings().setJavaScriptEnabled(true);


        myWebView.loadDataWithBaseURL(null, cssStyle, mime, encoding, null);

    }
}
