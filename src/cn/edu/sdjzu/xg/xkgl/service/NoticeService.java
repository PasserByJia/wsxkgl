package cn.edu.sdjzu.xg.xkgl.service;

import cn.edu.sdjzu.xg.xkgl.dao.NoticeDao;
import cn.edu.sdjzu.xg.xkgl.domain.Notice;

import java.sql.SQLException;
import java.util.Collection;

public class NoticeService {
    private static NoticeDao noticeDao = NoticeDao.getInstance();
    private  static  NoticeService noticeService = new NoticeService();
    public static NoticeService getInstance(){
        return noticeService;
    }
    public Collection<Notice> findAll() throws SQLException {
        return noticeDao.findAll();
    }
    public Notice find(int id) throws SQLException{
        return noticeDao.find(id);
    }
    public boolean add(Notice notice) throws SQLException{
        return noticeDao.add(notice);
    }
    public boolean update(Notice notice) throws SQLException{
        return noticeDao.update(notice);
    }
    public boolean delete(Integer id)throws SQLException{
        return  noticeDao.delete(id);
    }
    public boolean delete(Notice notice)throws SQLException{
        return noticeDao.delete(notice);
    }
}
