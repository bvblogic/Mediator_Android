package com.enterprayz.controller.beans.interfaces;

import java.util.List;

/**
 * Created by con on 17.04.16.
 */
public interface IScheduleGroupCard<T extends IScheduleCard> extends ICard {
    int getId();

    String getTitle();

    List<T> getCards();
}
