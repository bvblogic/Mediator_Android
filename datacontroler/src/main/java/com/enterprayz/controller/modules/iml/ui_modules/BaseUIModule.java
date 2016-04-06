package com.enterprayz.controller.modules.iml.ui_modules;

import com.enterprayz.controller.modules.interfaces.IAction;

/**
 * Created by urec on 30.11.15 urecki22@gmail.com.
 */
public class BaseUIModule<T extends IAction> {
    public final IAction action;

    public BaseUIModule(T action) {
        this.action = action;
    }
}
