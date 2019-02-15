package com.itcast.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**JDBC开发步骤小结
     1. 注册驱动
     2. 与数据库建立连接并获得连接对象
     3. 调用连接对象的createStatement方法获得发送对象
     4. 准备要执行的SQL语句
     5. 调用发送对象Statement的方法执行SQL语句并获得结果
          * int executeUpdate(String sql) 获得影响行数
          * ResultSet executeQuery(String sq) 获得结果集对象
     6 调用结果集对象的getXxx方法获得数据
     7. 关闭资源：先开后关 => ResultSet->Statement->Connection
*/


/*查询所有学生信息*/
public class JDBCTest2_ResultSetDemo {
    public static void main(String[] args) throws Exception {
        //1.注解驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql:///day18", "root", "abc12345");
        //3.获取发送对象
        Statement stat = conn.createStatement();
        //4.准备SQL语句
        String sql = "select * from student;";
        //5.获取结果集对象(ResultSet),执行SQL语句
        ResultSet rs = stat.executeQuery(sql);
        //6.使用循环读取数据
        while (rs.next()){
            //获得学号
            int id = rs.getInt("id");
            // 获得姓名
            String name = rs.getString("name");
            // 获得性别
            String gender = rs.getString("gender");
            // 获得生日
            String birthday = rs.getString("birthday");
            System.out.println(id+name+gender+birthday);

            /**通过行数获取(不推荐)
                //获得学号
                int id = rs.getInt(1);
                // 获得姓名
                String name = rs.getString(2);
                // 获得性别
                String gender = rs.getString(3);
                // 获得生日
                Date birthday = rs.getDate(4);
             */
        }
        //7.关闭资源
        rs.close();
        stat.close();
        conn.close();
    }
}
