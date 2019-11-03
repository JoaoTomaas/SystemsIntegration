package data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Utilizador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private int age;
    private String email;
    private String password;
    private String country;
    @OneToMany(mappedBy = "utilizador")
    private List<Item> items;

    public Utilizador() {
        super();
    }

    public Utilizador(int id, String name, int age, String email, String password, String country) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
