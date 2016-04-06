package com.enterprayz.controller.beans.iml;

import com.enterprayz.controller.beans.interfaces.IScheduleCard;
import com.enterprayz.controller.beans.interfaces.IScheduleGroupCard;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by con on 17.04.16.
 */
@ParseClassName("EventType")
public class ScheduleGroupCard extends ParseObject implements IScheduleGroupCard {

    private List<ScheduleCard> cards = new ArrayList<>();

    public ScheduleGroupCard() {
    }

    public void setCards(List<ScheduleCard> cards) {
        this.cards = cards;
    }

    @Override
    public int getId() {
        return getInt("type");
    }

    @Override
    public String getTitle() {
        return getString("title");
    }

    @Override
    public List<IScheduleCard> getCards() {
        return new ArrayList<IScheduleCard>(cards);
    }
}
