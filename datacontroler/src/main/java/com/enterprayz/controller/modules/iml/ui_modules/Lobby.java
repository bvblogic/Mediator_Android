package com.enterprayz.controller.modules.iml.ui_modules;

import com.enterprayz.controller.beans.interfaces.ILobbyEventCard;
import com.enterprayz.controller.beans.interfaces.ILobbyNewsCard;
import com.enterprayz.controller.modules.interfaces.IAction;
import com.enterprayz.controller.modules.interfaces.base.ActionResponse;
import com.enterprayz.controller.modules.interfaces.base.ICallBack;

/**
 * Created by volchochka on 26.02.16.
 */
public class Lobby extends BaseUIModule<IAction> implements ILobby {

    public Lobby(IAction action) {
        super(action);
    }


    @Override
    public ActionResponse<ILobbyNewsCard> getLobbyNews(final boolean update, final boolean cachePreload) {
        return new ActionResponse<ILobbyNewsCard>() {
            @Override
            public void onPrepare(ICallBack callBack) {
                action.setCallBack(callBack);
                action.getLobbyNewsCards(update, cachePreload);
            }
        };
    }

    @Override
    public ActionResponse<ILobbyEventCard> getLobbyEvent(final boolean update, final boolean cachePreload) {
        return new ActionResponse<ILobbyEventCard>() {
            @Override
            public void onPrepare(ICallBack callBack) {
                action.setCallBack(callBack);
                action.getLobbyEventCard(update, cachePreload);
            }
        };
    }
}
