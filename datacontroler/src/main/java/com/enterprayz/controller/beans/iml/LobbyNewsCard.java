package com.enterprayz.controller.beans.iml;

import com.enterprayz.controller.beans.interfaces.ILobbyNewsCard;

import java.util.Date;

/**
 * Created by con on 09.04.16.
 */
public class LobbyNewsCard implements ILobbyNewsCard {
    private Date date;
    private String title;
    private String description;
    private String htmlContent;

    public LobbyNewsCard(Date date, String title, String description, String htmlContent) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.htmlContent = htmlContent;
    }

    @Override
    public Date getEventDate() {
        return date;
    }

    @Override
    public String getEventTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getHtmlContent() {
        return htmlContent;
    }
}
