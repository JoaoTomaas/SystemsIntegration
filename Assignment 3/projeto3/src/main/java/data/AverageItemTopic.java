package data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class AverageItemTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @XmlAttribute
    int id_item;

    int average_amount;

    public AverageItemTopic(){}

    public AverageItemTopic(int id_item, int average_amount){
        this.id_item = id_item;
        this.average_amount = average_amount;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }


    public int getAverage_amount() {
        return average_amount;
    }

    public void setAverage_amount(int average_amount) {
        this.average_amount = average_amount;
    }
}
