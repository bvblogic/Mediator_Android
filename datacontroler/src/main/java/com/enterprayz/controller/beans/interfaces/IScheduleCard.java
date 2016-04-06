package com.enterprayz.controller.beans.interfaces;

import java.util.Date;

/**
 * Created by con on 17.04.16.
 */
public interface IScheduleCard extends ICard {
    String getTitle();

    Date getStartDate();

    Date getEndDate();
}
