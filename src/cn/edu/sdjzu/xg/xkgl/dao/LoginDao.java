package cn.edu.sdjzu.xg.xkgl.dao;


import cn.edu.sdjzu.xg.xkgl.domain.*;
import cn.edu.sdjzu.xg.xkgl.service.ProTitleService;
import util.JdbcHelper;

import java.io.Serializable;
import java.sql.*;

public class LoginDao {
    //声明数据库各个对象的引用
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet rs = null;
    private static PreparedStatement pstmt = null;
    //创建本类的惟一对象
    private static LoginDao loginDao = new LoginDao();
    //构造器定义为private，“阻止”其它类创建本类的对象
    private LoginDao(){}
    //返回(而不是创建)本类惟一对象
    public static LoginDao getInstance(){
        return loginDao;
    }
    /*查找所有学生*/
    public Student findStudent(String username,String password) throws SQLException{
        //SQL查询语句
        String selectSql = "SELECT * FROM student WHERE username=? AND password=?";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        pstmt = conn.prepareStatement(selectSql);
        //对预编译语句参数进行赋值
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        //执行预编译语句，返回结果集
        rs = pstmt.executeQuery();
        //声明学生引用
        Student student = null;
        //获得结果集，对学生对象属性赋值
        while(rs.next()){
            int id  = rs.getInt("id");
            String sex = rs.getString("sex");
            String no = rs.getString("no");
            String name = rs.getString("name");
            student = new Student(id,username,password,sex,no,name);
        }
        //关闭资源
        JdbcHelper.close(rs,statement,conn);
        return student;
    }
    /*通过用户名和密码查找一个老师*/
    public Teacher findTeacher(String username,String password) throws SQLException{
        //SQL查询语句
        String selectSql = "SELECT * FROM teacher WHERE username=? AND password=?";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        pstmt = conn.prepareStatement(selectSql);
        //对预编译语句参数进行赋值
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        //执行预编译语句，返回结果集
        rs = pstmt.executeQuery();
        //声明老师引用
        Teacher teacher = null;
        //获得结果集，对老师对象属性赋值
        while(rs.next()){
            int id  = rs.getInt("id");
            String name = rs.getString("name");
            String no = rs.getString("no");
            String sex = rs.getString("sex");
            int proTitle_id = rs.getInt("proTitle_id");
            ProTitle proTitle = ProTitleService.getInstance().findProTitle(proTitle_id);
            teacher = new Teacher(id,username,password,name,no,sex,proTitle);
        }
        //关闭资源
        JdbcHelper.close(rs,statement,conn);
        return teacher;
    }
    /*通过用户名和密码查找一个教务管理员*/
    public EduAdmin findEduAdmin(String username, String password) throws SQLException{
        //SQL查询语句
        String selectSql = "SELECT * FROM eduadmin WHERE username=? AND password=?";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        pstmt = conn.prepareStatement(selectSql);
        //对预编译语句参数进行赋值
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        //执行预编译语句，返回结果集
        rs = pstmt.executeQuery();
        //声明教务管理员引用
        EduAdmin eduAdmin = null;
        //获得结果集，对教务管理员对象属性赋值
        while(rs.next()){
            int id  = rs.getInt("id");
            String name = rs.getString("name");
            String no = rs.getString("no");
            String sex = rs.getString("sex");
            eduAdmin = new EduAdmin(id,username,password,name,no,sex);
        }
        //关闭资源
        JdbcHelper.close(rs,statement,conn);
        return eduAdmin;
    }
    /*通过用户名和密码查找一个系统管理员*/
    public SysAdmin findSysAdmin(String username, String password) throws SQLException{
        //SQL查询语句
        String selectSql = "SELECT * FROM sysadmin WHERE username=? AND password=?";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        pstmt = conn.prepareStatement(selectSql);
        //对预编译语句参数进行赋值
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        //执行预编译语句，返回结果集
        rs = pstmt.executeQuery();
        //声明系统管理员引用
        SysAdmin sysAdmin = null;
        //获得结果集，对系统管理员对象属性赋值
        while(rs.next()){
            int id  = rs.getInt("id");
            String name = rs.getString("name");
            String no = rs.getString("no");
            String sex = rs.getString("sex");
            sysAdmin = new SysAdmin(id,username,password,name,no,sex);
        }
        //关闭资源
        JdbcHelper.close(rs,statement,conn);
        return sysAdmin;
    }
}
