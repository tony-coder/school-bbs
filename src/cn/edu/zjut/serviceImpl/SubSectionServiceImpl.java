package cn.edu.zjut.serviceImpl;

import cn.edu.zjut.dao.SubSectionDao;
import cn.edu.zjut.po.SubSection;
import cn.edu.zjut.service.SubSectionService;

import java.util.List;

public class SubSectionServiceImpl implements SubSectionService {
    private SubSectionDao subSectionDao;
    @Override
    public List<SubSection> getAllSubSection() {
        return subSectionDao.findAll();
    }

    @Override
    public SubSection getSubSectionById(int id) {
        return subSectionDao.findById(id);
    }

    @Override
    public List<SubSection> getSubSectionByMId(int mid) {
        return subSectionDao.findByMainId(mid);
    }

    @Override
    public boolean add(SubSection subSection) {
        List<SubSection> subSections = subSectionDao.findByTitle(subSection.getTitle());
        if(subSections.size() < 1){//这个主板块标题不存在 保证没有重名的主板块
            subSectionDao.save(subSection);
            return true;
        } else
            return false;
    }

    public void setSubSectionDao(SubSectionDao subSectionDao) {
        this.subSectionDao = subSectionDao;
    }
}
