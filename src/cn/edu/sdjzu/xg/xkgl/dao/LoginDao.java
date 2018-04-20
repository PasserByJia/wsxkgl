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
    //创建本类的对象
    private static LoginDao loginDao = new LoginDao();
    //构造器定义为private，“阻止”其它类创建本类的对象
    private LoginDao(){}
    //返回(而不是创建)本类惟一对象
    public static LoginDao getInstance(){
        return loginDao;
    }
    public Student findStudent(String username,String password) throws SQLException{
        String selectSql = "SELECT * FROM student WHERE username=? AND password=?";
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(selectSql);
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        rs = pstmt.executeQuery();
        Student student = null;
        while(rs.next()){
            int id  = rs.getInt("id");
            String sex = rs.getString("sex");
            String no = rs.getString("no");
            String name = rs.getString("name");
            student = new Student(id,username,password,sex,no,name);
        }
        JdbcHelper.close(rs,statement,conn);
        return student;
    }
    public Teacher findTeacher(String username,String password) throws SQLException{

        String selectSql = "SELECT * FROM teacher WHERE username=? AND password=?";
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(selectSql);
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        rs = pstmt.executeQuery();
        Teacher teacher = null;
        while(rs.next()){
            int id  = rs.getInt("id");
            String name = rs.getString("name");
            String no = rs.getString("no");
            String sex = rs.getString("sex");
            int proTitle_id = rs.getInt("proTitle_id");
            ProTitle proTitle = ProTitleService.getInstance().findProTitle(proTitle_id);
            teacher = new Teacher(id,username,password,name,no,sex,proTitle);
        }
        JdbcHelper.close(rs,statement,conn);
        return teacher;
    }
    public EduAdmin findEduAdmin(String username, String password) throws SQLException{
        String selectSql = "SELECT * FROM eduadmin WHERE username=? AND password=?";
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(selectSql);
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        rs = pstmt.executeQuery();
        EduAdmin eduAdmin = null;
        while(rs.next()){
            int id  = rs.getInt("id");
            String name = rs.getString("name");
            String no = rs.getString("no");
            String sex = rs.getString("sex");
            eduAdmin = new EduAdmin(id,username,password,name,no,sex);
        }
        JdbcHelper.close(rs,statement,conn);
        return eduAdmin;
    }
    public SysAdmin findSysAdmin(String username, String password) throws SQLException{
        String selectSql = "SELECT * FROM sysadmin WHERE username=? AND password=?";
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(selectSql);
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        rs = pstmt.executeQuery();
        SysAdmin sysAdmin = null;
        while(rs.next()){
            int id  = rs.getInt("id");
            String name = rs.getString("name");
            String no = rs.getString("no");
            String sex = rs.getString("sex");
            sysAdmin = new SysAdmin(id,username,password,name,no,sex);
        }
        JdbcHelper.close(rs,statement,conn);
        return sysAdmin;
    }
}
