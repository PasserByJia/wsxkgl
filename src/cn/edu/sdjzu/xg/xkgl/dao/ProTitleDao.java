package cn.edu.sdjzu.xg.xkgl.dao;


import cn.edu.sdjzu.xg.xkgl.domain.ProTitle;
import util.JdbcHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

public class ProTitleDao {

        private ProTitleDao(){}
        private static ProTitleDao proTitleDao = new ProTitleDao();
        public static ProTitleDao getInstance(){
            return proTitleDao;
        }
        /**
         * 返回所有proTitle对象
         * @throws SQLException
         */
        public Collection<ProTitle> findAll() throws SQLException {
            //获取数据库连接对象
            Connection connection = JdbcHelper.getConn();
            //根据连接对象准备语句对象
            PreparedStatement preparedStatement =//预编译的语句
                    connection.prepareStatement("SELECT * FROM protitle");
            //执行预编译语句，结果集保存在rs对象中
            ResultSet rs = preparedStatement.executeQuery();
            Collection<ProTitle> proTitles =new HashSet<ProTitle>();
            while (rs.next()){
                Integer id=rs.getInt("id");
                String description=rs.getString("description");
                String no=rs.getString("no");
                ProTitle desiredproTitle=new ProTitle(id,no,description);
                proTitles.add(desiredproTitle);
            }
            //关闭资源
            JdbcHelper.close(preparedStatement,connection);
            //返回集合对象
            return proTitles;
        }
        /**
         *
         * @return 成功增加：true，失败：false
         * @throws SQLException
         */
        public boolean add(ProTitle proTitle) throws SQLException {

            //获得数据库连接对象
            Connection connection = JdbcHelper.getConn();
            //根据连接对象准备语句对象，如果SQL语句为多行，注意语句不同部分之间要有空格
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO protitle " +
                                    " (description,no)" +
                                    " VALUES (?,?)");
            //对预编译语句对象的参数赋值
            preparedStatement.setString(1,proTitle.getDescription());
            preparedStatement.setString(2, proTitle.getNo());
            //执行预编译语句，用其返回值、影响的行数为赋值affectedRowNum
            int affectedRowNum = preparedStatement.executeUpdate();
            //关闭资源
            JdbcHelper.close(preparedStatement,connection);
            //如果影响的行数大于0，则返回true，否则返回false
            return affectedRowNum >0;
        }
        /**
         *
         * @return 成功增加：true，失败：false
         * @throws SQLException
         */
        public boolean update(ProTitle proTitle) throws SQLException {
            //获得数据库连接对象
            Connection connection = JdbcHelper.getConn();
            //根据连接对象准备语句对象，如果SQL语句为多行，注意语句不同部分之间要有空格
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE protitle " +
                            " SET " +
                            " description=?," +
                            " no=? where id=?");
            //对预编译语句对象的参数赋值
            preparedStatement.setInt(3,proTitle.getId() );
            preparedStatement.setString(1, proTitle.getDescription());
            preparedStatement.setString(2, proTitle.getNo());
            //执行预编译语句，用其返回值、影响的行数为赋值affectedRowNum
            int affectedRowNum = preparedStatement.executeUpdate();
            //关闭资源
            JdbcHelper.close(preparedStatement,connection);
            //如果影响的行数大于0，则返回true，否则返回false
            return affectedRowNum >0;
        }
        /**
         *
         * @param id 目标对象对应的记录的id字段值
         * @throws SQLException
         */
        public ProTitle findProTitle(Integer id) throws SQLException {
            //声明目标对象的引用
            ProTitle desiredProTitle = null;
            //获得数据库连接对象
            Connection connection = JdbcHelper.getConn();
            //根据连接对象准备语句对象
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM protitle WHERE id=?");
            //对预编译语句对象的参数赋值
            preparedStatement.setInt(1,id);
            //执行预编译语句，结果集保存在resultSet对象中
            ResultSet resultSet = preparedStatement.executeQuery();
            //遍历resultSet对象
            if(resultSet.next()){
                String description = resultSet.getString("description");
                String no = resultSet.getString("no");
                desiredProTitle=new ProTitle(id,description,no);
            }
            //关闭资源
            JdbcHelper.close(preparedStatement,connection);
            return desiredProTitle;
        }
        public boolean delete(int id) throws SQLException{
            //获得数据库连接对象
            Connection conn = JdbcHelper.getConn();
            //根据链接对象准备语句对象
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM protitle WHERE id=?");
            //对编译语句对象的参数赋值
            preparedStatement.setInt(1, id);
            //执行预编译语句，用其返回值、影响的行数为赋值rowAffected
            int rowAffected = preparedStatement.executeUpdate();
            //关闭资源
            JdbcHelper.close(preparedStatement, conn);
            //如果影响的行数大于0，则返回true，否则返回false
            return rowAffected>0;
        }
        public boolean delete(ProTitle proTitle) throws SQLException {
            return this.delete(proTitle.getId());
        }
}
