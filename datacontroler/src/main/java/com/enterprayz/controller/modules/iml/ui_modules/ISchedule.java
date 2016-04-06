package com.enterprayz.controller.modules.iml.ui_modules;

import com.enterprayz.controller.beans.iml.ScheduleGroupCard;
import com.enterprayz.controller.beans.interfaces.ILobbyEventCard;
import com.enterprayz.controller.beans.interfaces.ILobbyNewsCard;
import com.enterprayz.controller.beans.interfaces.IScheduleCard;
import com.enterprayz.controller.beans.interfaces.IScheduleGroupCard;
import com.enterprayz.controller.modules.interfaces.base.ActionResponse;

/**
 * Created by volchochka on 26.02.16.
 */
public interface ISchedule {
    ActionResponse<IScheduleGroupCard> getScheduleGroups(boolean update, boolean cachePreload);

    ActionResponse<IScheduleCard> getScheduleCards(int groupId);
}
