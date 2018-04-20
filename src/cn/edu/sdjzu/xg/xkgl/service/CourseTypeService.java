package cn.edu.sdjzu.xg.xkgl.service;



import cn.edu.sdjzu.xg.xkgl.dao.CourseTypeDao;
import cn.edu.sdjzu.xg.xkgl.domain.CourseType;

import java.sql.SQLException;
import java.util.Collection;

public class CourseTypeService {
    private CourseTypeService(){}
    private static CourseTypeService courseTypeService;
    public static CourseTypeService getInstance(){
        if(courseTypeService==null){
            courseTypeService=new CourseTypeService();
        }
            return CourseTypeService.courseTypeService;
        }
    public Collection<CourseType> findAll() throws SQLException {
        return CourseTypeDao.getInstance().findAll();
    }
    public boolean addCourseType(CourseType courseType) throws SQLException{
        return CourseTypeDao.getInstance().add(courseType);
    }
    public boolean updateCourseType(CourseType courseType) throws SQLException{
        return CourseTypeDao.getInstance().update(courseType);
    }
    public CourseType findCourseType(Integer id) throws SQLException{
        return CourseTypeDao.getInstance().find(id);
    }
    public Collection<CourseType> findByDes(String des)throws SQLException{
        return CourseTypeDao.getInstance().findByDes(des);
    }
    public boolean deleteCourseTypeById(int id) throws SQLException{
        return CourseTypeDao.getInstance().delete(id);
    }
    public boolean deleteCourseType(CourseType courseType) throws SQLException{
        return CourseTypeDao.getInstance().delete(courseType);
    }
}
