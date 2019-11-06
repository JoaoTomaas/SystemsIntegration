package dto;

import java.util.Date;

public class ItemDTO {
    //podemos mudar os nomes mas acho que nao e preciso XD
    private int id;
    private String name;
    private String category;
    private String country_of_origin;
    private int price;
    private byte[] img;
    private Date published_date;
    private UtilizadorDTO utilizador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Date getPublished_date() {
        return published_date;
    }

    public void setPublished_date(Date published_date) {
        this.published_date = published_date;
    }

    public UtilizadorDTO getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(UtilizadorDTO utilizador) {
        this.utilizador = utilizador;
    }
}
