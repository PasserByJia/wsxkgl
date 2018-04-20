package cn.edu.sdjzu.xg.xkgl.dao;


import cn.edu.sdjzu.xg.xkgl.domain.EduAdmin;
import cn.edu.sdjzu.xg.xkgl.domain.Student;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public class EduAdminDao {
    //声明数据库的各个对象
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet rs = null;
    private static PreparedStatement pstmt = null;
    //声明userDao对象引用
    private static EduAdminDao eduAdminDao = null;
    //返回(不是创建)
    public static EduAdminDao getInstance() {
        if(EduAdminDao.eduAdminDao==null){
            EduAdminDao.eduAdminDao = new EduAdminDao();
        }
        return EduAdminDao.eduAdminDao;
    }
    public Collection<EduAdmin> findAll() throws SQLException {

        //sql语句查询所有的课题
        String selectSql = "SELECT * FROM eduadmin";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象，如果SQL语句为多行，注意语句不同部分之间要有空格
        statement = conn.createStatement();
        //执行查询语句，返回结果集
        rs = statement.executeQuery(selectSql);
        //遍历结果集放到集合中
        Collection<EduAdmin> eduAdmins = mapResultSetToTeacher(rs);
        JdbcHelper.close(statement,conn);
        return eduAdmins;
    }
    public Collection<EduAdmin> findAll(String conditionStr)throws SQLException{
        StringBuilder sqlSb= new StringBuilder("SELECT * FROM eduadmin");
        if(conditionStr!=null&&conditionStr.trim().length()!=0){
            sqlSb.append(" where");
            sqlSb.append(conditionStr);
        }
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement(sqlSb.toString());
        rs = pstmt.executeQuery();
        Collection<EduAdmin> eduAdmins = mapResultSetToTeacher(rs);
        JdbcHelper.close(pstmt,conn);
        return eduAdmins;
    }
    private Collection<EduAdmin> mapResultSetToTeacher(ResultSet rs) throws SQLException {
        Collection<EduAdmin> eduAdmins  = new TreeSet<EduAdmin>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String no = rs.getString("no");
            String password = rs.getString("password");
            String username = rs.getString("username");
            String sex = rs.getString("sex");
            EduAdmin eduAdmin = new EduAdmin(id,password,username,name,no,sex);
            eduAdmins.add(eduAdmin);
        }
        return eduAdmins;
    }
    public EduAdmin find(Integer id) throws SQLException{
        String addSql= "SELECT * FROM eduadmin WHERE id=?";
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象，如果SQL语句为多行，注意语句不同部分之间要有空格
        pstmt = conn.prepareStatement(addSql);
        //对预编译语句对象的参数赋值
        pstmt.setInt(1,id);
        rs = pstmt.executeQuery();
        EduAdmin eduAdmin = null;
        while (rs.next()) {
            String name = rs.getString("name");
            String no = rs.getString("no");
            String password = rs.getString("password");
            String username = rs.getString("username");
            String sex = rs.getString("sex");
            eduAdmin = new EduAdmin(id,password,username,name,no,sex);
        }
        JdbcHelper.close(pstmt,conn);
        return eduAdmin;
    }
    public boolean add(EduAdmin eduAdmin) throws SQLException{
        //sql插入语句
        String addSql="INSERT INTO eduadmin("
                +"name,"
                +"no,"
                +"password,"
                +"username,"
                +"sex)"
                +"VALUES (?,?,?,?,?)";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象，如果SQL语句为多行，注意语句不同部分之间要有空格
        pstmt = conn.prepareStatement(addSql);
        //对预编译语句对象的参数赋值
        pstmt.setString(1,eduAdmin.getName());
        pstmt.setString(2,eduAdmin.getNo()+"");
        pstmt.setString(3,eduAdmin.getPassword()+"");
        pstmt.setString(4,eduAdmin.getUsername()+"");
        pstmt.setString(5,eduAdmin.getSex()+"");
        //执行插入语句，返回结果集
        int rowAffected = pstmt.executeUpdate();
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        return rowAffected>0;
    }
    public boolean update(EduAdmin eduAdmin) throws SQLException{
        //sql更新语句
        String updateSql="UPDATE eduadmin SET "
                +"name=?,"
                +"no=?,"
                +"password=?,"
                +"username=?,"
                +"sex=?"
                + "where id=?";
        //获取数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象，如果SQL语句为多行，注意语句不同部分之间要有空格
        pstmt = conn.prepareStatement(updateSql);
        //对预编译语句对象的参数赋值
        pstmt.setString(1,eduAdmin.getName()+"");
        pstmt.setString(2,eduAdmin.getNo()+"");
        pstmt.setString(3,eduAdmin.getPassword()+"");
        pstmt.setString(4,eduAdmin.getUsername()+"");
        pstmt.setString(5,eduAdmin.getSex()+"");
        pstmt.setInt(6,eduAdmin.getId());
        //执行更新语句
        int rowAffected = pstmt.executeUpdate();
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        return rowAffected>0;
    }
    public boolean delete(EduAdmin eduAdmin, Connection connection)throws SQLException{
        String deleteSql="DELETE FROM eduadmin WHERE id=?";
        pstmt = conn.prepareStatement(deleteSql);
        pstmt.setInt(1, eduAdmin.getId());
        int rowAffected = pstmt.executeUpdate();
        return rowAffected >0;
    }
    public boolean delete(Integer id)throws SQLException{
        EduAdmin eduAdmin = this.find(id);
        return this.delete(eduAdmin);
    }

    public boolean delete(EduAdmin eduAdmin)throws SQLException{
        //sql删除语句
        String deleteSql="DELETE FROM eduadmin WHERE id=?";
        //得到数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象，如果SQL语句为多行，注意语句不同部分之间要有空格
        pstmt = conn.prepareStatement(deleteSql);
        //对预编译语句对象的参数赋值
        pstmt.setInt(1, eduAdmin.getId());
        //执行删除操作
        int rowAffected = pstmt.executeUpdate();
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        return rowAffected>0;
    }
}
