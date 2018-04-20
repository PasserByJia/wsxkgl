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

public final class StudentService {
    private static StudentDao studentDao = StudentDao.getInstance();
    private static StudentService studentService = new StudentService();
    private StudentService(){}

    public static StudentService getInstance(){
        return studentService;
    }

    public Collection<Student> findAll() throws SQLException{
        return studentDao.findAll();
    }
    public Collection<Student> findAll(String conditionStr)throws SQLException{
        return studentDao.findAll(conditionStr);
    }
    public Student find(Integer id) throws SQLException{
        return studentDao.find(id);
    }
    public Student findByUsername(String username)throws SQLException{
        return studentDao.findByUsername(username);
    }

    public boolean update(Student student) throws SQLException{
        return studentDao.update(student);
    }

    public boolean add(Student student) throws SQLException{
        return studentDao.add(student);
    }

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
