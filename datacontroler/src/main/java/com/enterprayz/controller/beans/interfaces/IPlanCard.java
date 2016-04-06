package com.enterprayz.controller.beans.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by con on 19.04.16.
 */
public interface IPlanCard<T extends IPlanStandCard> extends ICard {
    String getPlanImageUrl();

    List<T> getPlanStandCard();
}
