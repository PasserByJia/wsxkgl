package cn.edu.sdjzu.xg.xkgl.service;



import cn.edu.sdjzu.xg.xkgl.dao.OpenPeriodDao;
import cn.edu.sdjzu.xg.xkgl.domain.OpenPeriod;

import java.sql.SQLException;
import java.util.Collection;

public class OpenPeriodService {
    private static OpenPeriodDao openPeriodDao= OpenPeriodDao.getInstance();
    private static OpenPeriodService openPeriodService=new OpenPeriodService();


    public static OpenPeriodService getInstance(){
        return openPeriodService;
    }

    public Collection<OpenPeriod> findAll()throws SQLException {
        return openPeriodDao.findAll();
    }

    public OpenPeriod findAll(Integer id)throws SQLException{
        return openPeriodDao.findAll(id);
    }

    public boolean update(OpenPeriod openPeriod)throws SQLException{
        return openPeriodDao.update(openPeriod);
    }
}
