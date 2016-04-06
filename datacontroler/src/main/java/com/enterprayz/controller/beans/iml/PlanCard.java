package com.enterprayz.controller.beans.iml;

import com.enterprayz.controller.beans.interfaces.IPlanCard;
import com.enterprayz.controller.beans.interfaces.IPlanStandCard;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by con on 19.04.16.
 */
@ParseClassName("KeyImage")
public class PlanCard extends ParseObject implements IPlanCard {
    private List<IPlanStandCard> planStandCards = new ArrayList<>();

    public PlanCard() {

    }

    public void setPlanStandCards(List<IPlanStandCard> planStandCards) {
        this.planStandCards = planStandCards;
    }

    @Override
    public String getPlanImageUrl() {
        return getParseFile("image").getUrl();
    }

    @Override
    public List<IPlanStandCard> getPlanStandCard() {
        return planStandCards;
    }
}
