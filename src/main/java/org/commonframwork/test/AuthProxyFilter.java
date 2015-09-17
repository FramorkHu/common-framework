package org.commonframwork.test;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by huyan on 2015/9/14.
 */
public class AuthProxyFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {

        if (!"select".equalsIgnoreCase(method.getName())){
            return 0;
        }
        return 1;
    }
}
