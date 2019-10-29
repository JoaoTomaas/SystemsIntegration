package ejb;

import javax.ejb.Stateful;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.Item;
import data.User;

import java.util.Date;
import java.util.List;

@Stateful(name = "UserEJB")
public class UserBean implements UserBeanRemote{
@PersistenceContext(name="Item")
EntityManager em;

    public UserBean() { //Construtor pré-definido
    }

    //Acho que nao ha necessidade de levar Override
    public void Procurar_Items() {
        Query q = em.createQuery("from Item i");
        @SuppressWarnings("unchecked")
        List<Item> resultado = q.getResultList();

        System.out.println("Lista de items " + resultado);
    }

    public void Procurar_Items_Categoria(String categoria) {
        Query q = em.createQuery("from Item i where i.category = :c");
        q.setParameter("c", categoria);

        @SuppressWarnings("unchecked")
        List<Item> resultado = q.getResultList();

        System.out.println("Lista de items por categoria " + resultado);
    }

    public void Procurar_Items_Country(String pais) {
        Query q = em.createQuery("from Item i where i.country_of_origin = :p");
        q.setParameter("p", pais);

        @SuppressWarnings("unchecked")
        List<Item> resultado = q.getResultList();

        System.out.println("Lista de items por país de origem " + resultado);
    }

    @Override
    public void Procurar_Items_PriceRange(int lower_bound, int upper_bound) {
        Query q = em.createQuery("from Item i where i.price between :lb and  :ub");
        q.setParameter("lb", lower_bound);
        q.setParameter("ub", upper_bound);

        List<Item> resultado = q.getResultList();

        System.out.println("Lista de items dentro do price range " + resultado);
    }

    @Override
    public void Procurar_Items_Data(Date data) {
        Query q = em.createQuery("from Item i where i.published_date > :d");
        q.setParameter("d", data);

        List<Item> resultado = q.getResultList();

        System.out.println("Lista de items publicados apos a data " + resultado);
    }


}
