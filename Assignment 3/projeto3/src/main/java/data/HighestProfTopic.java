package data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class HighestProfTopic implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @XmlAttribute
    int id_item;

    int highest_profit;

    public HighestProfTopic() {}


    public HighestProfTopic(int id_item, int highest_profit) {
        this.id_item = id_item;
        this.highest_profit = highest_profit;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }


    public int getHighest_profit() {
        return highest_profit;
    }

    public void setHighest_profit(int highest_profit) {
        this.highest_profit = highest_profit;
    }
}
