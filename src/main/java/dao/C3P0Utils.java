package dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class C3P0Utils {
    private C3P0Utils() {
    }

    private static ComboPooledDataSource ds = null;
    private static ThreadLocal<Connection> tLocal = new ThreadLocal<>();

    static {
        ds = new ComboPooledDataSource();//读取默认配置文件

    }

    public static DataSource getDataSource() {
        return ds;
    }

    public static Connection getConnection() {
        Connection con = tLocal.get();
        if (con == null) {
            try {
                con = ds.getConnection();
                tLocal.set(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    public static void close(ResultSet resultSet, PreparedStatement pst, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        if (connection != null) {
//            try {
//                //connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
