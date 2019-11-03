package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;

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
        int status = -1;
        //Ver se o email existe na base de dados
        Query q = em.createQuery("from Utilizador u where u.email = :e");
        q.setParameter("e", email);

        if (!(q.getResultList().isEmpty())){ //Utilizador existe, verifica password
            Query x = em.createQuery("from Utilizador u where u.email = :e and u.password = :p");
            x.setParameter("e", email);
            x.setParameter("p", password);

            if (!(x.getResultList().isEmpty())){ //Sucesso, faz login
                status = 1;
            }
            else{ //Password errada, volta para a página de login
                status = 2;
            }
        }
        else{ //Utilizador nao registado
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
