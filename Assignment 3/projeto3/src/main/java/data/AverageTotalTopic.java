package data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class AverageTotalTopic implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @XmlAttribute
    int id_item;

    int average_mount;

    public AverageTotalTopic() {}

    public AverageTotalTopic(int id_item, int average_mount) {
        this.id_item = id_item;
        this.average_mount = average_mount;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getAverage_mount() {
        return average_mount;
    }

    public void setAverage_mount(int average_mount) {
        this.average_mount = average_mount;
    }
}
