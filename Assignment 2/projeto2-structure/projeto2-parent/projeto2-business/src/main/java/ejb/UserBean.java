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
@PersistenceContext(name="Item")
EntityManager em;

    public UserBean() { //Construtor pré-definido
    }

    //Acho que nao ha necessidade de levar Override
    //PROCURAR ITEMS
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

    public void Procurar_Items_PriceRange(int lower_bound, int upper_bound) {
        Query q = em.createQuery("from Item i where i.price between :lb and  :ub");
        q.setParameter("lb", lower_bound);
        q.setParameter("ub", upper_bound);

        List<Item> resultado = q.getResultList();

        System.out.println("Lista de items dentro do price range " + resultado);
    }

    public void Procurar_Items_Data(Date data) {
        Query q = em.createQuery("from Item i where i.published_date > :d");
        q.setParameter("d", data);

        List<Item> resultado = q.getResultList();

        System.out.println("Lista de items publicados apos a data " + resultado);
    }


    //NOTA: Só o dono do item é que vai poder editar a info do mesmo, logo temos que verificar
    //se o id do user da sessao e igual ao do dono do item


    //ITEM OPERATIONS
    /*public void Listar_Items_aVenda(); //Listar todos os items que tenho para venda, ordenados por data de insercao
    public void Inserir_Novo_Item(String name, String category, String country_of_origin); //Falta a fotografia
    public void Editar_Item_Info(String name, String category, String country_of_origin); //Falta a fotografia

    //USER OPERATIONS
    public void Editar_User_Info(String name, int age, String email, String country);
    public void Delete_UserAccount(); //Apagar primeiro os items do user e depois apagar o user
    */


}
