package ejb;

import data.Utilizador;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.crypto.Data;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.List;

@LocalBean
@Stateless(name = "RegisterEJB")
public class RegisterBean {
    @PersistenceContext(name = "Users")
    EntityManager em;

    private static final long serialVersionUID = 1L;
    private String email;

    private String password;

    public RegisterBean() {
    }

    public int UserRegister(String email, String password, String country, String name, int age) throws NoSuchAlgorithmException {
        Query q = em.createQuery("from Utilizador u where u.email = :e");
        q.setParameter("e", email);
        if ((q.getResultList().isEmpty())){
            em.persist(new Utilizador( age, email,country,name,password));
            return 0;
        }
        else{

            return -1;
        }


    }
    //meter no loggin
    public int testeeee(String email, String password) {
        //nao dara para meter os dois juntos...?
        Query q = em.createQuery("select password from Utilizador u where u.email = :e");
        q.setParameter("e", email);
        byte[] hash1=(byte[]) q.getSingleResult()  ;

        q = em.createQuery("select salt from Utilizador u where u.email = :e");
        q.setParameter("e", email);
        byte[] salt=(byte[]) q.getSingleResult()  ;

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            byte[] hash2 = factory.generateSecret(spec).getEncoded();
            if (Arrays.equals(hash1, hash2)) {
                return 1;
            }
            else{
                return-1;
            }
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        //javardice
        return -1;

    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
