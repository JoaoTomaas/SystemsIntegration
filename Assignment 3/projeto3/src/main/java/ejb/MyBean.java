package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Stateless
@LocalBean
public class MyBean {
    @PersistenceContext(name = "Items")
    EntityManager em;

    //final Logger logger = LoggerFactory.getLogger(MyBean.class);

    public MyBean() {
    }


    /*public Course getCourse(int id) {
        return this.lc.get(id);
    }*/

    /*public ListCountries getListCountries() {
        return this.lct;
    }*/

    public List<Country> getListCountries(){
        Query q = em.createQuery("from Country");
        @SuppressWarnings("unchecked")
        List<Country> lista_paises = q.getResultList();

        return lista_paises;
    }

    public List<Item> getListItems(){
        Query q = em.createQuery("from Item");
        @SuppressWarnings("unchecked")
        List <Item> lista_items = q.getResultList();

        return lista_items;
    }

    public void insertItem(String name){
        Item item = new Item(name);
        em.persist(item);
    }

    public void insertCountry(String name){
        Country country = new Country(name);
        em.persist(country);
    }

    //ESTATISTICAS


}

