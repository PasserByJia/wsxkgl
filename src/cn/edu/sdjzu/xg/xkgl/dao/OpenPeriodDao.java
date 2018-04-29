package cn.edu.sdjzu.xg.xkgl.dao;

import cn.edu.sdjzu.xg.xkgl.domain.OpenPeriod;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;

public class OpenPeriodDao {
    //构造器定义为private，“阻止”其它类创建本类的对象
    private OpenPeriodDao(){}
    //声明openPeriodDao对象引用
    private static OpenPeriodDao openPeriodDao;
    //返回本类的惟一对象
    public static OpenPeriodDao getInstance(){
        if(openPeriodDao==null){
            openPeriodDao=new OpenPeriodDao();
        }
        return openPeriodDao;
    }

    /**
     * 返回所有的选课时间对象
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
        //选课时间集合
        Collection<OpenPeriod> openPeriods=new HashSet<OpenPeriod>();
        //遍历结果集放到集合中
        while(rs.next()){
            Integer id = rs.getInt("id");
            Timestamp  startTime=rs.getTimestamp ("");
            Timestamp  endTime=rs.getTimestamp ("");
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
        //声明选课时间引用
        OpenPeriod desiredopenPeriod = null;
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //根据连接对象准备查询语句对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM openperiod WHERE id=?");
        //对预编译语句对象的参数赋值
        preparedStatement.setInt(1,id);
        //执行预编译语句，返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        //获得结果集，对选课时间属性赋值
        while (resultSet.next()){
            Timestamp  startTime = resultSet.getTimestamp ("startTime");
            Timestamp  endTime = resultSet.getTimestamp ("endTime");
            desiredopenPeriod = new OpenPeriod(id,startTime,endTime);
        }
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        return  desiredopenPeriod;
    }

    /*更改选课时间*/
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
        preparedStatement.setTimestamp (1,openPeriod.getStartTime());
        preparedStatement.setTimestamp (2,openPeriod.getEndTime());
        preparedStatement.setInt(3,openPeriod.getId());
        //执行预编译语句，用其返回值、影响的行数为赋值affectedRowNum
        int affectedRowNum = preparedStatement.executeUpdate();
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        //如果影响的行数大于0，则返回true，否则返回false
        return affectedRowNum >0;
    }
}
