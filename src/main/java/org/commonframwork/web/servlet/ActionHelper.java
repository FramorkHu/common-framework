package org.commonframwork.web.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huyan on 2015/8/21.
 * 加载所有Action
 * 使用annotation做注解的action
 */
public class ActionHelper {

    private static final Map<Requester, ActionInfo> ACTION_INFO_MAP = new HashMap<Requester, ActionInfo>();

    static {
        //List<Class<?>> actionClassList =
    }

    public static Map<Requester, ActionInfo> getActionMap(){

        return ACTION_INFO_MAP;
    }

}
