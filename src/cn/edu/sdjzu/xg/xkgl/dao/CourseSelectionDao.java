package cn.edu.sdjzu.xg.xkgl.dao;


import cn.edu.sdjzu.xg.xkgl.domain.Course;
import cn.edu.sdjzu.xg.xkgl.domain.CourseSelection;
import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.service.StudentService;
import util.JdbcHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public final class CourseSelectionDao {
    //声明数据库各个对象的引用
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static PreparedStatement preparedStatement = null;
    //创建本类惟一对象
    private static CourseSelectionDao courseSelectionDao = new CourseSelectionDao();
    //构造器定义为private，“阻止”其它类创建本类的对象
    private CourseSelectionDao(){}
    //返回(而不是创建)本类惟一对象
    public static CourseSelectionDao getInstance(){
        return courseSelectionDao;
    }

    /*通过id查找相应课程*/
    public CourseSelection find(Integer id)throws SQLException {
        // SQL语句：通过id查找相应课程
        String selectSql="SELECT * FROM courseSelection WHERE id=?";
        //获取数据库连接对象
        connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        preparedStatement = connection.prepareStatement(selectSql);
        //对预编译语句参数进行赋值
        preparedStatement.setInt(1,id);
        //执行预编译语句，返回结果集
        resultSet = preparedStatement.executeQuery();
        //声明选课结果引用
        CourseSelection courseSelection = null;
        //获得结果集，对选课结果对象属性赋值
        while(resultSet.next()){
            int course_id =  resultSet.getInt("course_id");
            int student_id = resultSet.getInt("student_id");
            Course course = CourseDao.getInstance().find(course_id);
            Student student = StudentDao.getInstance().find(student_id);
            courseSelection = new CourseSelection(id,course,student);
        }
        return courseSelection;
    }

    /*查找所有选课结果*/
    public Collection<CourseSelection> findAll() throws SQLException{
        // SQL语句：查找所有选课结果
        String selectSql="SELECT * FROM courseSelection";
        //获取数据库连接对象
        connection = JdbcHelper.getConn();
        //创建用于执行静态SQL 语句并返回它所生成结果的对象
        statement=connection.createStatement();
        //执行预编译语句，返回结果集
        resultSet=statement.executeQuery(selectSql);
        //遍历结果集放到选课结果集合中
        Collection<CourseSelection> courseSelections = mapResultSetToTeacher(resultSet);
        //关闭资源
        JdbcHelper.close(resultSet,statement,connection);
        return courseSelections;
    }
    /*通过老师id查找选课结果*/
    public Collection<CourseSelection> findByStudentId(Integer student_id) throws SQLException{
        // SQL语句：通过老师id查找所有选课结果
        String selectSql="SELECT * FROM courseSelection WHERE student_id=?";
        //获取数据库连接对象
        connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        preparedStatement = connection.prepareStatement(selectSql);
        //对预编译语句对象的参数赋值
        preparedStatement.setInt(1,student_id);
        //执行预编译语句，返回结果集
        resultSet = preparedStatement.executeQuery();
        //遍历结果集放到课程集合中
        Collection<CourseSelection> courseSelections = mapResultSetToTeacher(resultSet);
        return courseSelections;
    }

    /*通过课程id查找学生*/
    public List<Student> findStudentByCourseId(Integer course_id) throws SQLException{
        // SQL语句：通过课程id查找所有选课结果
        String selectSql="SELECT * FROM courseselection WHERE course_id=?";
        //获取数据库连接对象
        connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        preparedStatement = connection.prepareStatement(selectSql);
        //对预编译语句对象的参数赋值
        preparedStatement.setInt(1,course_id);
        //执行预编译语句，返回结果集
        resultSet = preparedStatement.executeQuery();
        //学生集合
        List<Student> students  = new ArrayList<Student>();
        //获得结果集，通过id查找学生保存在集合中
        while(resultSet.next()){
            int student_id =resultSet.getInt("student_id");
            students.add(StudentService.getInstance().find(student_id));
        }
        return students;
    }

    /*通过学生用户名查找选课结果*/
    public  Collection<CourseSelection> findByStudentUsername(String username)throws SQLException{
        //通过用户名获得学生
        Student student = StudentService.getInstance().findByUsername(username);
        //获得学生id
        int student_id = student.getId();
        //通过学生id查找学生
        return findByStudentId(student_id);
    }

    /*通过课程名查找选课结果*/
    public Collection<CourseSelection> findByCourseTitle(int courseid) throws SQLException{
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //根据连接对象准备预编译语句
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM courseselection WHERE course_id=?");
        //对预编译语句对象的参数赋值
        preparedStatement.setInt(1,courseid);
        //执行预编译语句，返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        //遍历结果集放到课程集合中
        Collection<CourseSelection> courseSelectionCollection = mapResultSetToTeacher(resultSet);
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        return courseSelectionCollection;
    }
    /*遍历结果集，保存在选课结果集合中*/
    private Collection<CourseSelection> mapResultSetToTeacher(ResultSet resultSet) throws SQLException {
        //选课结果集合
        Collection<CourseSelection> courseSelectionCollection  = new TreeSet<CourseSelection>();
        //获得结果集
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            int student_id = resultSet.getInt("student_id");
            int course_id = resultSet.getInt("course_id");
            Course course = CourseDao.getInstance().find(course_id);
            Student student = StudentDao.getInstance().find(student_id);
            CourseSelection courseSelection = new CourseSelection(id,course,student);
            //保存在集合中
            courseSelectionCollection.add(courseSelection);
        }
        return courseSelectionCollection;
    }

    /*更改选课结果*/
    public Boolean update(CourseSelection courseSelection) throws SQLException{
        //SQL语句，更改相应选课结果
        String updateSql="UPDATE courseSelection SET course_id=?,student_id=? WHERE id=?";
        //获取数据库连接对象
        connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        preparedStatement=connection.prepareStatement(updateSql);
        //对预编译语句参数进行赋值
        preparedStatement.setInt(1, courseSelection.getCourse().getId());
        preparedStatement.setInt(2, courseSelection.getStudent().getId());
        preparedStatement.setInt(3,courseSelection.getId());
        //执行预编译语句，返回受影响的行数
        int rowAffected = preparedStatement.executeUpdate();
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;
    }

    /*增加一个选课结果*/
    public Boolean add(CourseSelection courseSelection) throws SQLException{
        //SQL语句，增加一个选课结果
        String addSql = "INSERT INTO courseSelection(course_id,sutdent_id) VALUES (?,?)";
        //获取数据库连接对象
        connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        preparedStatement = connection.prepareStatement(addSql);
        //对预编译语句参数进行赋值
        preparedStatement.setInt(1,courseSelection.getCourse().getId());
        preparedStatement.setInt(2,courseSelection.getStudent().getId());
        //执行预编译语句，返回受影响的行数
        int rowAffected = preparedStatement.executeUpdate();
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;
    }
    public Boolean add(CourseSelection courseSelection,Connection connection) throws SQLException{
        //SQL语句，增加一个选课结果
        String addSql = "INSERT INTO courseSelection(course_id,student_id) VALUES (?,?)";
        //根据连接对象准备语句对象
        preparedStatement = connection.prepareStatement(addSql);
        //对预编译语句参数进行赋值
        preparedStatement.setInt(1,courseSelection.getCourse().getId());
        preparedStatement.setInt(2,courseSelection.getStudent().getId());
        //执行预编译语句，返回受影响的行数
        int rowAffected = preparedStatement.executeUpdate();
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;
    }

    /*通过id删除一个选课结果*/
    public boolean delete(Integer id)throws SQLException{
        //通过id查找相应选课结果
        CourseSelection courseSelection = this.find(id);
        //删除一个选课结果
        return this.delete(courseSelection);
    }
    public boolean delete(Integer id,Connection connection)throws SQLException{
        //通过id查找相应选课结果
        CourseSelection courseSelection = this.find(id);
        //删除一个选课结果
        return this.delete(courseSelection,connection);
    }
    /*删除一个选课结果*/
    private boolean delete(CourseSelection courseSelection) throws SQLException{
        //SQL语句，删除一个选课结果
        String deleteSql="DELETE FROM courseSelection WHERE id=?";
        //获取数据库连接对象
        connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        preparedStatement = connection.prepareStatement(deleteSql);
        //对预编译语句参数进行赋值
        preparedStatement.setInt(1, courseSelection.getId());
        //执行预编译语句，返回受影响的行数
        int rowAffected = preparedStatement.executeUpdate();
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;
    }
    public boolean delete(CourseSelection courseSelection, Connection connection) throws SQLException{
        //SQL语句，删除一个选课结果
        String deleteSql="DELETE FROM courseSelection WHERE id=?";
        //根据连接对象准备语句对象
        preparedStatement = connection.prepareStatement(deleteSql);
        //对预编译语句参数进行赋值
        preparedStatement.setInt(1,courseSelection.getId());
        //执行预编译语句，返回受影响的行数
        int rowAffected = preparedStatement.executeUpdate();
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;
    }

}
