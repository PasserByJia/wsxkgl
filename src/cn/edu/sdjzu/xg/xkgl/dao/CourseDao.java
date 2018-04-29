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
    //创建本类惟一对象
    private static CourseDao courseDao = new CourseDao();
    //构造器定义为private，“阻止”其它类创建本类的对象
    private CourseDao(){}
    //返回(而不是创建)本类惟一对象
    public static CourseDao getInstance(){
        return courseDao;
    }

    /*查找所有课程*/
    public Collection<Course> findAll()throws SQLException{
        // SQL语句：查找所有课程
        String selectSql="SELECT * FROM course";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象，如果SQL语句为多行，注意语句不同部分之间要有空格
        pstmt = conn.prepareStatement(selectSql);
        //执行预编译语句，返回结果集
        rs = pstmt.executeQuery();
        //遍历结果集放到课程集合中
        Collection<Course> courses = mapResultSetToTeacher(rs);
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        return courses;
    }
    public Collection<Course> findAll(String conditionStr)throws SQLException{
        // SQL语句：查找所有课程
        StringBuilder sqlSb= new StringBuilder("SELECT * FROM course");
        //追加SQL语句
        if(conditionStr!=null&&conditionStr.trim().length()!=0){
            sqlSb.append(" where");
            sqlSb.append(conditionStr);
        }
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        pstmt = conn.prepareStatement(sqlSb.toString());
        //执行预编译语句，返回结果集
        rs = pstmt.executeQuery();
        //遍历结果集放到课程集合中
        Collection<Course> courses = mapResultSetToTeacher(rs);
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        return courses;
    }
    /*通过老师查找课程*/
    public Collection<Course> findByTeacher(Teacher teacher)throws SQLException{
        // SQL语句：通过老师的id查找所有课程
        String selectSql="SELECT * FROM course WHERE teacher_id=?";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        pstmt = conn.prepareStatement(selectSql);
        //对预编译语句参数进行赋值
        pstmt.setInt(1,teacher.getId());
        //执行预编译语句，返回结果集
        rs = pstmt.executeQuery();
        //遍历结果集放到课程集合中
        Collection<Course> courses = mapResultSetToTeacher(rs);
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        return courses;
    }
    /*通过课程类型查找课程*/
    public  Collection<Course> findByType(Collection<CourseType> type)throws SQLException {
        //选课结果集合
        Collection<Course> courseCollection = new HashSet<Course>();
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //根据连接对象准备预编译语句
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM course WHERE courseType_id=?");
        //遍历课程类型
        for (CourseType courseType1: type) {
            //对预编译语句对象的参数赋值
            preparedStatement.setInt(1,courseType1.getId());
            //执行查询语句，返回结果集
            ResultSet rs = preparedStatement.executeQuery();
            //遍历结果集放到课程集合中
            courseCollection = mapResultSetToTeacher(rs);
        }
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        return courseCollection;
    }
    /*通过课程名查找相应课程*/
    public Course findOneByTitle(String title)throws SQLException{
        //声明课程引用
        Course course = null;
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //根据连接对象准备预编译语句
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM course WHERE title=?");
        //对预编译语句对象的参数赋值
        preparedStatement.setString(1,title);
        //执行预编译语句，返回结果集
        ResultSet rs = preparedStatement.executeQuery();
        //获得结果集，对课程对象属性赋值
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
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        return course;
    }
    /**
     *通过课程id查找一个课程
     * @param id 目标对象对应的记录的id字段值
     * @return id对象的School对象
     * @throws SQLException
     */
    public Course find(Integer id) throws SQLException{
        // SQL语句：通过课程的id查找一个课程
        String selectSql="SELECT * FROM course WHERE id=?";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备预编译语句
        pstmt = conn.prepareStatement(selectSql);
        //对预编译语句对象的参数赋值
        pstmt.setInt(1,id);
        //执行预编译语句，返回结果集
        rs = pstmt.executeQuery();
        //声明课程引用
        Course course= null;
        //获得结果集，对课程对象属性赋值
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
    /*遍历结果集，保存在课程集合中*/
    private Collection<Course> mapResultSetToTeacher(ResultSet rs) throws SQLException {
        //课程集合
        Collection<Course> courses = new TreeSet<Course>();
        //获得结果集
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
            //保存在集合中
            courses.add(course);
        }
        return courses;
    }

    /*更改课程*/
    public boolean update(Course course)throws SQLException{
        //SQL语句，更改相应课程
        String updateSql="UPDATE course SET accumulation=?,credit=?,hours=?,max=?,min=?,no=?, status=?,time=?,title=?, courseType_id=?,teacher_id=? where id=?";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        pstmt = conn.prepareStatement(updateSql);
        //对预编译语句参数进行赋值
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
        //执行预编译语句，返回受影响的行数
        int rowAffected = pstmt.executeUpdate();
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;
    }
    public boolean update(Course course,Connection connection)throws SQLException{
        //SQL语句，更改相应课程
        String updateSql="UPDATE course SET accumulation=? where id=?";
        //根据连接对象准备语句对象
        pstmt = connection.prepareStatement(updateSql);
        //对预编译语句参数进行赋值
        pstmt.setInt(1,course.getAccumulation());
        pstmt.setInt(2,course.getId());
        //执行预编译语句，返回受影响的行数
        int rowAffected = pstmt.executeUpdate();
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;
    }

    /*增加一个课程*/
    public boolean add(Course course)throws SQLException{
        //SQL语句，增加课程
        String addSql="INSERT INTO course(accumulation,credit,hours,max,min,no,status,time,title,courseType_id,teacher_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        pstmt = conn.prepareStatement(addSql, Statement.RETURN_GENERATED_KEYS);
        //对预编译语句参数进行赋值
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
        //执行预编译语句，返回受影响的行数
        int rowAffected = pstmt.executeUpdate();
        //初始化新增课程id为0
        int idOfAddedCourse = 0;
        //获取自动增长的id值
        rs = pstmt.getGeneratedKeys();
        if(rs.next()){
            //新增课程id赋值自动增长的id值
            idOfAddedCourse = rs.getInt(1);
        }
        //给课程属性赋值
        course.setId(idOfAddedCourse);
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;

    }

    /*通过id删除一个课程*/
    public boolean delete(Integer id,Connection connection)throws SQLException{
        //通过id查找课程
        Course course = this.find(id);
        //删除找到的课程
        return this.delete(course,connection);
    }

    /*删除一个课程*/
    public boolean delete(Course course,Connection connection)throws SQLException {
        //SQL语句，通过id删除一个课程
        String deleteSql="DELETE FROM course WHERE id=?";
        //根据连接对象准备语句对象
        pstmt = connection.prepareStatement(deleteSql);
        //对预编译语句参数进行赋值
        pstmt.setInt(1, course.getId());
        //执行预编译语句，返回受影响行数
        int rowAffected = pstmt.executeUpdate();
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;
    }
    /*通过课程名查找课程集合*/
    public Collection<Course> findByTitle(String title)throws SQLException{
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //根据连接对象准备预编译语句
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM course WHERE title=?");
        //对预编译语句参数进行赋值
        preparedStatement.setString(1,title);
        //执行预编译语句，返回结果集
        ResultSet rs = preparedStatement.executeQuery();
        //遍历结果集放到课程集合中
        Collection<Course> courseCollection = mapResultSetToTeacher(rs);
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        return courseCollection;

    }

}
