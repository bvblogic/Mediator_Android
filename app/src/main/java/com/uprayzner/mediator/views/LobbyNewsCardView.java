package com.uprayzner.mediator.views;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enterprayz.controller.beans.interfaces.ILobbyNewsCard;
import com.uprayzner.mediator.R;
import com.uprayzner.mediator.utils.SafeURLSpan;

import java.util.Date;

import enterprayz.megatools.Converter;

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
public class LobbyNewsCardView extends LinearLayout {
    private View parent;
    private TextView tvDate;
    private TextView tvHeader;
    private MultilineEllipsizeTextView tvContent;


    public LobbyNewsCardView(Context context) {
        super(context);
        ini();
    }

    public LobbyNewsCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ini();
    }

    public LobbyNewsCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini();
    }


    private void ini() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_lobby_event_card, this, true);
        iniUI();
        if (isInEditMode()) {
            setDateText(new Date());
            setHeaderText("Продажа билетов началась");
            setContentText("Продажа билетов на Старкон 2015 началась! И если у Вас уже есть самые серьезные намерения посетить нас в этом году, то Вам несказанно повезло. До 27 марта включительно Вы сможете приобрести…");
        }
    }

    private void iniUI() {
        parent = findViewById(R.id.ll_container);
        tvDate = (TextView) findViewById(R.id.tv_date_header);
        tvHeader = (TextView) findViewById(R.id.tv_data_header);
        tvContent = (MultilineEllipsizeTextView) findViewById(R.id.tv_content);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        parent.setOnClickListener(l);
    }

    public void setCard(ILobbyNewsCard card) {
        setDateText(card.getEventDate());
        setHeaderText(card.getEventTitle());
        setContentText(card.getDescription());
    }

    public void setDateText(Date date) {
        tvDate.setText(Converter.getFormatDate(date, "dd.MM.yyyy"));
    }

    public void setHeaderText(String text) {
        tvHeader.setText(text);
    }

    public void setContentText(String text) {
        tvContent.setEllipsizeText(text);
    }

    public void setContentTextAsHtml(String text) {
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        tvContent.setText(SafeURLSpan.parseSafeHtml(text));
    }
}