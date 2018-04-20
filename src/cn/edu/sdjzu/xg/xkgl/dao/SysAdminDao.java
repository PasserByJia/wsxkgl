package cn.edu.sdjzu.xg.xkgl.dao;

import cn.edu.sdjzu.xg.xkgl.domain.SysAdmin;
import util.JdbcHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.TreeSet;

public class SysAdminDao {
    private static Connection conn = null;
    private static ResultSet rs = null;
    private static PreparedStatement pstmt = null;
    private static SysAdminDao sysAdminDao = new SysAdminDao();
    private SysAdminDao (){}
    public static SysAdminDao getInstance(){return  sysAdminDao;}
    private Collection<SysAdminDao> sysAdminDaos;
    public  Collection<SysAdmin> findAll()throws SQLException{
        Collection<SysAdmin> sysAdmins = new TreeSet<SysAdmin>();
        conn = JdbcHelper.getConn();
        pstmt = conn.prepareStatement("SELECT * FROM sysadmin");
        rs = pstmt.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String no = rs.getString("no");
            String password = rs.getString("password");
            String sex = rs.getString("sex");
            String username = rs.getString("username");
            SysAdmin sysAdmin = new SysAdmin(id,name,no,password,sex,username);
            sysAdmins.add(sysAdmin);
        }
        JdbcHelper.close(pstmt,conn);
        return sysAdmins;

    }

    //根据ID，获取到对应的SysAdminDao
    public  SysAdmin find(Integer id)throws SQLException {
        SysAdmin sysAdmin = null;
        Connection connection = JdbcHelper.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM sysadmin WHERE id=?");
        //对预编译语句对象的参数赋值
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            String no = resultSet.getString("no");
            String password = resultSet.getString("password");
            String sex = resultSet.getString("sex");
            String username = resultSet.getString("username");
            sysAdmin = new SysAdmin(id,name,no,password,sex,username);
        }
        JdbcHelper.close(preparedStatement,connection);
        return  sysAdmin;
    }

    //修改SysAdminDao
    public boolean update(SysAdmin sysAdmin)throws SQLException{
        //获取数据库连接
        conn = JdbcHelper.getConn();
        //准备预编译的语句
        pstmt =//预编译的语句
                conn.prepareStatement("UPDATE sysadmin " +
                        "SET name=?," +
                        "no=?," +
                        "password=?" +
                        "sex=?" +
                        "username=?" +
                        " WHERE id=?");
        //对预编译语句对象的参数赋值
        pstmt.setString(1,sysAdmin.getName());
        pstmt.setString(2,sysAdmin.getNo());
        pstmt.setString(3,sysAdmin.getPassword());
        pstmt.setString(4,sysAdmin.getSex());
        pstmt.setString(5,sysAdmin.getUsername());
        pstmt.setInt(6,sysAdmin.getId());
        //执行预编译语句，用其返回值、影响的行数为赋值affectedRowNum
        int affectedRowNum = pstmt.executeUpdate();
        //关闭资源
        JdbcHelper.close(pstmt,conn);
        //如果影响的行数大于0，则返回true，否则返回false
        return affectedRowNum >0;
    }

    //增加一个SysAdminDao
    public boolean add(SysAdmin sysAdmin)throws ClassNotFoundException, SQLException{
        conn = JdbcHelper.getConn();
        //准备预编译的语句
        pstmt = conn.prepareStatement("insert into sysadmin " +
                        " (name, no, password, sex, username)" +
                        " values(?,?,?,?,?)");
        //对预编译语句对象的参数赋值
        pstmt.setString(1,sysAdmin.getName());
        pstmt.setString(2,sysAdmin.getNo());
        pstmt.setString(3,sysAdmin.getPassword());
        pstmt.setString(4,sysAdmin.getSex());
        pstmt.setString(5,sysAdmin.getUsername());
//        执行语句
        int rowAffected = pstmt.executeUpdate();
        //关闭资源
        JdbcHelper.close(pstmt, conn);
        //如果影响的行数大于0，则返回true，否则返回false
        return rowAffected > 0;
    }

    //删除一个SysAdminDao
    public boolean delete(Integer id)throws SQLException{
        //获得数据库连接对象
        conn = JdbcHelper.getConn();
        //根据连接对象准备语句对象，如果SQL语句为多行，注意语句不同部分之间要有空格
        pstmt= conn.prepareStatement("DELETE FROM sysadmin " +
                " WHERE id=?");
        //对预编译语句对象的参数赋值
        pstmt.setInt(1,id);
        //执行预编译语句，用其返回值、影响的行数为赋值affectedRowNum
        int affectedRowNum = pstmt.executeUpdate();
        //关闭资源
        JdbcHelper.close(pstmt ,conn);
        //如果影响的行数大于0，则返回true，否则返回false
        return affectedRowNum >0;
    }
    public boolean delete(SysAdmin sysAdmin) throws SQLException {
        //获得school的id
        int id = sysAdmin.getId();
        //根据id删除School对象
        Boolean yesOrNo = delete(id);
        //返回是否删除成功
        return yesOrNo;
    }

}
