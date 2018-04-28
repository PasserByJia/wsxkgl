package cn.edu.sdjzu.xg.xkgl.service;



import cn.edu.sdjzu.xg.xkgl.dao.CourseTypeDao;
import cn.edu.sdjzu.xg.xkgl.domain.CourseType;

import java.sql.SQLException;
import java.util.Collection;

public class CourseTypeService {
    //私有构造器
    private CourseTypeService(){}
    private static CourseTypeService courseTypeService;
    //返回实例对象
    public static CourseTypeService getInstance(){
        if(courseTypeService==null){
            courseTypeService=new CourseTypeService();
        }
            return CourseTypeService.courseTypeService;
        }
        //找到所有课程类型
    public Collection<CourseType> findAll() throws SQLException {
        return CourseTypeDao.getInstance().findAll();
    }
    //增加课程类型
    public boolean addCourseType(CourseType courseType) throws SQLException{
        return CourseTypeDao.getInstance().add(courseType);
    }
    //更新课程信息
    public boolean updateCourseType(CourseType courseType) throws SQLException{
        return CourseTypeDao.getInstance().update(courseType);
    }
    // 根据传入的参数，找到对应的课程类型
    public CourseType findCourseType(Integer id) throws SQLException{
        return CourseTypeDao.getInstance().find(id);
    }
    public Collection<CourseType> findByDes(String des)throws SQLException{
        return CourseTypeDao.getInstance().findByDes(des);
    }
    //根据id,删除课程
    public boolean deleteCourseTypeById(int id) throws SQLException{
        return CourseTypeDao.getInstance().delete(id);
    }
    //根据courseType,删除课程
    public boolean deleteCourseType(CourseType courseType) throws SQLException{
        return CourseTypeDao.getInstance().delete(courseType);
    }
}
