package cn.edu.zjut.service;

import cn.edu.zjut.po.BlackList;

import java.util.List;

public interface BlackListService {

    void save(BlackList blackListItem);

    void update(BlackList blackListItem);

    void delete(int userId);

    int getLevel(int userId);

    List getAll();
}
