package cn.edu.sdjzu.xg.xkgl.service;

import cn.edu.sdjzu.xg.xkgl.dao.LoginDao;
import cn.edu.sdjzu.xg.xkgl.dao.StudentDao;
import cn.edu.sdjzu.xg.xkgl.domain.*;
import util.JdbcHelper;

import java.sql.SQLException;

public class LoginService {
    private static LoginDao loginDao = LoginDao.getInstance();
    //创建本类唯一的对象
    private static LoginService loginService=new LoginService();
    //静态方法，返回本类的惟一对象
    public static LoginService getInstance(){
        return loginService;
    }

    public Student findStudent(String username, String password) throws SQLException {
        return LoginDao.getInstance().findStudent(username,password);
    }
    public Teacher findTeacher(String username, String password) throws SQLException{
        return LoginDao.getInstance().findTeacher(username,password);
    }
    public EduAdmin findEduAdmin(String username, String password) throws SQLException{
        return LoginDao.getInstance().findEduAdmin(username,password);
    }
    public SysAdmin findSysAdmin(String username, String password) throws SQLException{
        return LoginDao.getInstance().findSysAdmin(username,password);
    }
}
