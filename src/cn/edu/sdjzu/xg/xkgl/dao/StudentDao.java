package cn.edu.sdjzu.xg.xkgl.dao;

import cn.edu.sdjzu.xg.xkgl.domain.Student;
import cn.edu.sdjzu.xg.xkgl.domain.Teacher;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public final class StudentDao {
    //声明数据库的各个对象的引用
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static PreparedStatement preparedStatement = null;
    //声明studentDao对象引用
    private static StudentDao studentDao = new StudentDao();
    //声明学生集合引用
    private static Collection<Student> students = new TreeSet<Student>();
    //构造器定义为private，“阻止”其它类创建本类的对象
    private StudentDao(){}
    //返回本类的惟一对象
    public static StudentDao getInstance(){
        return studentDao;
    }

    /*通过id查找一个学生*/
    public Student find(Integer id)throws SQLException{
        // SQL语句：通过id查找一个学生
        String selectSql="SELECT * FROM student WHERE id=?";
        //获取数据库连接对象
        connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        preparedStatement = connection.prepareStatement(selectSql);
        //对预编译语句参数进行赋值
        preparedStatement.setInt(1,id);
        //执行预编译语句，返回结果集
        resultSet = preparedStatement.executeQuery();
        //声明学生引用
        Student student = null;
        //获得结果集，对学生对象属性赋值
        while (resultSet.next()) {
            String no = resultSet.getString("no");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String sex = resultSet.getString("sex");
            String username = resultSet.getString("username");
            student = new Student(id,username,password,sex,no,name);
        }
        return student;
    }

    /*查找所有学生*/
    public Collection<Student> findAll() throws SQLException{
        //SQL语句：查找所有学生
        String selectSql="SELECT * FROM student";
        //获取数据库连接对象
        connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        statement=connection.createStatement();
        //执行预编译语句，返回结果集
        resultSet=statement.executeQuery(selectSql);
        //遍历结果集放到学生集合中
        Collection<Student> students = mapResultSetToTeacher(resultSet);
        //关闭资源
        JdbcHelper.close(resultSet,statement,connection);
        return students;
    }
    /*查找所有学生*/
    public Collection<Student> findAll(String conditionStr)throws SQLException{
        //SQL语句：查找所有学生
        StringBuilder sqlSb= new StringBuilder("SELECT * FROM student");
        //追加SQL语句
        if(conditionStr!=null&&conditionStr.trim().length()!=0){
            sqlSb.append(" where");
            sqlSb.append(conditionStr);
        }
        //获取数据库连接对象
        connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        preparedStatement = connection.prepareStatement(sqlSb.toString());
        //执行预编译语句，返回结果集
        resultSet = preparedStatement.executeQuery();
        //遍历结果集放到学生集合中
        Collection<Student> students = mapResultSetToTeacher(resultSet);
        //关闭资源
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return students;
    }
    /*遍历结果集，保存在学生集合中*/
    private Collection<Student> mapResultSetToTeacher(ResultSet resultSet) throws SQLException {
        //学生集合
        Collection<Student> students  = new TreeSet<Student>();
        //获得结果集
        while(resultSet.next()){
            Student student = new Student(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("sex"),
                    resultSet.getString("no"),
                    resultSet.getString("name"));
            //保存在集合中
            students.add(student);
        }
        return students;
    }
    /*通过no查找学生*/
    public  Student findByUsername(String no)throws SQLException{
        //SQL语句：通过no查找学生
        String selectSql="SELECT * FROM student WHERE no=?";
        //获取数据库连接对象
        connection = JdbcHelper.getConn();
        //根据连接对象准备预编译语句
        preparedStatement = connection.prepareStatement(selectSql);
        //对预编译语句对象的参数赋值
        preparedStatement.setString(1,no);
        //执行预编译语句，返回结果集
        resultSet = preparedStatement.executeQuery();
        //声明学生引用
        Student student = null;
        //获得结果集，对学生对象属性赋值
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String sex = resultSet.getString("sex");
            String username = resultSet.getString("username");
            student = new Student(id,username,password,sex,no,name);
        }
        return student;
    }
    /*更改学生*/
    public Boolean update(Student student) throws SQLException{
        //SQL语句，更改相应学生
        String updateSql="UPDATE student SET name=?,no=?,password=?,sex=?,username=? WHERE id=?";
        //获取数据库连接对象
        connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        preparedStatement=connection.prepareStatement(updateSql);
        //对预编译语句参数进行赋值
        preparedStatement.setString(1,student.getName());
        preparedStatement.setString(2,student.getNo());
        preparedStatement.setString(3,student.getPassword());
        preparedStatement.setString(4,student.getSex());
        preparedStatement.setString(5,student.getUsername());
        preparedStatement.setInt(6,student.getId());
        //执行预编译语句，返回受影响的行数
        int rowAffected = preparedStatement.executeUpdate();
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;
    }

    /*增加一个学生*/
    public Boolean add(Student student) throws SQLException{
        //SQL语句，增加学生
        String addSql = "INSERT INTO student(name,no,password,sex,username) VALUES (?,?,?,?,?)";
        //获取数据库连接对象
        connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        preparedStatement = connection.prepareStatement(addSql);
        //对预编译语句参数进行赋值
        preparedStatement.setString(1,student.getName());
        preparedStatement.setString(2,student.getNo());
        preparedStatement.setString(3,student.getPassword());
        preparedStatement.setString(4,student.getSex());
        preparedStatement.setString(5,student.getUsername());
        //执行预编译语句，返回受影响的行数
        int rowAffected = preparedStatement.executeUpdate();
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;
    }

    /*通过id删除一个学生*/
    public boolean delete(Integer id)throws SQLException{
        //通过id查找学生
        Student student = this.find(id);
        //删除找到的学生
        return this.delete(student);
    }

    /*删除一个学生*/
    private boolean delete(Student student) throws SQLException{
        //SQL语句，通过id删除一个学生
        String deleteSql="DELETE FROM student WHERE id=?";
        //获取数据库连接对象
        connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        preparedStatement = connection.prepareStatement(deleteSql);
        //对预编译语句参数进行赋值
        preparedStatement.setInt(1, student.getId());
        //执行预编译语句，返回受影响行数
        int rowAffected = preparedStatement.executeUpdate();
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;
    }

    /*删除学生*/
    public boolean delete(Student student, Connection connection) throws SQLException{
        //SQL语句，通过id删除一个学生
        String deleteSql="DELETE FROM student WHERE id=?";
        //根据连接对象准备语句对象
        preparedStatement = connection.prepareStatement(deleteSql);
        //对预编译语句参数进行赋值
        preparedStatement.setInt(1,student.getId());
        //执行预编译语句，返回受影响行数
        int rowAffected = preparedStatement.executeUpdate();
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;
    }
}
