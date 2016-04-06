package com.enterprayz.controller.data_base.models;

import com.enterprayz.controller.beans.interfaces.IPlanStandCard;

import io.realm.RealmObject;

/**
 * Created by hacker on 10.05.16.
 */
public class RealmStandCard extends RealmObject implements IPlanStandCard {
    private String id;
    private String imageUrl;
    private int x;
    private int y;
    private String content;
    private String title;
    private String type;
    private int color;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
