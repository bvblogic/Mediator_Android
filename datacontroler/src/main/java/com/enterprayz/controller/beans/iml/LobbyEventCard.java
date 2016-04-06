package com.enterprayz.controller.beans.iml;

import com.enterprayz.controller.beans.interfaces.ILobbyEventCard;
import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by con on 17.04.16.
 */
@ParseClassName("Event")
public class LobbyEventCard extends ParseObject implements ILobbyEventCard {
    public LobbyEventCard() {

    }

    @Override
    public String getTitle() {
        return getString("title");
    }

    @Override
    public String getPlaceTitle() {
        return getString("placeName");
    }

    @Override
    public String getPlaceAddress() {
        return getString("address");
    }

    @Override
    public Date getEventDateStart() {
        return getDate("startAt");
    }

    @Override
    public Date getEventDateEnd() {
        return getDate("finishAt");
    }

    @Override
    public ParseGeoPoint getGeoPoint() {
        return getParseGeoPoint("placedAt");
    }

    @Override
    public boolean isTicketSale() {
        return getBoolean("ticketSale");
    }

    @Override
    public String getTicketUrl() {
        return getString("ticketUrl");
    }

    @Override
    public String getDestination() {
        return getString("text");
    }

    @Override
    public String getVkUrl() {
        return getString("vk");
    }

    @Override
    public String getYoutubeUrl() {
        return getString("youtube");
    }

    @Override
    public String getFbUrl() {
        return getString("facebook");
    }

    @Override
    public String getInstagramUrl() {
        return getString("instagram");
    }

    @Override
    public String getTwitterUrl() {
        return getString("twitter");
    }

    @Override
    public String getTumblrUrl() {
        return getString("tumblr");
    }

    @Override
    public String getYoutubePresentVideoUrl() {
        return getString("video");
    }
}
