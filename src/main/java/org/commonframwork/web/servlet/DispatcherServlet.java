package org.commonframwork.web.servlet;

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
@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherServlet.class);



    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{

        Class aClass = IocHelper.class;
        try {
            Class.forName(aClass.getName(), true, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setCharacterEncoding(Constant.UTF_8);
        String currentRequestMethod = req.getMethod();
        String currentRequestURL = req.getPathInfo();

        // 获取并遍历 Action 映射
        Map<Requester, ActionInfo> actionInfoMap = ActionHelper.getActionMap();
        for (Map.Entry<Requester, ActionInfo> actionInfoEntry : actionInfoMap.entrySet()){
            Requester requester = actionInfoEntry.getKey();
            String requestUrl = requester.getRequestUrl();
            String requestMethod = requester.getRequestMethod();

            // 获取正则表达式匹配器（用于匹配请求 URL 并从中获取相应的请求参数）
            Matcher matcher = Pattern.compile(requestUrl).matcher(currentRequestURL);
            //判断请求的方法与请求的地址是否匹配
            if (requestMethod.equals(currentRequestMethod) && matcher.matches()){
                //初始化action对象
                ActionInfo actionInfo = actionInfoEntry.getValue();
                //初始化action方法参数列表
                List<Object> paramList = new ArrayList<Object>();
                for (int i=1; i<=matcher.groupCount(); i++){
                    String param = matcher.group(i);
                    if (StringUtil.isDigits(param)){
                        paramList.add(Integer.parseInt(param));
                    } else {
                        paramList.add(param);
                    }
                }

                //获取action相关属性
                Class<?> actionClass = actionInfo.getActionClz();
                Method method = actionInfo.getActionMethod();
                try {
                    //创建action实例
                    Object actionInstance = BeanHelper.getBean(actionClass);
                    method.setAccessible(true);
                    //调用action相关方法
                    Object actionResult = method.invoke(actionInstance,paramList.toArray());
                    if (actionResult instanceof Result){
                        Result result = (Result) actionResult;
                        WebUtil.writeJSONToResponse(resp, result);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    LOGGER.error("",e);
                }
            }
        }


    }

}
