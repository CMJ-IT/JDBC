package com.itcast.PreparedStatement;

import org.junit.Test;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/*PreparedStatement实现增删改查*/
public class PreparedStatement2 {
    @Test
    /*1.添加数据*/
    public void add() throws Exception {
        //1.获取连接对象
        Connection conn = JDBCUtils.getConnection();
        //2.准备SQL语句
        String sql = "insert into student values(null,?,?,?);";
        //3.获取预编译对象
        PreparedStatement ps = conn.prepareStatement(sql);

        //4.给占位符?赋值数据
        ps.setString(1,"jack");
        ps.setString(2,"男");
        ps.setDate(3, Date.valueOf("2001-10-12"));

        //5.执行SQL语句
        ps.executeUpdate();
        //6.关闭资源
        JDBCUtils.close(ps,conn);
    }


    @Test
    /*2.修改数据*/
    public void update() throws Exception {
        //1.获取连接对象
        Connection conn = JDBCUtils.getConnection();
        //2.准备SQL语句
        String sql = "update student set name = ?, gender = ? where id = ?;";
        //3.获取预编译对象
        PreparedStatement ps = conn.prepareStatement(sql);

        //4.给占位符?赋值数据
        ps.setString(1,"lucky");
        ps.setString(2,"女");
        ps.setInt(3,9);

        //5.执行SQL语句
        ps.executeUpdate();
        //6.关闭资源
        JDBCUtils.close(ps,conn);
    }

    @Test
    /*3.删除数据*/
    public void delete() throws Exception {
        //1.获取连接对象
        Connection conn = JDBCUtils.getConnection();
        //2.准备SQL语句
        String sql = "delete from student where id = ?;";
        //3.获取预编译对象
        PreparedStatement ps = conn.prepareStatement(sql);

        //4.给占位符?赋值数据
        ps.setInt(1,9);

        //5.执行SQL语句
        ps.executeUpdate();
        //6.关闭资源
        JDBCUtils.close(ps,conn);
    }


    /*4.查询id小于4的学生信息,并保存将每一个记录 封装成一个学生对象 存储到List集合中*/
    @Test
    public void select() throws Exception {
        //1.获取连接对象
        Connection conn = JDBCUtils.getConnection();
        //2.准备SQL语句
        String sql = "select * from student where id < ?; ";
        //3.获取预编译对象
        PreparedStatement ps = conn.prepareStatement(sql);

        //4.给占位符?赋值数据
        ps.setInt(1,4);

        //5.执行SQL语句
        ResultSet rs = ps.executeQuery();

        //6.创建集合储存集合对象
        ArrayList<Student> list = new ArrayList<>();

        // 7.循环读取每一行记录
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            Date birthday = rs.getDate("birthday");

            //创建学生对象
            Student stu = new Student(id,name,gender,birthday);
            //将学生添加到集合中
            list.add(stu);
        }

        for (Student students : list){
            System.out.println(students);
        }
        //8.关闭资源
        JDBCUtils.close(ps,conn,rs);
    }
}
