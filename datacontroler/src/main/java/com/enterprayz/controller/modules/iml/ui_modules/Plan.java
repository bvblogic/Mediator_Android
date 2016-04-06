package com.enterprayz.controller.modules.iml.ui_modules;

import com.enterprayz.controller.beans.interfaces.IPlanCard;
import com.enterprayz.controller.beans.interfaces.IPlanStandCard;
import com.enterprayz.controller.modules.interfaces.IAction;
import com.enterprayz.controller.modules.interfaces.base.ActionResponse;
import com.enterprayz.controller.modules.interfaces.base.ICallBack;

/**
 * Created by con on 19.04.16.
 */
public class Plan extends BaseUIModule<IAction> implements IPlan {

    public Plan(IAction action) {
        super(action);
    }

    @Override
    public ActionResponse<IPlanCard> getPlanCard(final boolean update, final boolean cachePreload) {
        return new ActionResponse<IPlanCard>() {
            @Override
            protected void onPrepare(ICallBack callBack) {
                action.setCallBack(callBack);
                action.getPlanCard(update, cachePreload);
            }
        };
    }

    @Override
    public ActionResponse<IPlanStandCard> getStandCard(final String id) {
        return new ActionResponse<IPlanStandCard>() {
            @Override
            protected void onPrepare(ICallBack callBack) {
                action.setCallBack(callBack);
                action.getStandCard(id);
            }
        };
    }
}
