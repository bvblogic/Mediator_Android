package com.enterprayz.controller.modules.iml;

import com.enterprayz.controller.beans.interfaces.ICard;
import com.enterprayz.controller.modules.interfaces.INotifier;

import java.util.List;

/**
 * Created by urec on 30.11.15 urecki22@gmail.com.
 */
public class Notifier implements INotifier {
    @Override
    public void onResponseSuccess(ICard data) {

    }

    @Override
    public void onResponseError(Throwable error) {

    }

    @Override
    public void onResponseSuccess(List<? extends ICard> data) {

    }
}
