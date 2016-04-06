package com.enterprayz.controller.beans.iml;

import android.graphics.Color;
import android.util.Log;

import com.enterprayz.controller.beans.interfaces.IPlanStandCard;
import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by con on 19.04.16.
 */
@ParseClassName("EventStand")
public class PlanStandCard extends ParseObject implements IPlanStandCard {

    public PlanStandCard() {

    }

    @Override
    public String getId() {
        return getObjectId();
    }

    @Override
    public String getImageUrl() {
        return getParseFile("image").getUrl();
    }

    @Override
    public int getX() {
        return getInt("planCoordinateX");
    }

    @Override
    public int getY() {
        return getInt("planCoordinateY");
    }

    @Override
    public String getContent() {
        return getString("text");
    }

    @Override
    public String getTitle() {
        return getString("title");
    }

    @Override
    public String getType() {
        return getString("type");
    }

    @Override
    public int getColor() {
        return Color.parseColor("#" + getString("typeColor"));
    }
}

