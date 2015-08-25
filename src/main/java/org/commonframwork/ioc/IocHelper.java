package org.commonframwork.ioc;

import org.apache.commons.collections.CollectionUtils;
import org.commonframwork.core.ClassHelper;
import org.commonframwork.ioc.annotation.Bean;
import org.commonframwork.ioc.annotation.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Created by huyan on 2015/8/24.
 */
public class IocHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(IocHelper.class);
    static {

        try {
            Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();

            for(Map.Entry<Class<?>, Object> entry : beanMap.entrySet()){

                //获取bean的类型和实例
                Class<?> beanClass = entry.getKey();
                Object beanInstance = entry.getValue();

                Field[] fields = beanClass.getDeclaredFields();

                for (Field field: fields){
                    if (field.isAnnotationPresent(Inject.class)){
                        Class<?> interfaceClass = field.getType();
                        List<Class<?>> implementClassList = ClassHelper.getClassListBySuper(interfaceClass);

                        if (!CollectionUtils.isEmpty(implementClassList)){
                            //如果有多个实现类，则选取第一个
                            Class<?> implementClass = implementClassList.get(0);

                            Object implementInstance = beanMap.get(implementClass);
                            field.setAccessible(true);
                            field.set(beanInstance, implementInstance);
                        }

                    }
                }
            }

        } catch (Exception e){
            LOGGER.error("parse Ioc is error",e);
        }
    }
}
