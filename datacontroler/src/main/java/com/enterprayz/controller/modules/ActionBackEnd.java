package com.enterprayz.controller.modules;

import com.enterprayz.controller.beans.interfaces.ICard;
import com.enterprayz.controller.modules.interfaces.IAction;
import com.enterprayz.controller.modules.interfaces.INotifier;
import com.enterprayz.controller.modules.interfaces.base.CardCallback;
import com.enterprayz.controller.modules.interfaces.base.CardListCallback;
import com.enterprayz.controller.modules.interfaces.base.ICallBack;
import com.enterprayz.controller.modules.interfaces.base.ICardCallback;
import com.enterprayz.controller.modules.interfaces.base.ICardListCallback;

import java.util.List;

/**
 * Created by con on 16.04.16.
 */
public abstract class ActionBackEnd implements IAction {
    INotifier notifier;

    private CardCallback viewCardCallback;
    private CardListCallback viewCardListCallback;

    public ActionBackEnd(INotifier notifier) {
        this.notifier = notifier;
    }

    public void setCallBack(ICallBack callBack) {
        if (callBack != null) {
            if (callBack instanceof ICardCallback) {
                this.viewCardCallback = (CardCallback) callBack;
            } else if (callBack instanceof ICardListCallback) {
                this.viewCardListCallback = (CardListCallback) callBack;
            }
        }
    }


    protected void sendData(boolean preload, ICard card) {
        if (viewCardCallback != null && viewCardCallback.isRegister) {
            viewCardCallback.onSuccess(preload, card);
        } else {
            notifier.onResponseSuccess(card);
        }
    }

    protected void sendData(List<? extends ICard> cards) {
        if (viewCardListCallback != null && viewCardListCallback.isRegister) {
            viewCardListCallback.onSuccess(cards);
        } else {
            notifier.onResponseSuccess(cards);
        }
    }

    protected void sendError(Throwable t) {
        if (viewCardCallback != null && viewCardCallback.isRegister) {
            viewCardCallback.onError(t);
        } else if (viewCardListCallback != null && viewCardListCallback.isRegister) {
            viewCardListCallback.onError(t);
        } else {
            notifier.onResponseError(t);
        }
    }
}
