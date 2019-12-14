package cn.edu.zjut.po;

import java.util.Collection;
import java.util.Objects;

public class MainSection {
    private int id;
    private String title;
    private Collection<SubSection> subSectionsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainSection that = (MainSection) o;
        return id == that.id &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    public Collection<SubSection> getSubSectionsById() {
        return subSectionsById;
    }

    public void setSubSectionsById(Collection<SubSection> subSectionsById) {
        this.subSectionsById = subSectionsById;
    }
}
