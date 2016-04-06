package com.enterprayz.controller.modules.interfaces.base;

import com.enterprayz.controller.beans.interfaces.ICard;

/**
 * Created by con on 16.04.16.
 */
public abstract class ActionResponse<T extends ICard> {
    public void withCallback(CardCallback<T> callback) {
        onPrepare(callback);
    }

    public void withCallback(CardListCallback<T> callback) {
        onPrepare(callback);
    }

    public void onEventBus() {
        onPrepare(null);
    }

    protected abstract void onPrepare(ICallBack callBack);
}
