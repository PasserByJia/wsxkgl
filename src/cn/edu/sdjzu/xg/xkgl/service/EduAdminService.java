package cn.edu.sdjzu.xg.xkgl.service;

import cn.edu.sdjzu.xg.xkgl.dao.EduAdminDao;
import cn.edu.sdjzu.xg.xkgl.domain.EduAdmin;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public class EduAdminService {
    private static EduAdminDao eduAdminDao = EduAdminDao.getInstance();
    private  static  EduAdminService eduAdminService = new EduAdminService();
    private static Statement statement = null;
    private static ResultSet rs = null;
    private static PreparedStatement pstmt = null;
    public static EduAdminService getInstance(){
        return eduAdminService;
}
    public Collection<EduAdmin> findAll() throws SQLException {
        return eduAdminDao.findAll();
    }
    public Collection<EduAdmin> findAll(String conditionStr)throws SQLException{
        return eduAdminDao.findAll(conditionStr);
    }
    public EduAdmin find(Integer id) throws SQLException{
        return eduAdminDao.find(id);
    }
    public boolean add(EduAdmin eduAdmin) throws SQLException{
        return  eduAdminDao.add(eduAdmin);
    }
    public boolean update(EduAdmin eduAdmin) throws SQLException{
        System.out.println(eduAdmin.getUsername());
        return  eduAdminDao.update(eduAdmin);
    }
    public boolean delete(Integer id)throws SQLException{
        return eduAdminDao.delete(id);
    }
    public boolean delete(EduAdmin eduAdmin)throws SQLException{
        //获得进行事务的连接，添加教师和添加用户都使用本连接
        Connection connection = JdbcHelper.getConn();
        //将自动提交设为false,开始事务
        connection.setAutoCommit(false);
        boolean deleted = false;
        try {
            Collection<Integer> notices = new TreeSet<Integer>();
            //sql语句查询所有的课题
            String selectSql = "SELECT * FROM notice WHERE eduAdmin_id=?";
            pstmt.setInt(1,eduAdmin.getId());
            //获取数据库连接对象
            connection = JdbcHelper.getConn();
            //根据连接对象准备语句对象，如果SQL语句为多行，注意语句不同部分之间要有空格
            statement = connection.createStatement();
            //执行查询语句，返回结果集
            rs = statement.executeQuery(selectSql);
            while (rs.next()) {
                int id = rs.getInt("id");
                deleted =  NoticeService.getInstance().delete(id);
            }
            EduAdminDao.getInstance().delete(eduAdmin,connection);
            //提交事务
            connection.commit();
        } catch (SQLException e) {
            //回滚事务
            connection.rollback();
        } finally {
            //将自动提交设置为true，结束事务
            connection.setAutoCommit(true);
            //关闭资源
            JdbcHelper.close(null,connection);
        }
        return deleted;
    }
}
