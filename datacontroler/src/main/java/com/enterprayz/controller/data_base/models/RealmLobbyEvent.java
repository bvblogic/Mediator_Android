package com.enterprayz.controller.data_base.models;

import com.enterprayz.controller.beans.interfaces.ILobbyEventCard;
import com.parse.ParseGeoPoint;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by hacker on 10.05.16.
 */
public class RealmLobbyEvent extends RealmObject implements ILobbyEventCard {
    private String title;
    private String placeTitle;
    private String placeAddress;
    private Date eventDateStart;
    private Date eventDateEnd;
    private double lat;
    private double lon;
    private boolean isTicketSale;
    private String ticketUrl;
    private String destination;
    private String vkUrl;
    private String youtubeUrl;
    private String fbUrl;
    private String instagramUrl;
    private String twitterUrl;
    private String tumblrUrl;
    private String youtubePresentVideoUrl;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public void setEventDateStart(Date eventDateStart) {
        this.eventDateStart = eventDateStart;
    }

    public void setEventDateEnd(Date eventDateEnd) {
        this.eventDateEnd = eventDateEnd;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setTicketSale(boolean ticketSale) {
        isTicketSale = ticketSale;
    }

    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setVkUrl(String vkUrl) {
        this.vkUrl = vkUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public void setFbUrl(String fbUrl) {
        this.fbUrl = fbUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public void setTumblrUrl(String tumblrUrl) {
        this.tumblrUrl = tumblrUrl;
    }

    public void setYoutubePresentVideoUrl(String youtubePresentVideoUrl) {
        this.youtubePresentVideoUrl = youtubePresentVideoUrl;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getPlaceTitle() {
        return placeTitle;
    }

    @Override
    public String getPlaceAddress() {
        return placeAddress;
    }

    @Override
    public Date getEventDateStart() {
        return eventDateStart;
    }

    @Override
    public Date getEventDateEnd() {
        return eventDateEnd;
    }

    @Override
    public ParseGeoPoint getGeoPoint() {
        return new ParseGeoPoint(lat, lon);
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public boolean isTicketSale() {
        return isTicketSale;
    }

    @Override
    public String getTicketUrl() {
        return ticketUrl;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public String getVkUrl() {
        return vkUrl;
    }

    @Override
    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    @Override
    public String getFbUrl() {
        return fbUrl;
    }

    @Override
    public String getInstagramUrl() {
        return instagramUrl;
    }

    @Override
    public String getTwitterUrl() {
        return twitterUrl;
    }

    @Override
    public String getTumblrUrl() {
        return tumblrUrl;
    }

    @Override
    public String getYoutubePresentVideoUrl() {
        return youtubePresentVideoUrl;
    }
}
