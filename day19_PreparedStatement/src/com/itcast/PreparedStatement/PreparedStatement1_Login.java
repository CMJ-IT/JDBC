package com.itcast.PreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
/**改造登录案例示例代码(没有SQL注入)
 * 1. 使数据库保存户的账号和密码
 * 2. 让户输账号和密码
 * 3. 编写SQL语句，账号和密码部分使？占位
 * 4. 使PreparedSatement给?设置参数
 * 5. 使PreparedSatement执预编译的SQL语句
 * 6. 如果查询到数据，说明登录成功
 * 7. 如果查询不到数据，说明登录失败*/
public class PreparedStatement1_Login {
    public static void main(String[] args) throws Exception{
        //1.创建录入对象
        Scanner scanner = new Scanner(System.in);
        //2.接收用户输入的用户名和密码
        System.out.println("请输入用户名:");
        String username = scanner.nextLine();
        System.out.println("请输入用密码:");
        String password = scanner.nextLine();

        //3.连接数据库查询
        /**获取连接对象*/
        Connection conn = JDBCUtils.getConnection();
        /**准备SQL语句：将户名和密码拼接成SQL语句*/
        String sql = "select * from user where username = ? and password = ? ;";
        /**获得预编译对象：PreparedStatement*/
        PreparedStatement ps = conn.prepareStatement(sql);

        /**给占位符?赋值具体的内容*/
        ps.setString(1,username);
        ps.setString(2,password);

        /**执行SQL语句*/
        ResultSet rs = ps.executeQuery();
        //4.判断
        if (rs.next()){
            System.out.println("欢迎 "+username+" 回来");
        } else {
            System.out.println("用户名或密码错误");
        }

        //5.关闭资源
        JDBCUtils.close(ps,conn,rs);
    }
}
