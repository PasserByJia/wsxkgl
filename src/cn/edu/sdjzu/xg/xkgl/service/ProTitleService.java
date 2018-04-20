package cn.edu.sdjzu.xg.xkgl.service;



import cn.edu.sdjzu.xg.xkgl.dao.ProTitleDao;
import cn.edu.sdjzu.xg.xkgl.domain.ProTitle;

import java.sql.SQLException;
import java.util.Collection;

public class ProTitleService {
    private ProTitleService(){}
    private static ProTitleService proTitleService = new ProTitleService() ;
    public static ProTitleService getInstance(){
        return proTitleService;
    }
    public Collection<ProTitle> findAll() throws SQLException{
        return ProTitleDao.getInstance().findAll();
    }
    public boolean addProTitle(ProTitle proTitle) throws SQLException{
        return ProTitleDao.getInstance().add(proTitle);
    }
    public boolean updateProTitle(ProTitle proTitle) throws SQLException{
        return ProTitleDao.getInstance().update(proTitle);
    }
    public ProTitle findProTitle(Integer id) throws SQLException{
        return ProTitleDao.getInstance().findProTitle(id);
    }
    public boolean deleteProTitleById(int id) throws SQLException{
        return ProTitleDao.getInstance().delete(id);
    }
    public boolean deleteProTitle(ProTitle proTitle) throws SQLException{
        return ProTitleDao.getInstance().delete(proTitle);
    }
}
