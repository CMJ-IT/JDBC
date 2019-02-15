package com.itcast.jdbc;

/*JDBC工具类*/

import java.sql.*;

/**
 * 1. 将固定字符串定义为常量
 * 2. 在静态代码块中注册驱动(只注册一次)
 * 3. 提供一个获取连接的方法static Connection getConneciton();
 * 4. 定义关闭资源的方法close(Connection conn, Statement stmt, ResultSet rs)
 * 5. 重载关闭方法close(Connection conn, Statement stmt)*/
public class JDBCUtils {
    private static final String Driver_Class = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql:///day18";
    private static final String User = "root";
    private static final String Psaaword = "abc12345";

    //1.静态代码块:用来注册驱动
    static{
        try {
            Class.forName(Driver_Class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //2.返回连接对象
    public static Connection getConnection(){
        try {
            //获取连接对象
            return DriverManager.getConnection(URL, User,Psaaword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //关闭资源
    public static void close(Statement stat, Connection conn){
        close(stat,conn,null);
    }
    //关闭资源
    public static void close(Statement stat, Connection conn, ResultSet rs){
        try {
            if (rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (stat != null){
                stat.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
