package org.commonframwork.test;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by huyan on 2015/9/14.
 */
public class AuthProxy implements MethodInterceptor {

    private String name;

    public AuthProxy(String name){

        this.name = name;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        if (!"zs".equals(name)){
            System.out.println("no permission");
            return null;
        }

        return methodProxy.invokeSuper(o, objects);

    }
}
