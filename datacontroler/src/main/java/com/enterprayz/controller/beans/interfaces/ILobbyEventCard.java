package com.enterprayz.controller.beans.interfaces;

import com.parse.ParseGeoPoint;

import java.util.Date;

/**
 * Created by con on 17.04.16.
 */
public interface ILobbyEventCard extends ICard {


    String getTitle();

    String getPlaceTitle();

    String getPlaceAddress();

    Date getEventDateStart();

    Date getEventDateEnd();

    ParseGeoPoint getGeoPoint();

    boolean isTicketSale();

    String getTicketUrl();

    String getDestination();

    String getVkUrl();

    String getYoutubeUrl();

    String getFbUrl();

    String getInstagramUrl();

    String getTwitterUrl();

    String getTumblrUrl();

    String getYoutubePresentVideoUrl();

}
