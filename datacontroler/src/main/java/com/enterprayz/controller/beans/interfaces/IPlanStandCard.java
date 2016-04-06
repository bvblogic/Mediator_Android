package com.enterprayz.controller.beans.interfaces;

import com.enterprayz.controller.beans.enums.StandType;

import java.io.Serializable;

/**
 * Created by con on 19.04.16.
 */
public interface IPlanStandCard extends ICard {
    String getId();

    String getImageUrl();

    int getX();

    int getY();

    String getContent();

    String getTitle();

    String getType();

    int getColor();
}
