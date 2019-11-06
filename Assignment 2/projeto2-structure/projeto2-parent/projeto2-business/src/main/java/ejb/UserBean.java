package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Item;
import data.Utilizador;
import dto.ItemMapper;
import dto.UtilizadorDTO;

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
    public void Editar_User_Info(String original,String name, int age, String country,String password,String email){
        //System.out.println("-- fazendo update do user --");
        Query q = em.createQuery("select id from Utilizador u where u.email = :n");
        q.setParameter("n", original);
        int id=(int)q.getSingleResult();
        Utilizador user=em.find(Utilizador.class,id);
        user.setAge(age);
        user.setName(name);
        user.setCountry(country);
        user.setPassword(password);
        user.setEmail(email);
        em.persist(user);

    }



    //da um erro do mal JPA/Hibernate: detached entity passed to persist
    /*public void Insere_User(int age, String email, String pass, String country,String name){
        Utilizador new_Utilizador = new Utilizador(name, age, email, pass, country);
        em.persist(new_Utilizador);
    }*/
    //tive de tirar
    public UtilizadorDTO get_user_teste(String email){
        Query q = em.createQuery("from Utilizador u where u.email = :n");
        q.setParameter("n", email);
        Utilizador resultado = (Utilizador) q.getSingleResult();
        UtilizadorDTO udto = ItemMapper.INSTANCE.utilizadorToUtilizadorDTO(resultado);
        return udto;




    }


    //Apagar primeiro os items do user e depois apagar o user
    //em.find e depois o em.delete
    //usar cascate?
    public void Delete_UserAccount(String name){
        Query q = em.createQuery("from Utilizador u where u.email = :n");
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
