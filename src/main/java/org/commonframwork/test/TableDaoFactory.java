package org.commonframwork.test;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;


/**
 * Created by huyan on 2015/9/14.
 */
public class TableDaoFactory {

    private static TableDao tableDao = new TableDao();

    public static TableDao newInstance(){
        return tableDao;
    }

    public static TableDao getAuthInstance(AuthProxy authProxy){
        Enhancer enhancer = new Enhancer();
        //设置代理
        enhancer.setSuperclass(TableDao.class);
        enhancer.setCallback(authProxy);

        //生成代理
        return (TableDao)enhancer.create();
    }

    public static TableDao getAuthFilterInstance(AuthProxy authProxy){
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(TableDao.class);
        enhancer.setCallbacks(new Callback[]{authProxy, NoOp.INSTANCE});
        enhancer.setCallbackFilter(new AuthProxyFilter());

        return (TableDao)enhancer.create();
    }
}
