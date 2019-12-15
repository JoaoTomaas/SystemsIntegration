package data;

import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class CountrySalesTopic implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @XmlAttribute
    int id_item;
    String country_name;
    int produto;


    public CountrySalesTopic() {}

    public CountrySalesTopic(String country_name, int id_item,int produto) {
        this.country_name = country_name;
        this.id_item = id_item;
        this.produto = produto;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }
}
