package com.enterprayz.controller.modules.iml.ui_modules;

import com.enterprayz.controller.beans.interfaces.ILobbyEventCard;
import com.enterprayz.controller.beans.interfaces.ILobbyNewsCard;
import com.enterprayz.controller.modules.interfaces.base.ActionResponse;

/**
 * Created by volchochka on 26.02.16.
 */
public interface ILobby {
    ActionResponse<ILobbyNewsCard> getLobbyNews(boolean update, boolean cachePreload);

    ActionResponse<ILobbyEventCard> getLobbyEvent(boolean update, boolean cachePreload);
}
