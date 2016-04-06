package com.enterprayz.controller.modules.interfaces;

import com.enterprayz.controller.beans.interfaces.ICard;
import com.enterprayz.controller.modules.interfaces.base.IBaseModule;

import java.util.List;

/**
 * Created by urec on 30.11.15 urecki22@gmail.com.
 */
public interface INotifier extends IBaseModule {
    void onResponseSuccess(List<? extends ICard> data);

    void onResponseSuccess(ICard data);

    void onResponseError(Throwable error);
}
