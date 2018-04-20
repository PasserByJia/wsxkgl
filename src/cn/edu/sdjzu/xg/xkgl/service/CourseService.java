package cn.edu.sdjzu.xg.xkgl.service;



import cn.edu.sdjzu.xg.xkgl.dao.CourseDao;
import cn.edu.sdjzu.xg.xkgl.dao.CourseSelectionDao;
import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.domain.CourseType;
import cn.edu.sdjzu.xg.xkgl.domain.Teacher;
import util.JdbcHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public final class CourseService {
    private static CourseDao courseDao= CourseDao.getInstance();
    //创建本类唯一的对象
    private static CourseService courseService=new CourseService();
    //静态方法，返回本类的惟一对象
    public static CourseService getInstance(){
        return courseService;
    }
    public Collection<Course> findAll() throws SQLException {
        return courseDao.findAll();
    }
    public Collection<Course> findAll(String conditionStr)throws SQLException{
        return courseDao.findAll(conditionStr);
    }
    public Course find(Integer id)throws SQLException{
        return courseDao.find(id);
    }
    public boolean update(Course course)throws SQLException{
        return courseDao.update(course);
    }
    public boolean add(Course course)throws SQLException{
        return courseDao.add(course);
    }
    public boolean delete(Integer id)throws SQLException{
        Course course =CourseService.getInstance().find(id);
        return CourseService.getInstance().delete(course);
    }
    public  Course findOneByTitle(String title)throws SQLException{
        return courseDao.findOneByTitle(title);
    }
    public Collection <Course> findByTitle(String title)throws SQLException{
        return courseDao.findByTitle(title);
    }
    public Collection <Course> findByTeacher(Teacher teacher) throws  SQLException{
        return courseDao.findByTeacher(teacher);
    }
    public Collection<Course> findByType(Collection<CourseType> type)throws SQLException{
        return courseDao.findByType(type);
    }
    public  boolean delete(Course course)throws SQLException {
        Connection connection = JdbcHelper.getConn();
        //将自动提交设为false,开始事务
        connection.setAutoCommit(false);
        boolean deleted = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM courseselection WHERE course_id=?");
            preparedStatement.setInt(1,course.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id  =resultSet.getInt("id");
                deleted = CourseSelectionDao.getInstance().delete(id,connection);
            }
            CourseDao.getInstance().delete(course,connection);
            connection.commit();
        } catch (SQLException e) {
            //回滚事务
            connection.rollback();
        } finally {
            //将自动提交设置为true，结束事务
            connection.setAutoCommit(true);
            JdbcHelper.close(null,connection);
        }
        return deleted;
    }

    public boolean delete(int course_id,Connection connection)throws Exception {
        boolean deleted = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM courseselection WHERE course_id=?");
            preparedStatement.setInt(1,course_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id  =resultSet.getInt("id");
                deleted = CourseSelectionDao.getInstance().delete(id,connection);
            }
            CourseDao.getInstance().delete(course_id,connection);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("删除课程对应选课关系失败");
        }
        return deleted;
    }
}
