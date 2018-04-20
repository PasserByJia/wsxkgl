package cn.edu.sdjzu.xg.xkgl.service;

import cn.edu.sdjzu.xg.xkgl.dao.CourseDao;
import cn.edu.sdjzu.xg.xkgl.dao.TeacherDao;
import cn.edu.sdjzu.xg.xkgl.domain.Teacher;
import util.JdbcHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public final class TeacherService {
    private static TeacherDao teacherDao= TeacherDao.getInstance();
    //本类的一个对象引用，保存自身对象
    private static TeacherService teacherService=new TeacherService();
    //私有的构造方法，防止其它类创建它的对象
    private TeacherService(){}
    //静态方法，返回本类的惟一对象
    public static TeacherService getInstance(){
        return teacherService;
    }
    //获取所有教师
    public Collection<Teacher> findAll() throws SQLException {
        return teacherDao.findAll();
    }
    public Collection<Teacher> findAll(String conditionStr)throws SQLException{
        return teacherDao.findAll(conditionStr);
    }
    //获得id对应的教师
    public Teacher find(Integer id) throws SQLException {
        return teacherDao.find(id);
    }
    //更新一个教师
    public boolean update(Teacher teacher) throws SQLException {
        return teacherDao.update(teacher);
    }
    //增加一个教师
    public boolean add(Teacher teacher) throws SQLException {
        return teacherDao.add(teacher);
    }
    //删除一个教师
    public boolean delete(Integer id) throws Exception {
        Teacher teacher = this.find(id);
        return this.delete(teacher);
    }
    //删除一个教师
    public boolean delete(Teacher teacher) throws Exception {
            //获得进行事务的连接，添加教师和添加用户都使用本连接
            Connection connection = JdbcHelper.getConn();
            //将自动提交设为false,开始事务
            connection.setAutoCommit(false);
            boolean deleted = false;
            try {
                PreparedStatement preparedStatement =
                        connection.prepareStatement("SELECT * FROM course WHERE teacher_id=?");
                preparedStatement.setInt(1,teacher.getId());
                //执行预编译语句，结果集保存在resultSet对象中
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    deleted = CourseService.getInstance().delete(id,connection);
                }
                TeacherDao.getInstance().delete(teacher,connection);
                //提交事务
                connection.commit();
            } catch (SQLException e) {
                //回滚事务
                connection.rollback();
                throw new Exception("删除课程失败");
            } finally {
                //将自动提交设置为true，结束事务
                connection.setAutoCommit(true);
                //关闭资源
                JdbcHelper.close(null,connection);
            }
            return deleted;
        }
}




