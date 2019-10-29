package data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String category;
    private String country_of_origin;
    private int price;
    @Temporal(TemporalType.DATE)
    private Date published_date;
    //Como é que faço quanto à fotografia?
    @ManyToOne
    private User user;

    public Item (){
        super();
    }


    public Item(String name, String category, String country_of_origin, int price) {
        this.name = name;
        this.category = category;
        this.country_of_origin = country_of_origin;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry_of_origin() {
        return country_of_origin;
    }

    public void setCountry_of_origin(String country_of_origin) {
        this.country_of_origin = country_of_origin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
