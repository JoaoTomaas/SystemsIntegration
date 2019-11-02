package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Item;

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
    public void Editar_User_Info(String name, int age, String country){}

    //Apagar primeiro os items do user e depois apagar o user
    public void Delete_UserAccount(){}



}
