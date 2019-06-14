package com.atguigu.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定
 *
 * @author qmwh
 * @create 2019-05-30 21:15
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 从连接池中获取连接对象
     * @return
     */
    public Connection getThreadConnection(){
        try {
            Connection connection = connectionThreadLocal.get();
            if(connection == null){
                connection = dataSource.getConnection();
                connectionThreadLocal.set(connection);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 移除绑定的连接对象
     */
    public void removeThreadConnection(){
        connectionThreadLocal.remove();
    }
}
