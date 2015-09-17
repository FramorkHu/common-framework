package org.commonframwork.jdbc;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by huyan on 2015/8/25.
 */
public class DatabaseHandler {

    private static ThreadLocal<Connection> connectionContainer = new ThreadLocal<Connection>();


    public static void beginTransaction(){

        Connection connection = connectionContainer.get();
        if (connection != null){
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void commitTransaction(){

        Connection connection = connectionContainer.get();
        if (connection != null){
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connectionContainer.remove();
            }

        }
    }

    public static void rollbackTransaction(){

        Connection connection = connectionContainer.get();

        if (connection != null){
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connectionContainer.remove();
            }

        }
    }

}
