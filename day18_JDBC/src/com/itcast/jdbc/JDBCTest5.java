package com.itcast.jdbc;

import java.sql.Connection;
import java.sql.Statement;

/*使用JDBC的来操作银行转账的事务*/
public class JDBCTest5 {
    public static void main(String[] args) {
        Connection conn = null;//声明连接对象
        Statement stat = null;//声明发送对象
        try {
            /**1.获取连接对象*/
            conn = JDBCUtils.getConnection();
            //2.禁止自动提交事务(开启事务,等价start transaction;)
            conn.setAutoCommit(false);
            /**3.获取发送对象*/
            stat = conn.createStatement();
            //4.张三扣钱,更新
            String sql1 = "update account set balence = balence - 500 where name = '张三';";
            stat.executeUpdate(sql1);

            /*5.模拟出现异常*/
            //System.out.println(100/0);

            //6.李四加钱,更新
            String sql2 = "update account set balence = balence + 500 where name = '李四';";
            stat.executeUpdate(sql2);
            //7.提交事务
            conn.commit();

            //8.如果没有异常输出转账成功
            System.out.println("转账成功");
        } catch (Exception e) {
            System.out.println("转账失败");
            try {
                //9.事务回滚(出现异常,事务回滚,不对数据库进行更改)
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                //10.关闭资源
                JDBCUtils.close(stat,conn);
            }
        }
    }
}
