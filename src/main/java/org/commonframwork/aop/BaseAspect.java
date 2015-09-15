package org.commonframwork.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by huyan on 15/9/10.
 */
public abstract class BaseAspect implements MethodInterceptor {

    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return advice(new Pointcut(method, methodProxy), proxy, objects);
    }

    protected abstract Object advice(Pointcut pointcut, Object proxy, Object[] args );

    protected class Pointcut{

        private Method methodTarget;
        private MethodProxy methodProxy;

        public Pointcut(Method methodTarget, MethodProxy methodProxy){
            this.methodTarget = methodTarget;
            this.methodProxy = methodProxy;
        }

        public Method getMethodTarget() {
            return methodTarget;
        }

        public MethodProxy getMethodProxy() {
            return methodProxy;
        }

        public Object invoke(Object proxy, Object[] objects){

            Object result = null;
            try {

                result = methodProxy.invokeSuper(proxy, objects);
            } catch (Throwable e){
                e.printStackTrace();
            }

            return result;
        }
    }
}
