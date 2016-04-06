package com.enterprayz.controller;

import com.enterprayz.controller.beans.iml.LobbyNewsCard;
import com.enterprayz.controller.beans.interfaces.ILobbyNewsCard;
import com.enterptayz.rss.RssItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by con on 28.03.16.
 */
public class ModelConverter {


    public static List<ILobbyNewsCard> getLobbyNewsCards(List<RssItem> list) {
        List<ILobbyNewsCard> result = new ArrayList<>();
        for (RssItem article : list) {
            result.add(new LobbyNewsCard(
                    article.getDate(),
                    article.getTitle(),
                    article.getDescription(),
                    article.getContent()));
        }
        return result;
    }
}
