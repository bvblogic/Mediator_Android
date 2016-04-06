package com.enterprayz.controller.modules.interfaces.base;

import com.enterprayz.controller.beans.interfaces.ICard;

/**
 * Created by con on 16.04.16.
 */
public interface ICallBack<T extends ICard> {
    void onError(Throwable error);

    void unregister();

    void register();
}
