package cn.edu.zjut.action;

import cn.edu.zjut.po.MainSection;
import cn.edu.zjut.po.SubSection;
import cn.edu.zjut.service.MainSectionService;
import cn.edu.zjut.service.SubSectionService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


//创建讨论区
public class CreateDiscussAction extends BaseAction {
    private String mainSection;//主板块
    private String subSection;//子板块
    private MainSectionService mainSectionService;
    private SubSectionService subSectionService;

    public String create() {
        try {//子板块名称上也有主板块名称
            if (mainSection != null && mainSection.length()>0 && subSection != null && subSection.length()>0){
                MainSection main = new MainSection();
                main.setTitle(mainSection);
                ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
                if (mainSectionService == null)//新建实例
                    mainSectionService = (MainSectionService) context.getBean("mainSectionService");
                if (!mainSectionService.add(main)){
                    this.addFieldError("create_result", "该板块已经存在");
                    return "create";
                }
                String [] subf = subSection.split(",");
                int size = subf.length;
                boolean res = true;
                for (int i=0; i<size; i++){//增加子板块
                    System.out.println("子版块:"+subf[i]);
                    SubSection sub = new SubSection();
                    sub.setMainSectionByMainSectionId(main);
                    sub.setTitle(subf[i]);
                    if (subSectionService == null)
                        subSectionService = (SubSectionService) context.getBean("subSectionService");
                    if(!subSectionService.add(sub))//如果有重名的板块 不能创建成功
                        res = false;
                }
                if(res) {
                    this.addFieldError("create_result", "创建成功");
                    return SUCCESS;
                }
            }
            return ERROR;
        } catch (BeansException e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public void setMainSection(String mainSection) {
        this.mainSection = mainSection;
    }

    public void setSubSection(String subSection) {
        this.subSection = subSection;
    }

    public MainSectionService getMainSectionService() {
        return mainSectionService;
    }

    public void setMainSectionService(MainSectionService mainSectionService) {
        this.mainSectionService = mainSectionService;
    }

    public SubSectionService getSubSectionService() {
        return subSectionService;
    }

    public void setSubSectionService(SubSectionService subSectionService) {
        this.subSectionService = subSectionService;
    }
}
