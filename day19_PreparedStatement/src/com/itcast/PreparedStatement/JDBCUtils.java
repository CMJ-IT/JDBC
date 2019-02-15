package com.itcast.PreparedStatement;

/*JDBC工具类*/

import java.sql.*;

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
    public static void close(PreparedStatement ps, Connection conn){
        close(ps,conn,null);
    }
    //关闭资源
    public static void close(PreparedStatement ps, Connection conn, ResultSet rs){
        try {
            if (rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null){
                ps.close();
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
