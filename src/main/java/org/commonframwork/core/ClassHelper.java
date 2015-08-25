package org.commonframwork.core;

import org.apache.commons.lang.ClassUtils;
import org.commonframwork.util.ClassUtil;
import org.commonframwork.web.servlet.ConfigHelper;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by huyan on 2015/8/21.
 */
public class ClassHelper {

    private static final String BASE_PACKAGE = ConfigHelper.getString("base.package");

    public static List<Class<?>> getClassListByAnnotation(Class<? extends Annotation> annotationClass){
        return ClassUtil.getClassListByAnnotation(BASE_PACKAGE, annotationClass);
    }

    public static List<Class<?>> getClassListBySuper(Class<?> superClass) {
        return ClassUtil.getClassListBySuper(BASE_PACKAGE, superClass);
    }

    public static Class<?> loadClass(Class<?> clz){
        return ClassUtil.loadClass(clz.getName(),true);
    }

}
