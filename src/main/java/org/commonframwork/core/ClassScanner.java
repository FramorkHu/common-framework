package org.commonframwork.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.List;

/**
 * Created by huyan on 2015/9/17.
 */
public interface ClassScanner {

    public List<Class<?>> getClassList(String packageName);

    public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass);

    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass);

}
