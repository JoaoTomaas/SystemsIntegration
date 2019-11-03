package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Item;
import data.Utilizador;

import java.util.Date;
import java.util.List;

@LocalBean
@Stateless(name = "UserEJB")
public class UserBean {
@PersistenceContext(name="Users")
EntityManager em;

    public UserBean() { //Construtor pré-definido
    }


    //USER OPERATIONS
    //Penso que editar o email e a password nao faca sentido
    //secalhar usar o id?,qual vai ser a nossa primary key para distinguir users
    public void Editar_User_Info(String name, int age, String country){
        System.out.println("-- fazendo update do user --");
        Query query = em.createQuery("update Utilizador u set u.age = :age,u.country= :country "
                + "WHERE u.name = :name");
        query.setParameter("age", age);
        query.setParameter("name", name);
        query.setParameter("country", country);
        int rowsUpdated = query.executeUpdate();
        System.out.println("entities Updated: " + rowsUpdated);
    }


    /*
    //da um erro do mal JPA/Hibernate: detached entity passed to persist
    public void Insere_User(int id, int age, String email, String country,String name){
        Utilizador new_Utilizador = new Utilizador( id,  age,  email,  country,name);
        em.persist(new_Utilizador);
    }

     */

    //Apagar primeiro os items do user e depois apagar o user
    //em.find e depois o em.delete
    //usar cascate?
    public void Delete_UserAccount(String name){
        Query q = em.createQuery("from Utilizador u where u.name = :n");
        q.setParameter("n", name);
        Utilizador resultado = (Utilizador) q.getSingleResult();
        for(Item i :resultado.getItems() ){
            em.remove(i);
        }
        em.remove(resultado);
        //Query query = em.createQuery("delete from Utilizador u WHERE u.name = :p");
        //int deletedCount = query.setParameter("p", name).executeUpdate();
    }



}
