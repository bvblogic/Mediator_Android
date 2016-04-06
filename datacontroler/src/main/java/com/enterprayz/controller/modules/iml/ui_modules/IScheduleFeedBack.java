package com.enterprayz.controller.modules.iml.ui_modules;

/**
 * Created by con on 17.04.16.
 */
public interface IScheduleFeedBack {
    void getScheduleGroups(boolean update, boolean cachePreload);

    void getScheduleCards(int groupId);

}
