package cn.edu.zjut.dao;

import cn.edu.zjut.po.BlackList;
import cn.edu.zjut.po.User;

import java.util.List;

public interface BlackListDao {
    void save(BlackList blackListItem);

    void update(BlackList blackListItem);

    void delete(int userId);

    int getLevel(int userId);

    List getAll();

}
