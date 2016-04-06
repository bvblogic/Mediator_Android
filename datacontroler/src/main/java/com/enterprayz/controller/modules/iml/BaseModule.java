package com.enterprayz.controller.modules.iml;

import com.enterprayz.controller.modules.interfaces.base.IBaseFeedBack;

/**
 * Created by urec on 30.11.15 urecki22@gmail.com.
 */
public class BaseModule<T extends IBaseFeedBack> {
    public final T feedBack;

    public BaseModule(T feedBack) {
        this.feedBack = feedBack;
    }
}
