package org.commonframwork.core.impl;

import org.commonframwork.core.ClassScanner;
import org.commonframwork.core.support.AnnotationClassScannerTemplate;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by huyan on 2015/9/17.
 */
public class AnnotationClassScanner implements ClassScanner {

    @Override
    public List<Class<?>> getClassList(String packageName) {
        return null;
    }

    @Override
    public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return new AnnotationClassScannerTemplate(packageName, annotationClass).getClassList();
    }

    @Override
    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return null;
    }
}
