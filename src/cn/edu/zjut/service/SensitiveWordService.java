package cn.edu.zjut.service;

import cn.edu.zjut.po.SensitiveWord;

import java.util.List;

public interface SensitiveWordService {
    void addSensitiveWord(SensitiveWord sensitiveWord);
    void deleteSensitiveWord(SensitiveWord sensitiveWord);
    void alterSensitiveWord(SensitiveWord sensitiveWord);
    List findById(int id);
    List findAll();
}
