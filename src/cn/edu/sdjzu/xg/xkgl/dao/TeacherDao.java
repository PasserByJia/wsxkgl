package cn.edu.sdjzu.xg.xkgl.dao;

import cn.edu.sdjzu.xg.xkgl.domain.Teacher;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public class TeacherDao {
    private static TeacherDao teacherDao = new TeacherDao();
    private static Connection conn = null;
    private static ResultSet rs = null;
    private static PreparedStatement pstmt = null;
    private TeacherDao (){}
    public static TeacherDao getInstance(){return  teacherDao;}

    public Collection<Teacher> findAll()throws SQLException{
        String selectSql="SELECT * FROM teacher";
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(selectSql);
        rs = pstmt.executeQuery();
        Collection<Teacher> teachers = mapResultSetToTeacher(rs);
        JdbcHelper.close(pstmt,conn);
        return teachers;
    }
    public Collection<Teacher> findAll(String conditionStr)throws SQLException{
        StringBuilder sqlSb= new StringBuilder("SELECT * FROM teacher");
        if(conditionStr!=null&&conditionStr.trim().length()!=0){
            sqlSb.append(" where");
            sqlSb.append(conditionStr);
        }
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(sqlSb.toString());
        rs = pstmt.executeQuery();
        Collection<Teacher> teachers = mapResultSetToTeacher(rs);
        JdbcHelper.close(pstmt,conn);
        return teachers;
    }
    private Collection<Teacher> mapResultSetToTeacher(ResultSet rs) throws SQLException {
        Collection<Teacher> teachers  = new TreeSet<Teacher>();
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String no = rs.getString("no");
            String password = rs.getString("password");
            String sex = rs.getString("sex");
            String username = rs.getString("username");
            int proTitle_id = rs.getInt("proTitle_id");
            Teacher teacher = new Teacher(id,username,password,name,no,sex,ProTitleDao.getInstance().findProTitle(proTitle_id));
            teachers.add(teacher);
        }
        return teachers;
    }
    public  Teacher find(Integer id)throws SQLException {
        String selectSql="SELECT * FROM teacher WHERE id =?";
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(selectSql);
        pstmt.setInt(1,id);
        rs = pstmt.executeQuery();
        Teacher teacher = null;
        while (rs.next()) {
            String name = rs.getString("name");
            String no = rs.getString("no");
            String password = rs.getString("password");
            String sex = rs.getString("sex");
            String username = rs.getString("username");
            int proTitle_id = rs.getInt("proTitle_id");
            teacher = new Teacher(id,username,password,name,no,sex,ProTitleDao.getInstance().findProTitle(proTitle_id));
        }
        JdbcHelper.close(pstmt,conn);
        return teacher;
    }

    public Boolean update(Teacher teacher)throws SQLException{
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement("UPDATE teacher " +
                        "SET name=?," +
                        "no=?," +
                        "password=?," +
                        "sex=?," +
                        "username=?," +
                        "proTitle_id=?" +
                        " WHERE id=?");
        pstmt.setString(1,teacher.getName());
        pstmt.setString(2,teacher.getNo());
        pstmt.setString(3,teacher.getPassword());
        pstmt.setString(4,teacher.getSex());
        pstmt.setString(5,teacher.getUsername());
        pstmt.setInt(6,teacher.getProTitle().getId());
        pstmt.setInt(7,teacher.getId());
        int affectedRowNum = pstmt.executeUpdate();
        JdbcHelper.close(pstmt,conn);
        return affectedRowNum >0;
    }


    public Boolean add(Teacher teacher)throws SQLException{
        conn = JdbcHelper.getConn();
        pstmt= conn.prepareStatement("insert into teacher " +
                        " (name, no, password, sex, username, proTitle_id)" +
                        " values(?,?,?,?,?,?)");
        pstmt.setString(1,teacher.getName());
        pstmt.setString(2,teacher.getNo());
        pstmt.setString(3,teacher.getPassword());
        pstmt.setString(4,teacher.getSex());
        pstmt.setString(5,teacher.getUsername());
        pstmt.setInt(6,teacher.proTitle.getId());
        int rowAffected = pstmt.executeUpdate();
        JdbcHelper.close(pstmt, conn);
        return rowAffected > 0;
    }
    public boolean delete(Teacher teacher, Connection connection)throws SQLException{
        String deleteSql="DELETE FROM teacher WHERE id=?";
        pstmt = connection.prepareStatement(deleteSql);
        pstmt.setInt(1,teacher.getId());
        int rowAffected = pstmt.executeUpdate();
        return rowAffected>0;
    }
    public boolean delete(Integer id)throws SQLException{
        Teacher teacher = this.find(id);
        return this.delete(teacher);
    }

    private boolean delete(Teacher teacher) throws SQLException{
        String deleteSql="DELETE FROM teacher WHERE id=?";
        conn = JdbcHelper.getConn();
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(deleteSql);
        pstmt = conn.prepareStatement(deleteSql);
        pstmt.setInt(1, teacher.getId());
        int rowAffected = pstmt.executeUpdate();
        JdbcHelper.close(pstmt,conn);
        return rowAffected>0;
    }
}
