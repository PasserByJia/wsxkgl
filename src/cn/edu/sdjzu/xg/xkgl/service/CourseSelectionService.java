package cn.edu.sdjzu.xg.xkgl.service;


import cn.edu.sdjzu.xg.xkgl.dao.CourseDao;
import cn.edu.sdjzu.xg.xkgl.dao.CourseSelectionDao;
import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.domain.Student;
import util.JdbcHelper;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public final class CourseSelectionService {
    //私有化对象，单例模式
    private static CourseSelectionDao courseSelectionDao = CourseSelectionDao.getInstance();
    private static CourseSelectionService courseSelectionService = new CourseSelectionService();
    //私有构造器
    private CourseSelectionService(){}
     //该方法返回当前类对象
    public static CourseSelectionService getCourseSelectionService(){
        return courseSelectionService;
    }
    //返回所有选课记录
    public Collection<CourseSelection> findAll() throws SQLException {
        return courseSelectionDao.findAll();
    }
//返回指定id的对应选课记录
    public CourseSelection find(Integer id) throws SQLException{
        return courseSelectionDao.find(id);
    }
    //通过参数course_id返回同一课程的所有学生
    public List<Student> findStudentByCourseId(Integer course_id) throws SQLException{
        return courseSelectionDao.findStudentByCourseId(course_id);
    }
    //通过参数courseTitle返回同一课题的所有选课记录
    public Collection<CourseSelection> findByCourseTitle(String courseTitle)throws SQLException{
        Course course = CourseService.getInstance().findOneByTitle(courseTitle);
        int courseId = course.getId();
        return courseSelectionDao.findByCourseTitle(courseId);
    }
    //通过参数username返回同一用户名的所有选课记录
    public Collection<CourseSelection> findByStudentUsername(String username)throws SQLException{
        return courseSelectionDao.findByStudentUsername(username);
    }
    //更新选课记录
    public boolean update(CourseSelection courseSelection) throws SQLException{
        return courseSelectionDao.update(courseSelection);
    }
    //增加选课记录
    public boolean add(CourseSelection courseSelection) throws Exception{
        //进行事务的连接
        Connection connection=JdbcHelper.getConn();
        //将自动提交设为false，开始事务
        connection.setAutoCommit(false);
        boolean update=false;
        PreparedStatement preparedStatement=null;
        try{
            //调用CourseSelectionDao的方法，添加选课记录
            CourseSelectionDao.getInstance().add(courseSelection,connection);
            //获取课程，并赋值给course
            Course course= courseSelection.getCourse();
            //如果当前选课人数小于课程上限3，则当前选课人数加1
            if(course.getAccumulation()<course.getMax()){
                course.setAccumulation(course.getAccumulation()+1);
                //更新课程相应的信息
                update=CourseDao.getInstance().update(course,connection);
            }else{
                //自定义异常
                throw new Exception("选课人数超限");
            }
        }catch (SQLException e){
            //回滚事务
            connection.rollback();
            throw new Exception("选课失败");
        }catch (Exception e){
            connection.rollback();
            throw e;
        }finally {
            //将自动提交设为true，结束事务
            connection.setAutoCommit(true);
            //关闭链接
            JdbcHelper.close(preparedStatement,connection);
        }
        return update;
    }
    public boolean  delete(int id)throws Exception{
        //根据传入参数，找到当前选课记录
        CourseSelection courseSelection = this.find(id);
        boolean delete=false;
        //获取连接
        Connection connection=JdbcHelper.getConn();
        //将自动提交设为false，开始事务
        connection.setAutoCommit(false);
        //定义语句对象
        PreparedStatement preparedStatement=null;
        try{
            //删除选课记录
            boolean deleteRelation = CourseSelectionDao.getInstance().delete(courseSelection,connection);
            //获取课程
            Course course= courseSelection.getCourse();
            //当前选课人数减一
            course.setAccumulation(course.getAccumulation()-1);
            //更新课程信息
            delete =CourseDao.getInstance().update(course,connection);
        }catch (SQLException e) {
            //回滚事务
            connection.rollback();
            throw new Exception("删除课程失败");
        } finally {
            //将自动提交设为true，结束事务
            connection.setAutoCommit(true);
            // 关闭连接
            JdbcHelper.close(preparedStatement,connection);
        }

        return delete;
    }
    //删除选课记录，事务的一部分
    public boolean delete(CourseSelection courseSelection,Connection connection) throws SQLException {
        return CourseSelectionDao.getInstance().delete(courseSelection,connection);
    }

}