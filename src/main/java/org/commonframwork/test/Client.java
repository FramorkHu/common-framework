package org.commonframwork.test;

/**
 * Created by huyan on 2015/9/14.
 */
public class Client {

    public static void main(String args[]){
        TableDao tableDao = TableDaoFactory.newInstance();
        TableDao zsTableDao = TableDaoFactory.getAuthInstance(new AuthProxy("zs"));
        TableDao lsTableDao = TableDaoFactory.getAuthInstance(new AuthProxy("ls"));

        TableDao filterDap = TableDaoFactory.getAuthFilterInstance(new AuthProxy("ls"));
        //doMethod(tableDao);
        /*doMethod(zsTableDao);
        doMethod(lsTableDao);*/
        doMethod(filterDap);
    }

    public static void doMethod(TableDao tableDao){

        tableDao.del();
        tableDao.create();
        tableDao.select();
        tableDao.update();

    }
}
