package cn.edu.zjut.action;

import cn.edu.zjut.po.SensitiveWord;
import cn.edu.zjut.service.SensitiveWordService;

import java.util.List;

public class SensitiveWordAction extends BaseAction{
    private int id;
    private SensitiveWord sensitiveWord;
    private List allSensitiveWord;
    private SensitiveWordService sensitiveWordService = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SensitiveWord getSensitiveWord() {
        return sensitiveWord;
    }

    public void setSensitiveWord(SensitiveWord sensitiveWord) {
        this.sensitiveWord = sensitiveWord;
    }

    public SensitiveWordService getSensitiveWordService() {
        return sensitiveWordService;
    }

    public void setSensitiveWordService(SensitiveWordService sensitiveWordService) {
        this.sensitiveWordService = sensitiveWordService;
    }

    public List getAllSensitiveWord() {
        return allSensitiveWord;
    }

    public void setAllSensitiveWord(List allSensitiveWord) {
        this.allSensitiveWord = allSensitiveWord;
    }

    public String addSensitiveWord(){
        sensitiveWordService.addSensitiveWord(sensitiveWord);
        setAllSensitiveWord(sensitiveWordService.findAll());
        return SUCCESS;
    }

    public String deleteSensitiveWord(){
        sensitiveWord =(SensitiveWord) sensitiveWordService.findById(id).get(0);
        sensitiveWordService.deleteSensitiveWord(sensitiveWord);
        setAllSensitiveWord(sensitiveWordService.findAll());
        getRequest().put("allSensitiveWord",allSensitiveWord);
        return SUCCESS;
    }

    public String alterSensitiveWord(){
        sensitiveWordService.alterSensitiveWord(sensitiveWord);
        setAllSensitiveWord(sensitiveWordService.findAll());
        return SUCCESS;
    }

    public String findSensitiveWord(){
        setSensitiveWord(sensitiveWord);
        return SUCCESS;
    }

    public String findAllSensitiveWord(){
        setAllSensitiveWord(sensitiveWordService.findAll());
        return SUCCESS;
    }
}
