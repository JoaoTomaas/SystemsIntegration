package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @XmlAttribute
    int country_id;

    private String country_name;

    public Country() {}

    public Country(String country_name){
        this.country_name = country_name;
    }

    /*public Country(int country_id, String country_name){
        this.country_id = country_id;
        this.country_name = country_name;
    }*/

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    /* Update pode ser preciso para os results do kafka
    public void update(String name, String location2) {
        this.title = name;
        this.location = location2;
    }
     */

}
