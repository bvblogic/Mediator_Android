package com.enterprayz.controller.modules.interfaces;

import com.enterprayz.controller.modules.iml.Cache;
import com.enterprayz.controller.modules.iml.ui_modules.ILobbyFeedBack;
import com.enterprayz.controller.modules.iml.ui_modules.IPlanFeedBack;
import com.enterprayz.controller.modules.iml.ui_modules.IScheduleFeedBack;
import com.enterprayz.controller.modules.interfaces.base.ICallBack;

/**
 * Created 21.12.15 by Enterprayz
 * urecki22@gmail.com
 * enterprayz.blogspot.com
 */
public interface IAction extends ILobbyFeedBack, IScheduleFeedBack, IPlanFeedBack {
    void setCallBack(ICallBack callBack);

    Cache getCache();

}
