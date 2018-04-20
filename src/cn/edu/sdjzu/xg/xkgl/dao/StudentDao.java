package cn.edu.sdjzu.xg.xkgl.dao;

import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.domain.Teacher;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public final class StudentDao {
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static PreparedStatement preparedStatement = null;
    private static StudentDao studentDao = new StudentDao();
    private static Collection<Student> students = new TreeSet<Student>();
    private StudentDao(){}
    public static StudentDao getInstance(){
        return studentDao;
    }

    public Student find(Integer id)throws SQLException{
        String selectSql="SELECT * FROM student WHERE id=?";
        connection = JdbcHelper.getConn();
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setInt(1,id);
        resultSet = preparedStatement.executeQuery();
        Student student = null;
        while (resultSet.next()) {
            String no = resultSet.getString("no");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String sex = resultSet.getString("sex");
            String username = resultSet.getString("username");
            student = new Student(id,username,password,sex,no,name);
        }
        return student;
    }

    public Collection<Student> findAll() throws SQLException{

        String selectSql="SELECT * FROM student";
        connection = JdbcHelper.getConn();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(selectSql);
        Collection<Student> students = mapResultSetToTeacher(resultSet);
        JdbcHelper.close(resultSet,statement,connection);
        return students;
    }
    public Collection<Student> findAll(String conditionStr)throws SQLException{
        StringBuilder sqlSb= new StringBuilder("SELECT * FROM student");
        if(conditionStr!=null&&conditionStr.trim().length()!=0){
            sqlSb.append(" where");
            sqlSb.append(conditionStr);
        }
        connection = JdbcHelper.getConn();
        preparedStatement = connection.prepareStatement(sqlSb.toString());
        resultSet = preparedStatement.executeQuery();
        Collection<Student> students = mapResultSetToTeacher(resultSet);
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return students;
    }
    private Collection<Student> mapResultSetToTeacher(ResultSet resultSet) throws SQLException {
        Collection<Student> students  = new TreeSet<Student>();
        while(resultSet.next()){
            Student student = new Student(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("sex"),
                    resultSet.getString("no"),
                    resultSet.getString("name"));
            students.add(student);
        }
        return students;
    }
    public  Student findByUsername(String no)throws SQLException{
        String selectSql="SELECT * FROM student WHERE no=?";
        connection = JdbcHelper.getConn();
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1,no);
        resultSet = preparedStatement.executeQuery();
        Student student = null;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String sex = resultSet.getString("sex");
            String username = resultSet.getString("username");
            student = new Student(id,username,password,sex,no,name);
        }
        return student;
    }
    public Boolean update(Student student) throws SQLException{
        String updateSql="UPDATE student SET name=?,no=?,password=?,sex=?,username=? WHERE id=?";
        connection = JdbcHelper.getConn();
        preparedStatement=connection.prepareStatement(updateSql);
        preparedStatement.setString(1,student.getName());
        preparedStatement.setString(2,student.getNo());
        preparedStatement.setString(3,student.getPassword());
        preparedStatement.setString(4,student.getSex());
        preparedStatement.setString(5,student.getUsername());
        preparedStatement.setInt(6,student.getId());
        int rowAffected = preparedStatement.executeUpdate();
        JdbcHelper.close(preparedStatement,connection);
        return rowAffected>0;
    }

    public Boolean add(Student student) throws SQLException{
        String addSql = "INSERT INTO student(name,no,password,sex,username) VALUES (?,?,?,?,?)";
        connection = JdbcHelper.getConn();
        preparedStatement = connection.prepareStatement(addSql);
        preparedStatement.setString(1,student.getName());
        preparedStatement.setString(2,student.getNo());
        preparedStatement.setString(3,student.getPassword());
        preparedStatement.setString(4,student.getSex());
        preparedStatement.setString(5,student.getUsername());
        int rowAffected = preparedStatement.executeUpdate();
        JdbcHelper.close(preparedStatement,connection);
        return rowAffected>0;
    }

    public boolean delete(Integer id)throws SQLException{
        Student student = this.find(id);
        return this.delete(student);
    }

    private boolean delete(Student student) throws SQLException{
        String deleteSql="DELETE FROM student WHERE id=?";
        connection = JdbcHelper.getConn();
        preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setInt(1, student.getId());
        int rowAffected = preparedStatement.executeUpdate();
        JdbcHelper.close(preparedStatement,connection);
        return rowAffected>0;
    }

    public boolean delete(Student student, Connection connection) throws SQLException{
        String deleteSql="DELETE FROM student WHERE id=?";
        preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setInt(1,student.getId());
        int rowAffected = preparedStatement.executeUpdate();
        return rowAffected>0;
    }
}
