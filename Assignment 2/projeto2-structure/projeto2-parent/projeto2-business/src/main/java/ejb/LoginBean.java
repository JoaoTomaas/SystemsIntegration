package ejb;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

@LocalBean
@Stateless(name = "LoginEJB")
public class LoginBean implements Serializable {
    @PersistenceContext(name = "Users")
    EntityManager em;

    private static final long serialVersionUID = 1L;
    private String email;
    private String password;


    public LoginBean() {}

    public int loginUser(String email, String password){
        System.out.println("enteriiiiii ");
        int status = -1;
        //Ver se o email existe na base de dados
        Query q = em.createQuery("from Utilizador u where u.email = :e");
        q.setParameter("e", email);

        if (!(q.getResultList().isEmpty())){ //Utilizador existe, verifica password
            q = em.createQuery("select password from Utilizador u where u.email = :e");
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
                    System.out.println("deu bemmmmmmm");
                    status= 1;
                } else {
                    System.out.println("deu malllllll");
                    status =2;
                }
            } catch (InvalidKeySpecException e) {

                e.printStackTrace();
            }
           /* if (!(x.getResultList().isEmpty())){ //Sucesso, faz login
                status = 1;
            }
            else{ //Password errada, volta para a página de login
                status = 2;
            }*/
        }
        else{ //Utilizador nao registado
            System.out.println("user nao registado =(");
            status = 3;
        }

        return status;
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
