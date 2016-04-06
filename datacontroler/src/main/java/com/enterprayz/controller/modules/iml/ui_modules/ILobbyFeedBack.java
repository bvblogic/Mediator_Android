package com.enterprayz.controller.modules.iml.ui_modules;

/**
 * Created by con on 14.04.16.
 */
public interface ILobbyFeedBack {
    void getLobbyNewsCards(boolean update, boolean cachePreload);

    void getLobbyEventCard(boolean update, boolean cachePreload);
}
