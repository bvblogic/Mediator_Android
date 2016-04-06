package com.enterprayz.controller.beans.iml;

import com.enterprayz.controller.beans.interfaces.IScheduleCard;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by con on 17.04.16.
 */
@ParseClassName("EventPart")
public class ScheduleCard extends ParseObject implements IScheduleCard {

    public ScheduleCard() {

    }

    @Override
    public String getTitle() {
        return getString("title");
    }

    @Override
    public Date getStartDate() {
        return getDate("startAt");
    }

    @Override
    public Date getEndDate() {
        return getDate("finishAt");
    }
}
