package com.enterprayz.controller.modules.iml;

import android.content.Context;
import android.os.AsyncTask;

import com.enterprayz.controller.Constans;
import com.enterprayz.controller.ModelConverter;
import com.enterprayz.controller.beans.iml.LobbyEventCard;
import com.enterprayz.controller.beans.iml.PlanCard;
import com.enterprayz.controller.beans.iml.PlanStandCard;
import com.enterprayz.controller.beans.iml.ScheduleCard;
import com.enterprayz.controller.beans.iml.ScheduleGroupCard;
import com.enterprayz.controller.beans.interfaces.ILobbyEventCard;
import com.enterprayz.controller.beans.interfaces.ILobbyNewsCard;
import com.enterprayz.controller.beans.interfaces.IPlanCard;
import com.enterprayz.controller.beans.interfaces.IPlanStandCard;
import com.enterprayz.controller.beans.interfaces.IScheduleGroupCard;
import com.enterprayz.controller.modules.interfaces.base.ICardCallback;
import com.enterprayz.controller.modules.interfaces.base.ICardListCallback;
import com.enterptayz.rss.RssItem;
import com.enterptayz.rss.RssReader;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by con on 09.04.16.
 */
public class NetWrapper {
    private final Cache cache;
    private final Context context;

    public NetWrapper(Context context, Cache cache) {
        this.context = context;
        this.cache = cache;
    }

    public void getNewsData(final ICardListCallback<ILobbyNewsCard> callback) {
        new RssReader(Constans.rssSourceUrl, new RssReader.Handler() {
            @Override
            public void onGetItems(List<RssItem> list) {
                callback.onSuccess(ModelConverter.getLobbyNewsCards(list));
            }
        }).getItems();
    }

    public void getEventData(final ICardCallback<ILobbyEventCard> callback) {
        ParseQuery<LobbyEventCard> query = ParseQuery.getQuery(LobbyEventCard.class);
        query.getInBackground(Constans.PARSE_EVENT_OBJECT_ID, new GetCallback<LobbyEventCard>() {
            @Override
            public void done(LobbyEventCard object, ParseException e) {
                if (e == null) {
                    callback.onSuccess(false, object);
                } else {
                    callback.onError(e);
                }
            }
        });
    }


    public void getScheduleData(final ICardListCallback<IScheduleGroupCard> callback) {
        new AsyncScheduleData(callback).execute();
    }

    public void getPlanCard(final ICardCallback<IPlanCard> callback) {
        ParseQuery<PlanCard> query = ParseQuery.getQuery(PlanCard.class);
        query.getInBackground(Constans.PARSE_KEY_IMAGE_OBJECT_ID, new GetCallback<PlanCard>() {
            @Override
            public void done(final PlanCard planCard, ParseException e) {
                if (e == null) {
                    ParseQuery<PlanStandCard> query = ParseQuery.getQuery(PlanStandCard.class);
                    query.addAscendingOrder("order")
                            .whereNotEqualTo("planCoordinateX", null)
                            .whereNotEqualTo("planCoordinateY", null)
                            .whereNotEqualTo("image", null)
                            .whereNotEqualTo("order", null)
                            .whereNotEqualTo("typeColor", null)
                            .findInBackground(new FindCallback<PlanStandCard>() {
                                @Override
                                public void done(List<PlanStandCard> objects, ParseException e) {
                                    if (e == null) {
                                        planCard.setPlanStandCards(new ArrayList<IPlanStandCard>(objects));
                                        callback.onSuccess(false, planCard);
                                    } else {
                                        callback.onError(e);
                                    }
                                }
                            });
                } else {
                    callback.onError(e);
                }
            }
        });
    }

    private class AsyncScheduleData extends AsyncTask<Void, Void, List<ScheduleGroupCard>> {

        public ICardListCallback callback;

        public AsyncScheduleData(ICardListCallback callback) {
            this.callback = callback;
        }

        @Override
        protected void onPostExecute(List<ScheduleGroupCard> iScheduleGroupCards) {
            super.onPostExecute(iScheduleGroupCards);
            if (iScheduleGroupCards != null) {
                callback.onSuccess(iScheduleGroupCards);
            }
        }

        @Override
        protected List<ScheduleGroupCard> doInBackground(Void... params) {
            ParseQuery<ScheduleGroupCard> query = ParseQuery.getQuery(ScheduleGroupCard.class);
            List<ScheduleGroupCard> objects;
            try {
                objects = query.addAscendingOrder("order").find();
                for (ScheduleGroupCard card : objects) {
                    ParseQuery<ScheduleCard> parseQuery = ParseQuery.getQuery(ScheduleCard.class);
                    card.setCards(parseQuery.addAscendingOrder("startAt").whereEqualTo("place", card.getId()).find());
                }
                return objects;
            } catch (ParseException e) {
                callback.onError(e);
            }
            return null;
        }
    }
}
