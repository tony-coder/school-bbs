package cn.edu.zjut.dao;

import cn.edu.zjut.po.BlackList;

public interface BlackListDao {
    void save(BlackList blackListItem);

    void update(BlackList blackListItem);

    void delete(int userId);

    int getLevel(int userId);

}
