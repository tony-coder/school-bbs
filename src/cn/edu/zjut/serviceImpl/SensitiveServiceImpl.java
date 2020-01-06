package cn.edu.zjut.serviceImpl;

import cn.edu.zjut.dao.SensitiveWordDao;
import cn.edu.zjut.po.SensitiveWord;
import cn.edu.zjut.service.SensitiveWordService;

import java.util.List;

public class SensitiveServiceImpl implements SensitiveWordService {
    private SensitiveWordDao sensitiveWordDao;

    public SensitiveWordDao getSensitiveWordDao() {
        return sensitiveWordDao;
    }

    public void setSensitiveWordDao(SensitiveWordDao sensitiveWordDao) {
        this.sensitiveWordDao = sensitiveWordDao;
    }

    @Override
    public void addSensitiveWord(SensitiveWord sensitiveWord) {
        sensitiveWordDao.save(sensitiveWord);
    }

    @Override
    public void deleteSensitiveWord(SensitiveWord sensitiveWord) {
        sensitiveWordDao.delete(sensitiveWord);
    }

    @Override
    public void alterSensitiveWord(SensitiveWord sensitiveWord) {
        sensitiveWordDao.update(sensitiveWord);
    }

    @Override
    public List findById(int id) {
       return sensitiveWordDao.findById(id);
    }

    @Override
    public List findAll() {
        return sensitiveWordDao.findAll();
    }


}
