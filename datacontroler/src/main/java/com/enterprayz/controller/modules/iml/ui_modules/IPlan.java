package com.enterprayz.controller.modules.iml.ui_modules;

import com.enterprayz.controller.beans.interfaces.IPlanCard;
import com.enterprayz.controller.beans.interfaces.IPlanStandCard;
import com.enterprayz.controller.modules.interfaces.base.ActionResponse;

/**
 * Created by con on 19.04.16.
 */
public interface IPlan {
    ActionResponse<IPlanCard> getPlanCard(boolean update, boolean cachePreload);

    ActionResponse<IPlanStandCard> getStandCard(String id);
}
