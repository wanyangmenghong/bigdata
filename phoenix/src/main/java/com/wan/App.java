package com.wan;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Throwable {
        //System.setProperty("hadoop.home.dir", "F:\\hadoop\\hadoop-2.6.3");
        try {
            // 下面的驱动为Phoenix老版本使用2.11使用，对应hbase0.94+
            // Class.forName("com.salesforce.phoenix.jdbc.PhoenixDriver");
            // phoenix4.3用下面的驱动对应hbase0.98+
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 这里配置zookeeper的地址，可单个，也可多个。可以是域名或者ip
        String url = "jdbc:phoenix:keduox1,keduox2,keduox3:2181";
        // String url =
        // "jdbc:phoenix:41.byzoro.com,42.byzoro.com,43.byzoro.com:2181";
        Connection conn = DriverManager.getConnection(url);
        Statement statement = conn.createStatement();
        String sql = "select * from \"javaT02\"";
        long time = System.currentTimeMillis();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String row = rs.getString("PK");
            System.out.println("row -- " + row);
            String name = rs.getString("val");
            System.out.println("name -- " + name);
        }
        long timeUsed = System.currentTimeMillis() - time;
        System.out.println("time " + timeUsed + "mm");
        // 关闭连接
        rs.close();
        statement.close();
        conn.close();
    }
}
