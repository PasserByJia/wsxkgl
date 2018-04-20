package cn.edu.sdjzu.xg.xkgl.service;

import cn.edu.sdjzu.xg.xkgl.dao.SysAdminDao;
import cn.edu.sdjzu.xg.xkgl.domain.SysAdmin;

import java.sql.SQLException;
import java.util.Collection;

public class SysAdminService {
    private static SysAdminDao sysAdminDao = SysAdminDao.getInstance();
    private  static  SysAdminService sysAdminService = new SysAdminService();
    public static SysAdminService getInstance(){
        return sysAdminService;
    }
    public Collection<SysAdmin> findAll()throws SQLException {
        return SysAdminDao.getInstance().findAll();
    }
    public  SysAdmin find(Integer id)throws SQLException {
        return SysAdminDao.getInstance().find(id);
    }
    public boolean update(SysAdmin sysAdmin)throws SQLException{
        return SysAdminDao.getInstance().update(sysAdmin);
    }
    public boolean add(SysAdmin sysAdmin)throws ClassNotFoundException, SQLException{
        return SysAdminService.getInstance().add(sysAdmin);
    }
    public boolean delete(Integer id)throws SQLException{
        return SysAdminDao.getInstance().delete(id);
    }
    public boolean delete(SysAdmin sysAdmin) throws SQLException {
        return SysAdminDao.getInstance().delete(sysAdmin);
    }

}
