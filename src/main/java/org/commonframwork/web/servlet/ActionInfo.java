package org.commonframwork.web.servlet;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by huyan on 2015/8/21.
 */
public class ActionInfo {

    private Class<?> actionClz;
    private Method actionMethod;
    private List<Object> paramList;

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

    public List<Object> getParamList() {
        return paramList;
    }

    public void setParamList(List<Object> paramList) {
        this.paramList = paramList;
    }
}
