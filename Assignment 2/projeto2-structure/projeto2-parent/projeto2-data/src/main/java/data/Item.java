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
    private float price;
    private String img_path;
    @Temporal(TemporalType.DATE)
    private Date published_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    private Utilizador utilizador;

    public Item (){
        super();
    }


    public Item(String name, String category, String country_of_origin, float price, Date published_date, String img_path) {
        this.name = name;
        this.category = category;
        this.country_of_origin = country_of_origin;
        this.price = price;
        this.published_date = published_date;
        this.img_path = img_path;
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

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getPublished_date() {
        return published_date;
    }

    public void setPublished_date(Date published_date) {
        this.published_date = published_date;
    }

    //Como é que faço quanto à fotografia?
    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
