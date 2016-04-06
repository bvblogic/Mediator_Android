package com.enterprayz.controller;

import android.content.Context;

import com.enterprayz.controller.beans.interfaces.ILobbyEventCard;
import com.enterprayz.controller.beans.interfaces.ILobbyNewsCard;
import com.enterprayz.controller.beans.interfaces.IPlanCard;
import com.enterprayz.controller.beans.interfaces.IScheduleGroupCard;
import com.enterprayz.controller.modules.ActionBackEnd;
import com.enterprayz.controller.modules.iml.Cache;
import com.enterprayz.controller.modules.iml.NetWrapper;
import com.enterprayz.controller.modules.interfaces.INotifier;
import com.enterprayz.controller.modules.interfaces.base.CardCallback;
import com.enterprayz.controller.modules.interfaces.base.CardListCallback;
import com.enterprayz.controller.modules.interfaces.base.ICallBack;
import com.enterprayz.controller.modules.interfaces.base.ICardCallback;
import com.enterprayz.controller.modules.interfaces.base.ICardListCallback;

import java.util.List;

public class Action extends ActionBackEnd {
    public final Object tag;
    private Cache cache;
    private NetWrapper netWrapper;

    public Action(Context context, INotifier notifier, Cache cache) {
        super(notifier);
        this.tag = System.currentTimeMillis();
        this.netWrapper = new NetWrapper(context, cache);
        this.cache = cache;
    }


    public Cache getCache() {
        return cache;
    }

    @Override
    public void getLobbyNewsCards(boolean update, boolean cachePreload) {
        List<ILobbyNewsCard> lobbyEventCards = cache.getNewsCards();
        if (update || lobbyEventCards.size() == 0) {
            if (cachePreload && lobbyEventCards != null) {
                sendData(lobbyEventCards);
            }
            netWrapper.getNewsData(new CardListCallback<ILobbyNewsCard>() {
                @Override
                public void onSuccess(List<ILobbyNewsCard> data) {
                    cache.setEventCards(data);
                    sendData(data);
                }

                @Override
                public void onError(Throwable error) {
                    sendError(error);
                }
            });
        } else {
            sendData(lobbyEventCards);
        }
    }

    @Override
    public void getLobbyEventCard(boolean update, boolean cachePreload) {
        ILobbyEventCard card = cache.getEventCard();
        if (update || card == null) {
            if (cachePreload && card != null) {
                sendData(true, card);
            }
            netWrapper.getEventData(new CardCallback<ILobbyEventCard>() {
                @Override
                public void onSuccess(boolean preload, ILobbyEventCard data) {
                    cache.setEventCard(data);
                    sendData(false, data);
                }

                @Override
                public void onError(Throwable error) {
                    sendError(error);
                }
            });
        } else {
            sendData(false, card);
        }
    }

    @Override
    public void getScheduleGroups(boolean update, boolean cachePreload) {
        List<IScheduleGroupCard> cards = cache.getScheduleGroupCards();
        if (update || cards.size() == 0) {
            if (cachePreload && cards != null) {
                sendData(cards);
            }
            netWrapper.getScheduleData(new CardListCallback<IScheduleGroupCard>() {
                @Override
                public void onSuccess(List<IScheduleGroupCard> data) {
                    cache.setGroupCards(data);
                    sendData(data);
                }

                @Override
                public void onError(Throwable error) {
                    sendError(error);
                }
            });
        } else {
            sendData(cards);
        }
    }

    @Override
    public void getScheduleCards(int groupId) {
        sendData(cache.getScheduleCards(groupId));
    }

    @Override
    public void getPlanCard(boolean update, boolean cachePreload) {
        final IPlanCard card = cache.getPlanCard();
        if (update || card == null) {
            if (cachePreload && card != null) {
                sendData(true, card);
            }
            netWrapper.getPlanCard(new CardCallback<IPlanCard>() {
                @Override
                public void onSuccess(boolean preload, IPlanCard data) {
                    cache.setPlanCard(data);
                    sendData(false, data);
                }

                @Override
                public void onError(Throwable error) {
                    sendError(error);
                }
            });
        } else {
            sendData(false,card);
        }
    }

    @Override
    public void getStandCard(String id) {
        sendData(false,cache.getStandCard(id));
    }
}
