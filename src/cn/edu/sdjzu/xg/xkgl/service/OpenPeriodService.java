package cn.edu.sdjzu.xg.xkgl.service;



import cn.edu.sdjzu.xg.xkgl.dao.OpenPeriodDao;
import cn.edu.sdjzu.xg.xkgl.domain.OpenPeriod;

import java.sql.SQLException;
import java.util.Collection;

public class OpenPeriodService {
    //创建本类对象
    private static OpenPeriodDao openPeriodDao= OpenPeriodDao.getInstance();
    private static OpenPeriodService openPeriodService=new OpenPeriodService();

    //返回实例对象
    public static OpenPeriodService getInstance(){
        return openPeriodService;
    }
    //   返回所有OpenPeriod对象
    public Collection<OpenPeriod> findAll()throws SQLException {
        return openPeriodDao.findAll();
    }
    // 根据id，  返回相应OpenPeriod对象
    public OpenPeriod findAll(Integer id)throws SQLException{
        return openPeriodDao.findAll(id);
    }
    //更新openPeriod的信息
    public boolean update(OpenPeriod openPeriod)throws SQLException{
        return openPeriodDao.update(openPeriod);
    }
}
