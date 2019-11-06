package data;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.*;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.List;

@Entity
public class Utilizador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private int age;
    private String email;
    private byte[] password;
    private byte[] salt;

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        this.salt=salt;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try{
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            try {
                byte[] hash = factory.generateSecret(spec).getEncoded();
                this.password=hash;
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private String country;
    @OneToMany(mappedBy = "utilizador")
    private List<Item> items;

    public Utilizador(){
        super();
    }

    public Utilizador( int age, String email, String country,String name,String password) throws NoSuchAlgorithmException {
        this.age = age;
        this.email = email;
        this.country = country;
        this.name=name;
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        this.salt=salt;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        try {
            byte[] hash = factory.generateSecret(spec).getEncoded();
            this.password=hash;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
