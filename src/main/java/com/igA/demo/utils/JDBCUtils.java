package com.igA.demo.utils;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.LogChannel;
import org.pentaho.di.core.logging.LogChannelFactory;

import java.sql.*;
import java.util.Properties;

public class JDBCUtils {



    private static String MYSQLURL = "jdbc:mysql://ip:port/dbname?userSSL=true&useUnicode=true&characterEncoding=UTF8&serverTimezone=Asia/Shanghai";
    //jdbc:mysql://localhost:3306/test111
    private static String POSTGRESQLURL = "jdbc:postgresql://ip:port/dbname?searchpath=schema";
    //jdbc:postgresql://localhost:5432/postgres?searchpath=test111
//    private static String ORACLEURL = "jdbc:oracle:thin:@ip:port/dbname";   //jdbc:oracle:thin:@ip:port/dbname 使用的是Service Name方式来指定数据库
    private static String ORACLEURL = "jdbc:oracle:thin:@ip:port:dbname"; //jdbc:oracle:thin:@ip:port:dbname 使用的是SID方式来指定数据库。
    //jdbc:oracle:thin:@10.0.108.21:1521:jyk

    private static String SQLSERVERURL = "jdbc:sqlserver://ip:port;databaseName=dbname";

    public static Connection getConnection(String databaseType, String ip, String port, String dbname, String schema, String user, String password) throws SQLException, KettleException {
        KettleEnvironment.init();
        LogChannelFactory logChannelFactory = new org.pentaho.di.core.logging.LogChannelFactory();
        LogChannel kettleLog = logChannelFactory.create("数据采集");

        if (databaseType.equals("mysql")) {
            MYSQLURL = MYSQLURL.replace("ip", ip).replace("port", port).replace("dbname", dbname);   //富平县数据库名ww_report
            kettleLog.logBasic("-----url----"+MYSQLURL);
            return DriverManager.getConnection(MYSQLURL, user, password);
        }
        if (databaseType.equals("postgresql")) {
            POSTGRESQLURL = POSTGRESQLURL.replace("ip", ip).replace("port", port).replace("dbname", dbname).replace("schema", schema);
            return DriverManager.getConnection(POSTGRESQLURL,
                    user, password);
        }
        if (databaseType.equals("oracle")) {
            ORACLEURL = ORACLEURL.replace("dbname", dbname).replace("ip", ip).replace("port", port);
            kettleLog.logBasic("-----url----"+ORACLEURL);
            return DriverManager.getConnection(ORACLEURL, user, password);
        }
        if (databaseType.equals("sqlserver")) {
            SQLSERVERURL = SQLSERVERURL.replace("dbname", dbname).replace("ip", ip).replace("port", port);
//            kettleLog.logBasic("-----url----"+SQLSERVERURL);
            Properties properties = new Properties();
            properties.put("user", user);
            properties.put("password", password);
            properties.put("sslProtocol", "TLSv1.2");
            return DriverManager.getConnection(SQLSERVERURL,properties);
        }
        return null;

    }


    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        try {
            assert statement != null;
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            assert resultSet != null;
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            assert connection != null;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
