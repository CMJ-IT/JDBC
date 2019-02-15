package com.itcast.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
/*JDBC实现登录案例*/
/**
 * 1. 使用数据库保存用户的账号和密码
 * 2. 让用户输入账号和密码
 * 3. 使用SQL根据用户的账号和密码去数据库查询数据
 * 4. 如果查询到数据，说明登录成功
 * 5. 如果查询不到数据，说明登录失败
*/
public class JDBCTest4_Login {
    public static void main(String[] args) throws Exception{
        //1.创建录入对象
        Scanner scanner = new Scanner(System.in);
        //2.// 接收用户输入的用户名和密码
        System.out.println("请输入用户名:");
        String username = scanner.nextLine();
        System.out.println("请输入用密码:");
        String password = scanner.nextLine();

        //3.连接数据库查询
        Connection conn = JDBCUtils.getConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from user where username = '"+username+"' and password = '"+password+"';";
        ResultSet rs = stat.executeQuery(sql);

        //4.判断
        if (rs.next()){
            System.out.println("登录成功!");
        }else {
            System.out.println("登录失败!");
        }

        //5.关闭资源
        JDBCUtils.close(stat,conn,rs);
    }
}
