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
    // 返回所有课程
    public Collection<Course> findAll() throws SQLException {
        return courseDao.findAll();
    }
    //根据传入的条件，返回符合条件的所有课程
    public Collection<Course> findAll(String conditionStr)throws SQLException{
        return courseDao.findAll(conditionStr);
    }
    //根据id，找到对应的课程
    public Course find(Integer id)throws SQLException{
        return courseDao.find(id);
    }
    // 更新课程信息
    public boolean update(Course course)throws SQLException{
        return courseDao.update(course);
    }
    //增加课程
    public boolean add(Course course)throws SQLException{
        return courseDao.add(course);
    }
    //根据id删除相应课程
    public boolean delete(Integer id)throws SQLException{
        //找到相应课程
        Course course =CourseService.getInstance().find(id);
        return CourseService.getInstance().delete(course);
    }
    //根据传入的title找到对应一个的课程
    public  Course findOneByTitle(String title)throws SQLException{
        return courseDao.findOneByTitle(title);
    }
    //根据传入的title找到对应的所有课程
    public Collection <Course> findByTitle(String title)throws SQLException{
        return courseDao.findByTitle(title);
    }
    //根据传入的teacher找到对应的所有课程
    public Collection <Course> findByTeacher(Teacher teacher) throws  SQLException{
        return courseDao.findByTeacher(teacher);
    }
    //根据传入type的找到对应的所有课程
    public Collection<Course> findByType(Collection<CourseType> type)throws SQLException{
        return courseDao.findByType(type);
    }
    //删除课程
    public  boolean delete(Course course)throws SQLException {
        //获取连接
        Connection connection = JdbcHelper.getConn();
        //将自动提交设为false,开始事务
        connection.setAutoCommit(false);
        boolean deleted = false;
        try {
            //创建参数语句对象
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM courseselection WHERE course_id=?");
            //为参数赋值
            preparedStatement.setInt(1,course.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                //获取id栏信息
                int id  =resultSet.getInt("id");
                //删除相应的选课记录
                deleted = CourseSelectionDao.getInstance().delete(id,connection);
            }
            //删除课程
            CourseDao.getInstance().delete(course,connection);
            //提交
            connection.commit();
        } catch (SQLException e) {
            //回滚事务
            connection.rollback();
        } finally {
            //将自动提交设置为true，结束事务
            connection.setAutoCommit(true);
            //关闭连接
            JdbcHelper.close(null,connection);
        }
        return deleted;
    }

    public boolean delete(int course_id,Connection connection)throws Exception {
        boolean deleted = false;
        try {
            //创建语句对象
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM courseselection WHERE course_id=?");
            //赋值语句对象的参数
            preparedStatement.setInt(1,course_id);
            //执行查询
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                //获取id
                int id  =resultSet.getInt("id");
                //删除相应的选课记录
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
