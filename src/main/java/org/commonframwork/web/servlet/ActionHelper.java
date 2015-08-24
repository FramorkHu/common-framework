package org.commonframwork.web.servlet;

import org.apache.commons.lang.ArrayUtils;
import org.commonframwork.core.ClassHelper;
import org.commonframwork.web.annotation.Action;
import org.commonframwork.web.annotation.Request;
import org.commonframwork.util.StringUtil;

import java.lang.reflect.Method;
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
        // 获取并遍历所有使用 Action 注解
        List<Class<?>> actionClassList = ClassHelper.getClassListByAnnotation(Action.class);

        for (Class<?> actionClass : actionClassList){
            Method[] methods = actionClass.getDeclaredMethods();
            if (!ArrayUtils.isEmpty(methods)){
                for (Method actionMethod : methods){
                    //判断当前action有没有Request注解
                    if (actionMethod.isAnnotationPresent(Request.class)){
                        String url = actionMethod.getAnnotation(Request.class).value();
                        String[] urlArray = url.split(":");
                        if (!ArrayUtils.isEmpty(urlArray)){
                            String requestMethod = urlArray[0];
                            String requestUrl = urlArray[1];
                            requestUrl = StringUtil.replaceAll(requestUrl,"\\{\\w+\\}","(\\\\w+)");
                            ACTION_INFO_MAP.put(new Requester(requestMethod, requestUrl),new ActionInfo(actionClass,actionMethod));
                        }
                    }
                    // 获取 @Requet 注解中的 URL 字符串

                }

            }
        }
    }

    private static void handleActionMethod(Method actionMethod){

    }

    public static Map<Requester, ActionInfo> getActionMap(){

        return ACTION_INFO_MAP;
    }

}
