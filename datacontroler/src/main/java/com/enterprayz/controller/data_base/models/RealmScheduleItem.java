package com.enterprayz.controller.data_base.models;

import com.enterprayz.controller.beans.interfaces.IScheduleCard;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by hacker on 10.05.16.
 */
public class RealmScheduleItem extends RealmObject implements IScheduleCard {
    private String title;
    private Date startDate;
    private Date endDate;

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
