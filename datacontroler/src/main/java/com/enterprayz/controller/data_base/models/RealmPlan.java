package com.enterprayz.controller.data_base.models;

import com.enterprayz.controller.beans.interfaces.IPlanCard;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by hacker on 10.05.16.
 */
public class RealmPlan extends RealmObject implements IPlanCard {
    private String planImageUrl;
    private RealmList<RealmStandCard> planStandCard;

    public void setPlanImageUrl(String planImageUrl) {
        this.planImageUrl = planImageUrl;
    }

    public void setPlanStandCard(RealmList<RealmStandCard> planStandCard) {
        this.planStandCard = planStandCard;
    }

    @Override
    public String getPlanImageUrl() {
        return planImageUrl;
    }

    @Override
    public List<RealmStandCard> getPlanStandCard() {
        return planStandCard;
    }
}
