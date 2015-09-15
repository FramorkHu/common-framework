package org.commonframwork.aop.annotation;

import java.lang.annotation.*;

/**
 * Created by huyan on 15/9/10.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    String pkg() default "";

    String clz() default "";

    Class<? extends Annotation> annotation() default Aspect.class;


}
