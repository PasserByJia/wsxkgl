package cn.edu.sdjzu.xg.xkgl.dao;

import cn.edu.sdjzu.xg.xkgl.domain.OpenPeriod;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;

public class OpenPeriodDao {
    private OpenPeriodDao(){}
    private static OpenPeriodDao openPeriodDao;
    public static OpenPeriodDao getInstance(){
        if(openPeriodDao==null){
            openPeriodDao=new OpenPeriodDao();
        }
        return openPeriodDao;
    }

    /**
     * 返回所有的openPeriod对象
     * @throws SQLException
     */
    public Collection<OpenPeriod> findAll() throws SQLException {
        //获取数据库连接对象
        Connection connection= JdbcHelper.getConn();
        //根据连接对象准备语句对象
        PreparedStatement preparedStatement=//预编译语句
                connection.prepareStatement("SELECT * FROM openperiod");
        //执行预编译语句，结果保存在rs对象中
        ResultSet rs = preparedStatement.executeQuery();
        Collection<OpenPeriod> openPeriods=new HashSet<OpenPeriod>();
        while(rs.next()){
            Integer id = rs.getInt("id");
            Date startTime=rs.getDate("");
            Date endTime=rs.getDate("");
            OpenPeriod desiredopenPeriod=new OpenPeriod(id,startTime,endTime);
            openPeriods.add(desiredopenPeriod);
        }
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        //返回集合对象
        return openPeriods;
    }

    /**
     * 根据ID，获取到对应的openPeriod
     */
    public  OpenPeriod findAll(Integer id)throws SQLException {
        OpenPeriod desiredopenPeriod = null;
        Connection connection = JdbcHelper.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM openperiod WHERE id=?");
        //对预编译语句对象的参数赋值
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Date startTime = resultSet.getDate("startTime");
            Date endTime = resultSet.getDate("endTime");
            desiredopenPeriod = new OpenPeriod(id,startTime,endTime);
        }
        JdbcHelper.close(preparedStatement,connection);
        return  desiredopenPeriod;
    }

    /**
     * 修改
     */
    public Boolean update(OpenPeriod openPeriod)throws SQLException{
        //获取数据库连接
        Connection connection = JdbcHelper.getConn();
        //准备预编译的语句
        PreparedStatement preparedStatement =//预编译的语句
                connection.prepareStatement("UPDATE openperiod " +
                        "SET startTime=?," +
                        "endTime=?" +
                        " WHERE id=?");
        //对预编译语句对象的参数赋值
        preparedStatement.setDate(1,openPeriod.getStartTime());
        preparedStatement.setDate(2,openPeriod.getEndTime());
        preparedStatement.setInt(3,openPeriod.getId());
        //执行预编译语句，用其返回值、影响的行数为赋值affectedRowNum
        int affectedRowNum = preparedStatement.executeUpdate();
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        //如果影响的行数大于0，则返回true，否则返回false
        return affectedRowNum >0;
    }
}
