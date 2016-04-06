package com.enterprayz.controller.modules.iml;

import android.content.Context;

import com.enterprayz.controller.Action;
import com.enterprayz.controller.modules.iml.ui_modules.IPlan;
import com.enterprayz.controller.modules.iml.ui_modules.ISchedule;
import com.enterprayz.controller.modules.iml.ui_modules.Lobby;
import com.enterprayz.controller.modules.iml.ui_modules.Plan;
import com.enterprayz.controller.modules.iml.ui_modules.Schedule;
import com.enterprayz.controller.modules.interfaces.INotifier;
import com.enterprayz.controller.modules.interfaces.base.IBaseFeedBack;
import com.enterprayz.controller.modules.iml.ui_modules.ILobby;

import java.util.Hashtable;

/**
 * Created by urec on 30.11.15 urecki22@gmail.com.
 */
public class Core implements IBaseFeedBack {
    private Context context;
    private Cache cache;
    private INotifier notifier;

    private final Hashtable<Object, Action> actions;

    public Core(Context context) {
        this.context = context;
        actions = new Hashtable<>();
        notifier = new Notifier();
        cache = new Cache();
    }

    public ILobby feed() {
        Action action = getNewAction();
        return new Lobby(action);
    }

    public ISchedule schedule() {
        Action action = getNewAction();
        return new Schedule(action);
    }

    public IPlan plan() {
        Action action = getNewAction();
        return new Plan(action);
    }

    private Action getNewAction() {
        Action action = new Action(context, notifier, cache);
        actions.put(action.tag, action);
        return action;
    }
}
