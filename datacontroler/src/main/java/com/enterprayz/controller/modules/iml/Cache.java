package com.enterprayz.controller.modules.iml;

import com.enterprayz.controller.beans.interfaces.ILobbyEventCard;
import com.enterprayz.controller.beans.interfaces.ILobbyNewsCard;
import com.enterprayz.controller.beans.interfaces.IPlanCard;
import com.enterprayz.controller.beans.interfaces.IPlanStandCard;
import com.enterprayz.controller.beans.interfaces.IScheduleCard;
import com.enterprayz.controller.beans.interfaces.IScheduleGroupCard;
import com.enterprayz.controller.data_base.DbProvider;
import com.enterprayz.controller.modules.ActionBackEnd;
import com.enterprayz.controller.modules.interfaces.base.IBaseModule;
import com.enterprayz.controller.modules.interfaces.base.ICardCallback;
import com.enterprayz.controller.modules.interfaces.base.ICardListCallback;

import java.util.List;

/**
 * Created by urec on 30.11.15 urecki22@gmail.com.
 */
public class Cache implements IBaseModule {

    public List<ILobbyNewsCard> getNewsCards() {
        return DbProvider.getInstiance().getNews();
    }

    public void setEventCards(List<ILobbyNewsCard> eventCards) {
        DbProvider.getInstiance().updateNews(eventCards);
    }

    public ILobbyEventCard getEventCard() {
        return DbProvider.getInstiance().getEvent();
    }

    public void setEventCard(ILobbyEventCard eventCard) {
        DbProvider.getInstiance().updateEvent(eventCard);
    }


    public List<IScheduleGroupCard> getScheduleGroupCards() {
        return DbProvider.getInstiance().getSchedulesGroups();
    }


    public List<IScheduleCard> getScheduleCards(int groupId) {
        return DbProvider.getInstiance().getSchedulesCards(groupId);
    }

    public void setGroupCards(List<IScheduleGroupCard> groupCards) {
        if (groupCards != null) {
            DbProvider.getInstiance().updateSchedules(groupCards);
        }
    }

    public IPlanCard getPlanCard() {
        return DbProvider.getInstiance().getPlan();
    }

    public IPlanStandCard getStandCard(String id) {
        return DbProvider.getInstiance().getStand(id);
    }

    public void setPlanCard(IPlanCard planCard) {
        DbProvider.getInstiance().updateStands(planCard);
    }
}
