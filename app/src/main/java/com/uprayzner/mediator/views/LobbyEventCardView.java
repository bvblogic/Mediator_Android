package com.uprayzner.mediator.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enterprayz.controller.beans.interfaces.ILobbyEventCard;
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
public class LobbyEventCardView extends LinearLayout {
    private TextView tvHeader;
    private TextView tvContent;
    private TextView tvFooter;

    private ILobbyEventCard card;

    public LobbyEventCardView(Context context) {
        super(context);
        ini();
    }

    public LobbyEventCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ini();
    }

    public LobbyEventCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini();
    }


    private void ini() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_lobby_info_card, this, true);
        iniUI();
        if (isInEditMode()) {
            setHeaderText("ЭКСПОФОРУМ");
            setContentText("СПб, Большой\\nПроспект в.о., 103\\nПавильоны 7, 8, 8А\\nс 31 июля\\nпо 2 августа");
            setFooterText("2015");
        }
    }

    private void iniUI() {
        tvHeader = (TextView) findViewById(R.id.tv_header);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvFooter = (TextView) findViewById(R.id.tv_footer);
    }

    public void setCard(ILobbyEventCard card) {
        this.card = card;
    }

    public void setHeaderText(String text) {
        tvHeader.setVisibility(VISIBLE);
        tvHeader.setText(text);
    }

    public void setContentText(String text) {
        tvContent.setVisibility(VISIBLE);
        tvContent.setText(text);
    }

    public void setFooterText(String text) {
        tvFooter.setVisibility(VISIBLE);
        tvFooter.setText(text);
    }
}