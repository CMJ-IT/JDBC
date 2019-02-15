package com.itcast.jdbc;


import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*JDBC实现对单表数据增、删、改、查*/
public class JDBCTest1 {
     /*
        1.使JDBC在MySQL的数据库中创建一张学生表:
            1) id 是主键，整数类型，自增长
            2) name 是varchar(20)，非空
            3) 性别 是char类型
            4) 生日 是date类型
        */
    @Test
     public void testCreateTable() throws Exception {
        //1.注解驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql:///day18", "root", "abc12345");
        //3.获取发送对象
        Statement stat = conn.createStatement();
        //4.准备SQL语句
        String sql = "create table student(id int primary key auto_increment" +
                ",name varchar(20) not null" +
                ",gender char(1)" +
                ",birthday date);";
        //5.执行SQL语句
        int row = stat.executeUpdate(sql);
        /**System.out.println("row:"+row);*/
        //6.关闭资源
        stat.close();
        conn.close();
    }

    /*2.插入数据*/
    @Test
    public void add()throws Exception{
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql:///day18", "root", "abc12345");
        //3.获取发送对象
        Statement stat = conn.createStatement();
        //4.准备SQL语句
        String sql = "insert into student values(null,'小张','男','1998-10-12'),(null,'李飞','女','2009-07-10');";
        //5.执行插入SQL语句
        stat.executeUpdate(sql);
        //6.关闭资源
        stat.close();
        conn.close();

    }

    /*3.修改*/
    @Test
    public void update()throws Exception{
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql:///day18", "root", "abc12345");
        //3.获取发送对象
        Statement stat = conn.createStatement();
        //4.准备SQL语句
        String sql = "update student set name = 'lucy',gender='女' where id = 2;";
        //5.执行修改SQL语句
        stat.executeUpdate(sql);
        //6.关闭资源
        stat.close();
        conn.close();
    }

    /*4.删除*/
    @Test
    public void delete() throws Exception{
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql:///day18", "root", "abc12345");
        //3.获取发送对象
        Statement stat = conn.createStatement();
        //4.准备SQL语句
        String sql = "delete from student where id = 6;";
        //5.执行删除SQL语句
        stat.executeUpdate(sql);
        //6.关闭资源
        stat.close();
        conn.close();
    }
}
