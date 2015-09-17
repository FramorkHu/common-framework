package org.commonframwork.aop;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.commonframwork.aop.annotation.Aspect;
import org.commonframwork.core.ClassHelper;
import org.commonframwork.ioc.BeanHelper;
import org.commonframwork.util.ClassUtil;
import org.commonframwork.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huyan on 15/9/10.
 */
public class AopHelper {

    static {
        try {
            List<Class<?>> aspectList = ClassHelper.getClassListByAnnotation(Aspect.class);

            for (Class<?> aspectClass : aspectList){
                //获取aspect注解属性值
                Aspect aspect = aspectClass.getAnnotation(Aspect.class);
                String pkg = aspect.pkg();
                String clz = aspect.clz();

                List<Class<?>> targetClassList = new ArrayList<Class<?>>();
                if (StringUtils.isNotEmpty(pkg) && StringUtils.isNotEmpty(clz)){
                    targetClassList.add(Class.forName(pkg+"."+clz));
                } else if (StringUtils.isNotEmpty(pkg)){
                    //如果包名不为空，添加包下所以类
                    targetClassList.add(Class.forName(pkg));
                }

                if (!CollectionUtils.isEmpty(targetClassList)){
                    BaseAspect baseAspect = (BaseAspect)aspectClass.newInstance();
                    for (Class<?> targetClass : targetClassList){
                        //获取目标实例
                        Object targetInstance = BeanHelper.getBean(targetClass);
                        //创建代理
                        Object proxyInstance = baseAspect.getProxy(targetClass);

                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
