package cn.edu.sdjzu.xg.xkgl.service;



import cn.edu.sdjzu.xg.xkgl.dao.ProTitleDao;
import cn.edu.sdjzu.xg.xkgl.domain.ProTitle;

import java.sql.SQLException;
import java.util.Collection;

public class ProTitleService {
    //私有构造器
    private ProTitleService(){}
    //创建本类对象
    private static ProTitleService proTitleService = new ProTitleService() ;
    //返回本类对象
    public static ProTitleService getInstance(){
        return proTitleService;
    }
    //返回所有ProTitle
    public Collection<ProTitle> findAll() throws SQLException{
        return ProTitleDao.getInstance().findAll();
    }
    //增加ProTitle
    public boolean addProTitle(ProTitle proTitle) throws SQLException{
        return ProTitleDao.getInstance().add(proTitle);
    }
    //增加ProTitle信息
    public boolean updateProTitle(ProTitle proTitle) throws SQLException{
        return ProTitleDao.getInstance().update(proTitle);
    }
    //根据id，找到对应ProTitle
    public ProTitle findProTitle(Integer id) throws SQLException{
        return ProTitleDao.getInstance().findProTitle(id);
    }
    //根据id，删除对应ProTitle

    public boolean deleteProTitleById(int id) throws SQLException{
        return ProTitleDao.getInstance().delete(id);
    }
    //根据proTitle，删除对应ProTitle

    public boolean deleteProTitle(ProTitle proTitle) throws SQLException{
        return ProTitleDao.getInstance().delete(proTitle);
    }
}
