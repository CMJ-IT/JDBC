package com.itcast.jdbc;

import org.junit.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest3_JDBCUtils {
    /*使用JDBC工具类插入一条数据*/
    @Test
    public void add() throws Exception {
        //1.获取连接对象
        Connection conn = JDBCUtils.getConnection();
        //2.获取发送发送对象
        Statement stat = conn.createStatement();
        //3.准备SQL语句
        String sql = "insert into student values(null,'小张','男','1998-10-12');";
        //4.执行SQL语句
        stat.executeUpdate(sql);
        //5.关闭资源
        JDBCUtils.close(stat,conn);
    }

    /*使用JDBC工具类查询所有数据*/
    @Test
    public void findAll()throws Exception{
        //1.获取连接对象
        Connection conn = JDBCUtils.getConnection();
        //2.获取发送发送对象
        Statement stat = conn.createStatement();
        //3.准备SQL语句
        String sql = "select * from student";
        //4.执行SQL语句
        ResultSet rs = stat.executeQuery(sql);
        //5.使用循环读取数据
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            String birthday = rs.getString("birthday");
            System.out.println("学号:"+id + "姓名:"+name + "性别:"+gender + "生日:"+birthday);
        }
        //6.关闭资源
        JDBCUtils.close(stat,conn,rs);
    }
}
