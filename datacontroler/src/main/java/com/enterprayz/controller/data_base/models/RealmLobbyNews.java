package com.enterprayz.controller.data_base.models;

import com.enterprayz.controller.beans.interfaces.ILobbyNewsCard;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by hacker on 10.05.16.
 */
public class RealmLobbyNews extends RealmObject implements ILobbyNewsCard {
    private Date eventDate;
    private String eventTitle;
    private String description;
    private String htmlContent;

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    @Override
    public Date getEventDate() {
        return eventDate;
    }

    @Override
    public String getEventTitle() {
        return eventTitle;
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
