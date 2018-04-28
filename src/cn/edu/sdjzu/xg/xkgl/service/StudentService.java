package cn.edu.sdjzu.xg.xkgl.service;


import cn.edu.sdjzu.xg.xkgl.dao.CourseSelectionDao;
import cn.edu.sdjzu.xg.xkgl.dao.StudentDao;
import cn.edu.sdjzu.xg.xkgl.domain.Student;
import util.JdbcHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public final class StudentService {
    //创建本类对象
    private static StudentDao studentDao = StudentDao.getInstance();
    private static StudentService studentService = new StudentService();
    public List<Student> studentList;
    //私有构造器
    private StudentService(){}
    //返回本类对象
    public static StudentService getInstance(){
        return studentService;
    }
    //返回所有学生
    public Collection<Student> findAll() throws SQLException{
        return studentDao.findAll();
    }
    //根据传入参数，返回相应的所有学生
    public Collection<Student> findAll(String conditionStr)throws SQLException{
        return studentDao.findAll(conditionStr);
    }
    //根据传入参数id，返回相应的学生

    public Student find(Integer id) throws SQLException{
        return studentDao.find(id);
    }
    //根据传入参数username，返回相应的所有学生

    public Student findByUsername(String username)throws SQLException{
        return studentDao.findByUsername(username);
    }
    //更新学生信息
    public boolean update(Student student) throws SQLException{
        return studentDao.update(student);
    }
//增加学生
    public boolean add(Student student) throws SQLException{
        return studentDao.add(student);
    }
//根据id，删除相应的学生
    public boolean delete(Integer id)throws Exception{
        Student student = this.find(id);
        return this.delete(student);
    }

    private boolean delete(Student student) throws Exception {
        //获得进行事务的连接
        Connection connection = JdbcHelper.getConn();
        //将自动提交设为false,开始事务
        connection.setAutoCommit(false);
        boolean deleted = false;
        try{
            //sql语句查询
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id FROM courseselection WHERE student_id=?");
            preparedStatement.setInt(1,student.getId());
            //执行预编译语句，结果集保存在resultSet对象中
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                int courseSelection_id = resultSet.getInt("id");
                deleted = CourseSelectionDao.getInstance().delete(courseSelection_id);
            }

            deleted = StudentDao.getInstance().delete(student,connection);
            //提交事务
            connection.commit();
        }
        catch (SQLException e){
            //回滚

            connection.rollback();
            throw new Exception("删除学生时,删除选课关系出错");
        }
        finally {
            //将自动提交设置为true，结束事务
            connection.setAutoCommit(true);
            //关闭资源
            JdbcHelper.close(null,connection);
        }
        return deleted;
    }
}
