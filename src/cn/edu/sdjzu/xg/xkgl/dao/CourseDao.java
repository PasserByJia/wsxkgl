package cn.edu.sdjzu.xg.xkgl.dao;



import cn.edu.sdjzu.xg.xkgl.domain.*;
import cn.edu.sdjzu.xg.xkgl.service.CourseTypeService;
import cn.edu.sdjzu.xg.xkgl.service.TeacherService;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CourseDao {
    //声明数据库各个对象的引用
    private static Connection conn = null;
    private static ResultSet rs = null;
    private static PreparedStatement pstmt = null;
    private static CourseDao courseDao = new CourseDao();
    private CourseDao(){}
    public static CourseDao getInstance(){
        return courseDao;
    }

    public Collection<Course> findAll()throws SQLException{
        String selectSql="SELECT * FROM course";
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(selectSql);
        rs = pstmt.executeQuery();
        Collection<Course> courses = mapResultSetToTeacher(rs);
        JdbcHelper.close(pstmt,conn);
        return courses;
    }
    public Collection<Course> findAll(String conditionStr)throws SQLException{
        StringBuilder sqlSb= new StringBuilder("SELECT * FROM course");
        if(conditionStr!=null&&conditionStr.trim().length()!=0){
            sqlSb.append(" where");
            sqlSb.append(conditionStr);
        }
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(sqlSb.toString());
        rs = pstmt.executeQuery();
        Collection<Course> courses = mapResultSetToTeacher(rs);
        JdbcHelper.close(pstmt,conn);
        return courses;
    }
    public Collection<Course> findByTeacher(Teacher teacher)throws SQLException{
        String selectSql="SELECT * FROM course WHERE teacher_id=?";
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(selectSql);
        pstmt.setInt(1,teacher.getId());
        rs = pstmt.executeQuery();
        Collection<Course> courses = mapResultSetToTeacher(rs);
        JdbcHelper.close(pstmt,conn);
        return courses;
    }
    public  Collection<Course> findByType(Collection<CourseType> type)throws SQLException {
        Collection<Course> courseCollection = new HashSet<Course>();
        Connection connection = JdbcHelper.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM course WHERE courseType_id=?");
        //对预编译语句对象的参数赋值
        for (CourseType courseType1: type) {
            preparedStatement.setInt(1,courseType1.getId());
            ResultSet rs = preparedStatement.executeQuery();
            courseCollection = mapResultSetToTeacher(rs);
        }
        JdbcHelper.close(preparedStatement,connection);
        return courseCollection;
    }
    public Course findOneByTitle(String title)throws SQLException{
        Course course = null;
        Connection connection = JdbcHelper.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM course WHERE title=?");
        preparedStatement.setString(1,title);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            int teacherId = rs.getInt("teacher_id");
            Teacher teacher = TeacherService.getInstance().find(teacherId);
            int courseTypeID = rs.getInt("courseType_id");
            CourseType courseType = CourseTypeService.getInstance().findCourseType(courseTypeID);
            int id = rs.getInt("id");
            String no = rs.getString("no");
            int max = rs.getInt("max");
            int min = rs.getInt("min");
            int accumulation = rs.getInt("accumulation");
            int hours = rs.getInt("hours");
            String time = rs.getString("time");
            int credit = rs.getInt("credit");
            boolean status =  rs.getBoolean("status");
            course = new Course(id,no,title,max,min,accumulation,hours,time,credit,status,teacher,courseType);
        }
        JdbcHelper.close(preparedStatement,connection);
        return course;
    }
    /**
     *
     * @param id 目标对象对应的记录的id字段值
     * @return id对象的School对象
     * @throws SQLException
     */
    public Course find(Integer id) throws SQLException{
        String selectSql="SELECT * FROM course WHERE id=?";
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(selectSql);
        pstmt.setInt(1,id);
        rs = pstmt.executeQuery();
        Course course= null;
        while(rs.next()) {
            CourseType courseType = CourseTypeDao.getInstance().find(rs.getInt("courseType_id"));
            Teacher teacher = TeacherDao.getInstance().find(rs.getInt("teacher_id"));
            course = new Course(rs.getInt("id"),
                    rs.getString("no"),
                    rs.getString("title"),
                    rs.getInt("max"),
                    rs.getInt("min"),
                    rs.getInt("accumulation"),
                    rs.getInt("hours")
                    ,rs.getString("time"),
                    rs.getInt("credit"),
                    rs.getBoolean("status")
                    ,teacher,
                    courseType);
        }
        return course;
    }
    private Collection<Course> mapResultSetToTeacher(ResultSet rs) throws SQLException {
        Collection<Course> courses = new TreeSet<Course>();
        while(rs.next()) {
            CourseType courseType = CourseTypeDao.getInstance().find(rs.getInt("courseType_id"));
            Teacher teacher = TeacherDao.getInstance().find(rs.getInt("teacher_id"));
            Course course = new Course(rs.getInt("id"),
                rs.getString("no"),
                rs.getString("title"),
                rs.getInt("max"),
                rs.getInt("min"),
                rs.getInt("accumulation"),
                rs.getInt("hours")
                ,rs.getString("time"),
                rs.getInt("credit"),
                rs.getBoolean("status")
                ,teacher,
                courseType);
            courses.add(course);
        }
        return courses;
    }

    public boolean update(Course course)throws SQLException{
        String updateSql="UPDATE course SET accumulation=?,credit=?,hours=?,max=?,min=?,no=?, status=?,time=?,title=?, courseType_id=?,teacher_id=? where id=?";
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(updateSql);
        pstmt.setInt(1,course.getAccumulation());
        pstmt.setInt(2,course.getCredit());
        pstmt.setInt(3,course.getHours());
        pstmt.setInt(4,course.getMax());
        pstmt.setInt(5,course.getMin());
        pstmt.setString(6,course.getNo());
        pstmt.setBoolean(7,course.isStatus());
        pstmt.setString(8,course.getTime());
        pstmt.setString(9,course.getTitle());
        pstmt.setInt(10,course.getCourseType().getId());
        pstmt.setInt(11,course.getTeacher().getId());
        pstmt.setInt(12,course.getId());
        int rowAffected = pstmt.executeUpdate();
        JdbcHelper.close(pstmt,conn);
        return rowAffected>0;
    }
    public boolean update(Course course,Connection connection)throws SQLException{
        String updateSql="UPDATE course SET accumulation=? where id=?";
        pstmt = connection.prepareStatement(updateSql);
        pstmt.setInt(1,course.getAccumulation());
        pstmt.setInt(2,course.getId());
        int rowAffected = pstmt.executeUpdate();
        return rowAffected>0;
    }

    public boolean add(Course course)throws SQLException{
        String addSql="INSERT INTO course(accumulation,credit,hours,max,min,no,status,time,title,courseType_id,teacher_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(addSql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1,course.getAccumulation());
        pstmt.setInt(2,course.getCredit());
        pstmt.setInt(3,course.getHours());
        pstmt.setInt(4,course.getMax());
        pstmt.setInt(5,course.getMin());
        pstmt.setString(6,course.getNo());
        pstmt.setBoolean(7,course.isStatus());
        pstmt.setString(8,course.getTime());
        pstmt.setString(9,course.getTitle());
        pstmt.setInt(10,course.getCourseType().getId());
        pstmt.setInt(11,course.getTeacher().getId());
        int rowAffected = pstmt.executeUpdate();
        int idOfAddedCourse = 0;
        rs = pstmt.getGeneratedKeys();
        if(rs.next()){
            idOfAddedCourse = rs.getInt(1);
        }
        course.setId(idOfAddedCourse);
        JdbcHelper.close(pstmt,conn);
        return rowAffected>0;

    }

    public boolean delete(Integer id,Connection connection)throws SQLException{
        Course course = this.find(id);
        return this.delete(course,connection);
    }



    public boolean delete(Course course,Connection connection)throws SQLException {
        String deleteSql="DELETE FROM course WHERE id=?";
        pstmt = connection.prepareStatement(deleteSql);
        pstmt.setInt(1, course.getId());
        int rowAffected = pstmt.executeUpdate();
        return rowAffected>0;
    }
    public Collection<Course> findByTitle(String title)throws SQLException{
        Connection connection = JdbcHelper.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM course WHERE title=?");
        preparedStatement.setString(1,title);
        ResultSet rs = preparedStatement.executeQuery();
        Collection<Course> courseCollection = mapResultSetToTeacher(rs);
        JdbcHelper.close(preparedStatement,connection);
        return courseCollection;

    }

}
