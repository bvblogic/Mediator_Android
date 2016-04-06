package com.enterprayz.controller.modules.interfaces.base;

import com.enterprayz.controller.beans.interfaces.ICard;

import java.util.List;

/**
 * Created by con on 07.04.16.
 */
public interface ICardListCallback<T extends ICard> extends ICallBack<T> {
    void onSuccess(List<T> data);
}
