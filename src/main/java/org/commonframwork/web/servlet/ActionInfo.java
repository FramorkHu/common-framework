package org.commonframwork.web.servlet;

import java.lang.reflect.Method;

/**
 * Created by huyan on 2015/8/21.
 */
public class ActionInfo {

    private Class<?> actionClz;
    private Method actionMethod;

    public ActionInfo(Class<?> actionClz, Method actionMethod){
        this.actionClz = actionClz;
        this.actionMethod = actionMethod;
    }

    public Class<?> getActionClz() {
        return actionClz;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
