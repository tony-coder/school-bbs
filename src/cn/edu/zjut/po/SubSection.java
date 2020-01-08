package cn.edu.zjut.po;

import java.util.Collection;
import java.util.Objects;

public class SubSection {
    private int id;
//    private int mainSectionId;
    private String title;
    private String description;
    private MainSection mainSectionByMainSectionId;
    private Collection<Topic> topicsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getMainSectionId() {
//        return mainSectionId;
//    }
//
//    public void setMainSectionId(int mainSectionId) {
//        this.mainSectionId = mainSectionId;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubSection that = (SubSection) o;
        return id == that.id &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    public MainSection getMainSectionByMainSectionId() {
        return mainSectionByMainSectionId;
    }

    public void setMainSectionByMainSectionId(MainSection mainSectionByMainSectionId) {
        this.mainSectionByMainSectionId = mainSectionByMainSectionId;
    }

    public Collection<Topic> getTopicsById() {
        return topicsById;
    }

    public void setTopicsById(Collection<Topic> topicsById) {
        this.topicsById = topicsById;
    }

}
