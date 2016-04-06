package com.enterprayz.controller;

import android.content.Context;

import com.enterprayz.controller.beans.iml.LobbyEventCard;
import com.enterprayz.controller.beans.iml.PlanCard;
import com.enterprayz.controller.beans.iml.PlanStandCard;
import com.enterprayz.controller.beans.iml.ScheduleCard;
import com.enterprayz.controller.beans.iml.ScheduleGroupCard;
import com.enterprayz.controller.modules.iml.Core;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by urec on 30.11.15 urecki22@gmail.com.
 */
public class DataController {
    private final Core core;
    private Context context;


    private static DataController instance;

    private DataController(Context context) {
        prepareParse(context);
        this.context = context;
        this.core = new Core(context);
    }

    public static void ini(Context context) {
        instance = new DataController(context);
    }

    private void prepareParse(Context context) {
        ParseObject.registerSubclass(LobbyEventCard.class);
        ParseObject.registerSubclass(ScheduleCard.class);
        ParseObject.registerSubclass(ScheduleGroupCard.class);
        ParseObject.registerSubclass(PlanCard.class);
        ParseObject.registerSubclass(PlanStandCard.class);

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
        Parse.initialize(context, Constans.PARSE_APP_ID, Constans.PARSE_CLIENT_KEY);
    }

    public static Core getInstance() {
        if (instance == null) {
            throw new RuntimeException("Please before use make DataController.ini()" +
                    " in your App.class");
        }
        return instance.core;
    }

}
