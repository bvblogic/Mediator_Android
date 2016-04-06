package com.enterprayz.controller.modules.interfaces.base;

import com.enterprayz.controller.beans.interfaces.ICard;

import java.util.List;

/**
 * Created by con on 16.04.16.
 */
public interface ICardCallback<T extends ICard> extends ICallBack {
    void onSuccess(boolean preload, T data);

}
