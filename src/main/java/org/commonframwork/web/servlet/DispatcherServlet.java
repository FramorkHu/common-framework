package org.commonframwork.web.servlet;

import org.commonframwork.core.ClassHelper;
import org.commonframwork.core.Constant;
import org.commonframwork.ioc.BeanHelper;
import org.commonframwork.ioc.IocHelper;
import org.commonframwork.util.StringUtil;
import org.commonframwork.util.WebUtil;
import org.commonframwork.web.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huyan on 2015/8/21.
 */
@WebServlet(value = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherServlet.class);
    private static final Map<Requester, ActionInfo> ACTION_INFO_MAP = ActionHelper.getActionMap();


    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{

        initClass();
        req.setCharacterEncoding(Constant.UTF_8);
        String currentRequestMethod = req.getMethod();
        String currentRequestURL = req.getPathInfo();

        Object result = invokeRequest(currentRequestMethod, currentRequestURL);

        doResponse(result, resp);



    }

    public Object invokeRequest(String requestMethod, String requestUrl){
        ActionInfo actionInfo = getMatchActionInfo(requestMethod, requestUrl);
        return invokeActionMethod(actionInfo);

    }

    public void doResponse(Object result, HttpServletResponse response){
        if (result!=null &&
                result instanceof Result){
            Result realResult = (Result) result;
            WebUtil.writeJSONToResponse(response, realResult);
        }
    }
    public ActionInfo getMatchActionInfo(String invokeRequestMethod, String invokeRequestUrl){

        for (Map.Entry<Requester, ActionInfo> actionInfoEntry : ACTION_INFO_MAP.entrySet()){
            Requester requester = actionInfoEntry.getKey();
            String requestUrl = requester.getRequestUrl();
            String requestMethod = requester.getRequestMethod();

            // 获取正则表达式匹配器（用于匹配请求 URL 并从中获取相应的请求参数）
            Matcher matcher = Pattern.compile(requestUrl).matcher(invokeRequestUrl);
            //判断请求的方法与请求的地址是否匹配
            if (requestMethod.equals(invokeRequestMethod) && matcher.matches()){
                ActionInfo actionInfo = actionInfoEntry.getValue();

                List<Object> paramList = getMethodParameter(matcher);

                actionInfo.setParamList(paramList);

                return actionInfo;

            }
        }
        return null;
    }


    public void initClass(){
        ClassHelper.loadClass(IocHelper.class);
    }

    public List<Object> getMethodParameter(Matcher matcher){
        //提取匹配连接中的参数
        List<Object> paramList = new ArrayList<Object>();
        for (int i=1; i<=matcher.groupCount(); i++){
            String param = matcher.group(i);
            if (StringUtil.isDigits(param)){
                paramList.add(Integer.parseInt(param));
            } else {
                paramList.add(param);
            }
        }

        return paramList;
    }

    public Object invokeActionMethod(ActionInfo actionInfo){
        if (actionInfo ==  null){
            return null;
        }
        Object actionResult = null;
        Class<?> actionClass = actionInfo.getActionClz();
        Method method = actionInfo.getActionMethod();
        List<Object> paramList = actionInfo.getParamList();
        try {
            //创建action实例
            Object actionInstance = BeanHelper.getBean(actionClass);
            method.setAccessible(true);
            //调用action相关方法
            actionResult = method.invoke(actionInstance,paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("invokeRequestMethod is error",e);
        }
        return actionResult;
    }

}
