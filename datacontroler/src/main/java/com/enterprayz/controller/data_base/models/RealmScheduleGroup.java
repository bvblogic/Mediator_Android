package com.enterprayz.controller.data_base.models;

import com.enterprayz.controller.beans.interfaces.IScheduleGroupCard;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by hacker on 10.05.16.
 */
public class RealmScheduleGroup extends RealmObject implements IScheduleGroupCard {
    private int id;
    private String title;
    private RealmList<RealmScheduleItem> cards;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public RealmList<RealmScheduleItem> getCards() {
        return cards;
    }

    public void setCards(RealmList<RealmScheduleItem> cards) {
        this.cards = cards;
    }
}
