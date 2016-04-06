package com.enterprayz.controller.modules.iml.ui_modules;

import com.enterprayz.controller.beans.interfaces.IScheduleCard;
import com.enterprayz.controller.beans.interfaces.IScheduleGroupCard;
import com.enterprayz.controller.modules.interfaces.IAction;
import com.enterprayz.controller.modules.interfaces.base.ActionResponse;
import com.enterprayz.controller.modules.interfaces.base.ICallBack;

/**
 * Created by con on 17.04.16.
 */
public class Schedule extends BaseUIModule<IAction> implements ISchedule {

    public Schedule(IAction action) {
        super(action);
    }

    @Override
    public ActionResponse<IScheduleGroupCard> getScheduleGroups(final boolean update, final boolean cachePreload) {
        return new ActionResponse<IScheduleGroupCard>() {
            @Override
            protected void onPrepare(ICallBack callBack) {
                action.setCallBack(callBack);
                action.getScheduleGroups(update, cachePreload);
            }
        };
    }

    @Override
    public ActionResponse<IScheduleCard> getScheduleCards(final int groupId) {
        return new ActionResponse<IScheduleCard>() {
            @Override
            protected void onPrepare(ICallBack callBack) {
                action.setCallBack(callBack);
                action.getScheduleCards(groupId);
            }
        };
    }
}