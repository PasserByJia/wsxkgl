package cn.edu.sdjzu.xg.xkgl.service;

import cn.edu.sdjzu.xg.xkgl.dao.NoticeDao;
import cn.edu.sdjzu.xg.xkgl.domain.Notice;

import java.sql.SQLException;
import java.util.Collection;

public class NoticeService {
    //创建本类对象
    private static NoticeDao noticeDao = NoticeDao.getInstance();
    private  static  NoticeService noticeService = new NoticeService();
    //返回实例对象
    public static NoticeService getInstance(){
        return noticeService;
    }
    //返回所有通知
    public Collection<Notice> findAll() throws SQLException {
        return noticeDao.findAll();
    }
    //根据id返回相应通知
    public Notice find(int id) throws SQLException{
        return noticeDao.find(id);
    }
    //增加通知
    public boolean add(Notice notice) throws SQLException{
        return noticeDao.add(notice);
    }
    //更新通知信息
    public boolean update(Notice notice) throws SQLException{
        return noticeDao.update(notice);
    }
    //删除通知
    public boolean delete(Integer id)throws SQLException{
        return  noticeDao.delete(id);
    }
    public boolean delete(Notice notice)throws SQLException{
        return noticeDao.delete(notice);
    }
}
