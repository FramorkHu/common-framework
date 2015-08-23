package org.commonframwork.web.servlet;

import org.apache.commons.lang.ClassUtils;
import org.commonframwork.util.ClassUtil;

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

}
