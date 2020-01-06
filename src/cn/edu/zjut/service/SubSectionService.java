package cn.edu.zjut.service;

import cn.edu.zjut.po.SubSection;

import java.util.List;

public interface SubSectionService {
    //获取所有子板块
    List<SubSection> getAllSubSection();

    //根据id获取子板块
    SubSection getSubSectionById(int id);

    //根据mid获取子板块
    List<SubSection> getSubSectionByMId(int mid);

    //添加子板库
    public boolean add(SubSection subSection);
}
