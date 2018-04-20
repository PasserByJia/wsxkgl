package cn.edu.sdjzu.xg.xkgl.dao;


import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.service.StudentService;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public final class CourseSelectionDao {
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static PreparedStatement preparedStatement = null;
    private static CourseSelectionDao courseSelectionDao = new CourseSelectionDao();
    private CourseSelectionDao(){}
    public static CourseSelectionDao getInstance(){
        return courseSelectionDao;
    }

    public CourseSelection find(Integer id)throws SQLException {
        String selectSql="SELECT * FROM courseSelection WHERE id=?";
        connection = JdbcHelper.getConn();
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setInt(1,id);
        resultSet = preparedStatement.executeQuery();
        CourseSelection courseSelection = null;
        while(resultSet.next()){
            int course_id =  resultSet.getInt("course_id");
            int student_id = resultSet.getInt("student_id");
            Course course = CourseDao.getInstance().find(course_id);
            Student student = StudentDao.getInstance().find(student_id);
            courseSelection = new CourseSelection(id,course,student);
        }
        return courseSelection;
    }

    public Collection<CourseSelection> findAll() throws SQLException{

        String selectSql="SELECT * FROM courseSelection";
        connection = JdbcHelper.getConn();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(selectSql);
        Collection<CourseSelection> courseSelections = mapResultSetToTeacher(resultSet);
        JdbcHelper.close(resultSet,statement,connection);
        return courseSelections;
    }
    public Collection<CourseSelection> findByStudentId(Integer student_id) throws SQLException{
        String selectSql="SELECT * FROM courseSelection WHERE student_id=?";
        connection = JdbcHelper.getConn();
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setInt(1,student_id);
        resultSet = preparedStatement.executeQuery();
        Collection<CourseSelection> courseSelections = mapResultSetToTeacher(resultSet);
        return courseSelections;
    }

    public  Collection<CourseSelection> findByStudentUsername(String username)throws SQLException{
        Student student = StudentService.getInstance().findByUsername(username);
        int student_id = student.getId();
        return findByStudentId(student_id);
    }

    public Collection<CourseSelection> findByCourseTitle(int courseid) throws SQLException{
        Connection connection = JdbcHelper.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM courseselection WHERE course_id=?");
        //对预编译语句对象的参数赋值
        preparedStatement.setInt(1,courseid);
        ResultSet resultSet = preparedStatement.executeQuery();
        Collection<CourseSelection> courseSelectionCollection = mapResultSetToTeacher(resultSet);
        JdbcHelper.close(preparedStatement,connection);
        return courseSelectionCollection;
    }
    private Collection<CourseSelection> mapResultSetToTeacher(ResultSet resultSet) throws SQLException {
        Collection<CourseSelection> courseSelectionCollection  = new TreeSet<CourseSelection>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            int student_id = resultSet.getInt("student_id");
            int course_id = resultSet.getInt("course_id");
            Course course = CourseDao.getInstance().find(course_id);
            Student student = StudentDao.getInstance().find(student_id);
            CourseSelection courseSelection = new CourseSelection(id,course,student);
            courseSelectionCollection.add(courseSelection);
        }
        return courseSelectionCollection;
    }

    public Boolean update(CourseSelection courseSelection) throws SQLException{
        String updateSql="UPDATE courseSelection SET course_id=?,student_id=? WHERE id=?";
        connection = JdbcHelper.getConn();
        preparedStatement=connection.prepareStatement(updateSql);
        preparedStatement.setInt(1, courseSelection.getCourse().getId());
        preparedStatement.setInt(2, courseSelection.getStudent().getId());
        preparedStatement.setInt(3,courseSelection.getId());
        int rowAffected = preparedStatement.executeUpdate();
        JdbcHelper.close(preparedStatement,connection);
        return rowAffected>0;
    }

    public Boolean add(CourseSelection courseSelection) throws SQLException{
        String addSql = "INSERT INTO courseSelection(course_id,sutdent_id) VALUES (?,?)";
        connection = JdbcHelper.getConn();
        preparedStatement = connection.prepareStatement(addSql);
        preparedStatement.setInt(1,courseSelection.getCourse().getId());
        preparedStatement.setInt(2,courseSelection.getStudent().getId());
        int rowAffected = preparedStatement.executeUpdate();
        JdbcHelper.close(preparedStatement,connection);
        return rowAffected>0;
    }
    public Boolean add(CourseSelection courseSelection,Connection connection) throws SQLException{
        String addSql = "INSERT INTO courseSelection(course_id,student_id) VALUES (?,?)";
        preparedStatement = connection.prepareStatement(addSql);
        preparedStatement.setInt(1,courseSelection.getCourse().getId());
        preparedStatement.setInt(2,courseSelection.getStudent().getId());
        int rowAffected = preparedStatement.executeUpdate();
        return rowAffected>0;
    }

    public boolean delete(Integer id)throws SQLException{
        CourseSelection courseSelection = this.find(id);
        return this.delete(courseSelection);
    }
    public boolean delete(Integer id,Connection connection)throws SQLException{
        CourseSelection courseSelection = this.find(id);
        return this.delete(courseSelection,connection);
    }
    private boolean delete(CourseSelection courseSelection) throws SQLException{
        String deleteSql="DELETE FROM courseSelection WHERE id=?";
        connection = JdbcHelper.getConn();
        preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setInt(1, courseSelection.getId());
        int rowAffected = preparedStatement.executeUpdate();
        JdbcHelper.close(preparedStatement,connection);
        return rowAffected>0;
    }

    public boolean delete(CourseSelection courseSelection, Connection connection) throws SQLException{
        String deleteSql="DELETE FROM courseSelection WHERE id=?";
        preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setInt(1,courseSelection.getId());
        int rowAffected = preparedStatement.executeUpdate();
        return rowAffected>0;
    }

}
