package org.commonframwork.core.support;

import java.lang.annotation.Annotation;

/**
 * Created by huyan on 2015/9/17.
 */
public class AnnotationClassScannerTemplate extends ClassScannerTemplate {

    private Class<? extends Annotation> annotationClass;

    protected AnnotationClassScannerTemplate(String packageName, Class<? extends Annotation> annotationClass){

        super(packageName);
        this.annotationClass = annotationClass;
    }

    @Override
    boolean checkAddClass(Class<?> clz) {
        return clz.isAnnotationPresent(annotationClass);
    }
}
