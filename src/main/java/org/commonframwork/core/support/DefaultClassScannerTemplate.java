package org.commonframwork.core.support;

/**
 * Created by huyan on 2015/9/17.
 */
public class DefaultClassScannerTemplate extends ClassScannerTemplate {

    protected DefaultClassScannerTemplate(String packageName){
        super(packageName);
    }

    @Override
    boolean checkAddClass(Class<?> clz) {
        return true;
    }

}
