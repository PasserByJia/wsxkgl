package cn.edu.sdjzu.xg.xkgl.service;


import cn.edu.sdjzu.xg.xkgl.dao.CourseDao;
import cn.edu.sdjzu.xg.xkgl.dao.CourseSelectionDao;
import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import util.JdbcHelper;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public final class CourseSelectionService {
    private static CourseSelectionDao courseSelectionDao = CourseSelectionDao.getInstance();
    private static CourseSelectionService courseSelectionService = new CourseSelectionService();
    private CourseSelectionService(){}

    public static CourseSelectionService getCourseSelectionService(){
        return courseSelectionService;
    }

    public Collection<CourseSelection> findAll() throws SQLException {
        return courseSelectionDao.findAll();
    }

    public CourseSelection find(Integer id) throws SQLException{
        return courseSelectionDao.find(id);
    }
    public Collection<CourseSelection> findByCourseTitle(String courseTitle)throws SQLException{
        Course course = CourseService.getInstance().findOneByTitle(courseTitle);
        int courseId = course.getId();
        return courseSelectionDao.findByCourseTitle(courseId);
    }
    public Collection<CourseSelection> findByStudentUsername(String username)throws SQLException{
        return courseSelectionDao.findByStudentUsername(username);
    }

    public boolean update(CourseSelection courseSelection) throws SQLException{
        return courseSelectionDao.update(courseSelection);
    }

    public boolean add(CourseSelection courseSelection) throws Exception{
        Connection connection=JdbcHelper.getConn();
        connection.setAutoCommit(false);
        boolean update=false;
        PreparedStatement preparedStatement=null;
        try{
            CourseSelectionDao.getInstance().add(courseSelection,connection);
            Course course= courseSelection.getCourse();
            if(course.getAccumulation()<course.getMax()){
                course.setAccumulation(course.getAccumulation()+1);
                update=CourseDao.getInstance().update(course,connection);
            }else{
                //自定义异常
                throw new Exception("选课人数超限");
            }
        }catch (SQLException e){
            connection.rollback();
            throw new Exception("选课失败");
        }catch (Exception e){
            connection.rollback();
            throw e;
        }finally {
            connection.setAutoCommit(true);
            JdbcHelper.close(preparedStatement,connection);
        }
        return update;
    }
    public boolean  delete(int id)throws Exception{
        CourseSelection courseSelection = this.find(id);
        boolean delete=false;
        Connection connection=JdbcHelper.getConn();
        PreparedStatement preparedStatement=null;
        try{
            boolean deleteRelation = CourseSelectionDao.getInstance().delete(courseSelection,connection);
            Course course= courseSelection.getCourse();
            course.setAccumulation(course.getAccumulation()-1);
            delete =CourseDao.getInstance().update(course,connection);
        }catch (SQLException e) {
            connection.rollback();
            throw new Exception("删除课程失败");
        } finally {
            connection.setAutoCommit(true);
            JdbcHelper.close(preparedStatement,connection);
        }

        return delete;
    }

    public boolean delete(CourseSelection courseSelection,Connection connection) throws SQLException {
        return CourseSelectionDao.getInstance().delete(courseSelection,connection);
    }

}