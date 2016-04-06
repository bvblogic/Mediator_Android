package com.enterprayz.controller.modules.interfaces.base;

import com.enterprayz.controller.beans.interfaces.ICard;

/**
 * Created by con on 16.04.16.
 */
public abstract class CardCallback<T extends ICard> implements ICardCallback<T> {
    public boolean isRegister = false;

    @Override
    public void unregister() {
        isRegister = false;
    }

    @Override
    public void register() {
        isRegister = true;
    }
}
