package org.commonframwork.ioc;

import org.apache.commons.collections.CollectionUtils;
import org.commonframwork.core.ClassHelper;
import org.commonframwork.ioc.annotation.Bean;
import org.commonframwork.ioc.annotation.Inject;
import org.commonframwork.util.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huyan on 2015/8/24.
 */
public class BeanHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanHelper.class);

    private static final Map<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();

    static {
        try {
            List<Class<?>> beanClassList = ClassHelper.getClassListByAnnotation(Bean.class);
            for (Class<?> beanClass : beanClassList){
                Object objectInstance = beanClass.newInstance();
                beanMap.put(beanClass, objectInstance);
            }


        } catch (Exception e){
            LOGGER.error("初始化bean信息出错");
        }

    }

    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }
}
