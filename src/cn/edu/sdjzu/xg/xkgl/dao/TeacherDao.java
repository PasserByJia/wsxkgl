package cn.edu.sdjzu.xg.xkgl.dao;

import cn.edu.sdjzu.xg.xkgl.domain.Teacher;
import util.JdbcHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.TreeSet;

public class TeacherDao {
    //声明本类惟一对象
    private static TeacherDao teacherDao = new TeacherDao();
    //声明数据库的各个对象的引用
    private static Connection conn = null;
    private static ResultSet rs = null;
    private static PreparedStatement pstmt = null;
    //构造器定义为private，“阻止”其它类创建本类的对象
    private TeacherDao(){}
    //返回本类的惟一对象
    public static TeacherDao getInstance(){return  teacherDao;}

    /*查找所有老师*/
    public Collection<Teacher> findAll()throws SQLException{
        //SQL查询语句
        String selectSql="SELECT * FROM teacher";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        pstmt = conn.prepareStatement(selectSql);
        //执行预编译语句，返回结果集
        rs = pstmt.executeQuery();
        //遍历结果集放到老师集合中
        Collection<Teacher> teachers = mapResultSetToTeacher(rs);
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        return teachers;
    }
    public Collection<Teacher> findAll(String conditionStr)throws SQLException{
        //SQL查询语句
        StringBuilder sqlSb= new StringBuilder("SELECT * FROM teacher");
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
        //遍历结果集放到老师集合中
        Collection<Teacher> teachers = mapResultSetToTeacher(rs);
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        return teachers;
    }
    /*遍历结果集，保存在老师集合中*/
    private Collection<Teacher> mapResultSetToTeacher(ResultSet rs) throws SQLException {
        //老师集合
        Collection<Teacher> teachers  = new TreeSet<Teacher>();
        //获得结果集
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String no = rs.getString("no");
            String password = rs.getString("password");
            String sex = rs.getString("sex");
            String username = rs.getString("username");
            int proTitle_id = rs.getInt("proTitle_id");
            Teacher teacher = new Teacher(id,username,password,name,no,sex, ProTitleDao.getInstance().findProTitle(proTitle_id));
            //保存在集合中
            teachers.add(teacher);
        }
        return teachers;
    }
    /*通过no查找老师*/
    public Teacher findByNo(String teacher_no) throws SQLException{
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        pstmt = conn.prepareStatement("SELECT * FROM teacher WHERE no =?");
        //对预编译语句对象的参数赋值
        pstmt.setString(1,teacher_no);
        //执行预编译语句，返回结果集
        rs = pstmt.executeQuery();
        //声明老师引用
        Teacher teacher = null;
        //获得结果集，对老师对象属性赋值
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String password = rs.getString("password");
            String sex = rs.getString("sex");
            String username = rs.getString("username");
            int proTitle_id = rs.getInt("proTitle_id");
            teacher = new Teacher(id,username,password,name,teacher_no,sex, ProTitleDao.getInstance().findProTitle(proTitle_id));
        }
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        return teacher;
    }
    /*通过id查找老师*/
    public Teacher find(Integer id)throws SQLException {
        //SQL查询语句
        String selectSql="SELECT * FROM teacher WHERE id =?";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        pstmt = conn.prepareStatement(selectSql);
        //对预编译语句对象的参数赋值
        pstmt.setInt(1,id);
        //执行预编译语句，返回结果集
        rs = pstmt.executeQuery();
        //声明老师引用
        Teacher teacher = null;
        //获得结果集，对老师对象属性赋值
        while (rs.next()) {
            String name = rs.getString("name");
            String no = rs.getString("no");
            String password = rs.getString("password");
            String sex = rs.getString("sex");
            String username = rs.getString("username");
            int proTitle_id = rs.getInt("proTitle_id");
            teacher = new Teacher(id,username,password,name,no,sex, ProTitleDao.getInstance().findProTitle(proTitle_id));
        }
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        return teacher;
    }

    /*更改老师*/
    public Boolean update(Teacher teacher)throws SQLException{
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        pstmt = conn.prepareStatement("UPDATE teacher " +
                        "SET name=?," +
                        "no=?," +
                        "password=?," +
                        "sex=?," +
                        "username=?," +
                        "proTitle_id=?" +
                        " WHERE id=?");
        //对预编译语句参数进行赋值
        pstmt.setString(1,teacher.getName());
        pstmt.setString(2,teacher.getNo());
        pstmt.setString(3,teacher.getPassword());
        pstmt.setString(4,teacher.getSex());
        pstmt.setString(5,teacher.getUsername());
        pstmt.setInt(6,teacher.getProTitle().getId());
        pstmt.setInt(7,teacher.getId());
        //执行预编译语句，返回受影响的行数
        int affectedRowNum = pstmt.executeUpdate();
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        //如果受影响行数大于0，则返回true，否则返回false
        return affectedRowNum >0;
    }
    /*增加一个老师*/
    public Boolean add(Teacher teacher)throws SQLException{
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        pstmt= conn.prepareStatement("insert into teacher " +
                        " (name, no, password, sex, username, proTitle_id)" +
                        " values(?,?,?,?,?,?)");
        //对预编译语句对象的参数赋值
        pstmt.setString(1,teacher.getName());
        pstmt.setString(2,teacher.getNo());
        pstmt.setString(3,teacher.getPassword());
        pstmt.setString(4,teacher.getSex());
        pstmt.setString(5,teacher.getUsername());
        pstmt.setInt(6,teacher.proTitle.getId());
        //执行预编译语句，返回影响的行数
        int rowAffected = pstmt.executeUpdate();
        //关闭资源
        JdbcHelper.close(pstmt, conn);
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected > 0;
    }

    /*删除一个老师*/
    public boolean delete(Teacher teacher, Connection connection)throws SQLException{
        //SQL语句，通过id删除一个学生
        String deleteSql="DELETE FROM teacher WHERE id=?";
        //根据连接对象准备语句对象
        pstmt = connection.prepareStatement(deleteSql);
        //对预编译语句参数进行赋值
        pstmt.setInt(1,teacher.getId());
        //执行预编译语句，返回受影响行数
        int rowAffected = pstmt.executeUpdate();
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;
    }
    public boolean delete(Integer id)throws SQLException{
        Teacher teacher = this.find(id);
        return this.delete(teacher);
    }
    private boolean delete(Teacher teacher) throws SQLException{
        //SQL语句，通过id删除一个学生
        String deleteSql="DELETE FROM teacher WHERE id=?";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        pstmt = conn.prepareStatement(deleteSql);
        //对预编译语句参数进行赋值
        pstmt.setInt(1, teacher.getId());
        //执行预编译语句，返回受影响行数
        int rowAffected = pstmt.executeUpdate();
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        //如果受影响行数大于0，则返回true，否则返回false
        return rowAffected>0;
    }
}
