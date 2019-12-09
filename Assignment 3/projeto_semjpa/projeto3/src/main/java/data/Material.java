package data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;


@XmlAccessorType(XmlAccessType.FIELD)
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    int id;

    private String title;
    private String location;

    public Material() {}

    public Material(int id, String name, String location2) {
        this.id = id;
        this.title = name;
        this.location = location2;
    }

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
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public void update(String name, String location2) {
        this.title = name;
        this.location = location2;
    }

}