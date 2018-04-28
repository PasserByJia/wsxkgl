package cn.edu.sdjzu.xg.xkgl.service;

import cn.edu.sdjzu.xg.xkgl.dao.SysAdminDao;
import cn.edu.sdjzu.xg.xkgl.domain.SysAdmin;

import java.sql.SQLException;
import java.util.Collection;

public class SysAdminService {
    // 创建本类对象
    private static SysAdminDao sysAdminDao = SysAdminDao.getInstance();
    private  static  SysAdminService sysAdminService = new SysAdminService();
    //返回本类对象
    public static SysAdminService getInstance(){
        return sysAdminService;
    }
    //返回所有系统管理员
    public Collection<SysAdmin> findAll()throws SQLException {
        return SysAdminDao.getInstance().findAll();
    }
    //根据id，找到对应的系统管理员
    public  SysAdmin find(Integer id)throws SQLException {
        return SysAdminDao.getInstance().find(id);
    }
    //更新系统管理员
    public boolean update(SysAdmin sysAdmin)throws SQLException{
        return SysAdminDao.getInstance().update(sysAdmin);
    }
    //增加系统管理员
    public boolean add(SysAdmin sysAdmin)throws ClassNotFoundException, SQLException{
        return SysAdminService.getInstance().add(sysAdmin);
    }
    //根据id，删除对应的系统管理员
    public boolean delete(Integer id)throws SQLException{
        return SysAdminDao.getInstance().delete(id);
    }
    //删除系统管理员
    public boolean delete(SysAdmin sysAdmin) throws SQLException {
        return SysAdminDao.getInstance().delete(sysAdmin);
    }

}
