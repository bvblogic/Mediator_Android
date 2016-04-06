package com.enterprayz.controller.data_base;

import com.enterprayz.controller.beans.iml.LobbyNewsCard;
import com.enterprayz.controller.beans.iml.ScheduleCard;
import com.enterprayz.controller.beans.interfaces.ILobbyEventCard;
import com.enterprayz.controller.beans.interfaces.ILobbyNewsCard;
import com.enterprayz.controller.beans.interfaces.IPlanCard;
import com.enterprayz.controller.beans.interfaces.IPlanStandCard;
import com.enterprayz.controller.beans.interfaces.IScheduleCard;
import com.enterprayz.controller.beans.interfaces.IScheduleGroupCard;
import com.enterprayz.controller.data_base.models.RealmLobbyEvent;
import com.enterprayz.controller.data_base.models.RealmLobbyNews;
import com.enterprayz.controller.data_base.models.RealmPlan;
import com.enterprayz.controller.data_base.models.RealmScheduleGroup;
import com.enterprayz.controller.data_base.models.RealmScheduleItem;
import com.enterprayz.controller.data_base.models.RealmStandCard;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by hacker on 10.05.16.
 */
public class DbProvider {

    private Realm realm;

    public DbProvider(Realm realm) {
        this.realm = realm;
    }

    public static DbProvider getInstiance() {
        return new DbProvider(Realm.getDefaultInstance());
    }

    public void updateEvent(final ILobbyEventCard card) {
        final RealmLobbyEvent lobbyEvent = realm.where(RealmLobbyEvent.class).findFirst();
        if (lobbyEvent == null) {
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    bgRealm.createObject(RealmLobbyEvent.class);
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    updateEvent(card);
                }
            });
        } else {
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    final RealmLobbyEvent lobbyEvent = bgRealm.where(RealmLobbyEvent.class).findFirst();
                    lobbyEvent.setTitle(card.getTitle());
                    lobbyEvent.setPlaceTitle(card.getPlaceTitle());
                    lobbyEvent.setPlaceAddress(card.getPlaceAddress());
                    lobbyEvent.setEventDateStart(card.getEventDateStart());
                    lobbyEvent.setEventDateEnd(card.getEventDateEnd());
                    lobbyEvent.setLat(card.getGeoPoint().getLatitude());
                    lobbyEvent.setLon(card.getGeoPoint().getLongitude());
                    lobbyEvent.setTicketSale(card.isTicketSale());
                    lobbyEvent.setTicketUrl(card.getTicketUrl());
                    lobbyEvent.setDestination(card.getDestination());
                    lobbyEvent.setVkUrl(card.getVkUrl());
                    lobbyEvent.setYoutubeUrl(card.getYoutubeUrl());
                    lobbyEvent.setFbUrl(card.getFbUrl());
                    lobbyEvent.setInstagramUrl(card.getInstagramUrl());
                    lobbyEvent.setTwitterUrl(card.getTwitterUrl());
                    lobbyEvent.setTumblrUrl(card.getTumblrUrl());
                    lobbyEvent.setYoutubePresentVideoUrl(card.getYoutubePresentVideoUrl());
                }
            });
        }
    }

    public ILobbyEventCard getEvent() {
        return realm.where(RealmLobbyEvent.class).findFirst();
    }

    public void updateNews(List<ILobbyNewsCard> newsCards) {
        RealmResults<RealmLobbyNews> realmList = realm.where(RealmLobbyNews.class).findAll();
        realm.beginTransaction();
        realmList.deleteAllFromRealm();
        realm.commitTransaction();

        if (realmList.size() == 0) {
            for (final ILobbyNewsCard newsCard : newsCards) {
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm bgRealm) {
                        RealmLobbyNews news = bgRealm.createObject(RealmLobbyNews.class);
                        news.setDescription(newsCard.getDescription());
                        news.setEventDate(newsCard.getEventDate());
                        news.setEventTitle(newsCard.getEventTitle());
                        news.setHtmlContent(newsCard.getHtmlContent());
                    }
                });
            }
        }
    }

    public List<ILobbyNewsCard> getNews() {
        RealmResults<RealmLobbyNews> list = realm.where(RealmLobbyNews.class).findAll();
        List<ILobbyNewsCard> res = new ArrayList<>();
        for (RealmLobbyNews news : list) {
            res.add(new LobbyNewsCard(
                    news.getEventDate(),
                    news.getEventTitle(),
                    news.getDescription(),
                    news.getHtmlContent()
            ));
        }
        return res;
    }

    public void updateSchedules(List<IScheduleGroupCard> cards) {
        RealmResults<RealmScheduleGroup> realmList = realm.where(RealmScheduleGroup.class).findAll();
        realm.beginTransaction();
        realmList.deleteAllFromRealm();
        realm.commitTransaction();

        for (final IScheduleGroupCard<ScheduleCard> iScheduleGroupCard : cards) {
            realm.beginTransaction();
            RealmScheduleGroup scheduleGroup = realm.createObject(RealmScheduleGroup.class);
            scheduleGroup.setTitle(iScheduleGroupCard.getTitle());
            scheduleGroup.setId(iScheduleGroupCard.getId());
            RealmList<RealmScheduleItem> list = new RealmList<>();
            for (IScheduleCard card : iScheduleGroupCard.getCards()) {
                list.add(create(realm, card));
            }
            scheduleGroup.setCards(list);
            realm.commitTransaction();
        }
    }

    private RealmScheduleItem create(Realm realm, IScheduleCard card) {
        RealmScheduleItem realmScheduleItem = realm.createObject(RealmScheduleItem.class);
        realmScheduleItem.setTitle(card.getTitle());
        realmScheduleItem.setStartDate(card.getStartDate());
        realmScheduleItem.setEndDate(card.getEndDate());
        return realmScheduleItem;
    }

    public List<IScheduleGroupCard> getSchedulesGroups() {
        List<IScheduleGroupCard> res = new ArrayList<>();
        RealmResults<RealmScheduleGroup> realmScheduleItems = realm.where(RealmScheduleGroup.class).findAll();
        res.addAll(realmScheduleItems.subList(0, realmScheduleItems.size()));
        return res;
    }

    public List<IScheduleCard> getSchedulesCards(int groupId) {
        RealmScheduleGroup realmScheduleItems = realm.where(RealmScheduleGroup.class).equalTo("id", groupId).findFirst();
        List<IScheduleCard> res = new ArrayList<>();
        res.addAll(realmScheduleItems.getCards().subList(0, realmScheduleItems.getCards().size()));
        return res;
    }


    public void updateStands(IPlanCard<IPlanStandCard> planCard) {
        RealmPlan realmObj = realm.where(RealmPlan.class).findFirst();
        if (realmObj != null) {
            realm.beginTransaction();
            realmObj.deleteFromRealm();
            realm.commitTransaction();
        }

        realm.beginTransaction();
        RealmPlan plan = realm.createObject(RealmPlan.class);
        plan.setPlanImageUrl(planCard.getPlanImageUrl());
        RealmList<RealmStandCard> list = new RealmList<>();
        for (IPlanStandCard card : planCard.getPlanStandCard()) {
            list.add(create(realm, card));
        }
        plan.setPlanStandCard(list);
        realm.commitTransaction();
    }

    public RealmStandCard create(Realm realm, IPlanStandCard card) {
        RealmStandCard standCard = realm.createObject(RealmStandCard.class);
        standCard.setId(card.getId());
        standCard.setImageUrl(card.getImageUrl());
        standCard.setX(card.getX());
        standCard.setY(card.getY());
        standCard.setContent(card.getContent());
        standCard.setTitle(card.getTitle());
        standCard.setType(card.getType());
        standCard.setColor(card.getColor());
        return standCard;
    }

    public IPlanCard getPlan() {
        return realm.where(RealmPlan.class).findFirst();
    }

    public IPlanStandCard getStand(String id) {
        return realm.where(RealmStandCard.class).equalTo("id", id).findFirst();
    }
}