package ejb;

import data.Item;
import data.Utilizador;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@LocalBean
@Stateless(name = "ItemEJB")
public class ItemBean {
    @PersistenceContext(name="Users")
    EntityManager em;

    public ItemBean() {}

    //PROCURAR ITEMS (Acho que nao ha necessidade de levar Override)
    public List<Item> Procurar_Items() {
        Query q = em.createQuery("from Item i");
        @SuppressWarnings("unchecked")
        List<Item> resultado = q.getResultList();

        //System.out.println("Lista de items " + resultado);
        return resultado;
    }

    public List<Item> Procurar_Items_Categoria(String categoria) {
        Query q = em.createQuery("from Item i where i.category = :c");
        q.setParameter("c", categoria);

        @SuppressWarnings("unchecked")
        List<Item> resultado = q.getResultList();

        //System.out.println("Lista de items por categoria " + resultado);
        return resultado;
    }

    //Procurar pelo pais do utilizador
    public List<Item> Procurar_Items_Country(String email) {
        Query q = em.createQuery("from Item i where i.country_of_origin = (select country from Utilizador u where u.email = :e)");
        q.setParameter("e", email);

        @SuppressWarnings("unchecked")
        List<Item> resultado = q.getResultList();

        //System.out.println("Lista de items por país de origem " + resultado);
        return resultado;
    }

    public List<Item> Procurar_Items_PriceRange(int lower_bound, int upper_bound) {
        Query q = em.createQuery("from Item i where i.price between :lb and  :ub");
        q.setParameter("lb", lower_bound);
        q.setParameter("ub", upper_bound);

        List<Item> resultado = q.getResultList();

        //System.out.println("Lista de items dentro do price range " + resultado);
        return resultado;
    }

    public List<Item> Procurar_Items_Data(Date data) {
        Query q = em.createQuery("from Item i where i.published_date > :d");
        q.setParameter("d", data);

        List<Item> resultado = q.getResultList();

        //System.out.println("Lista de items publicados apos a data " + resultado);
        return resultado;
    }


    //LISTAR ITEMS
    //Listar todos os items que tenho para venda, ordenados por data de insercao
    public List<Item> Listar_Items_aVenda(String email){ //Ver como passar o utilizador
        Query q = em.createQuery("from Item i where i.utilizador.email = :e order by published_date");
        q.setParameter("e", email);

        List<Item> resultado = q.getResultList();

        //System.out.println("Lista de items que o user " + email + " tem para venda:" + resultado);
        return resultado;
    } //TESTAR


    //ITEM OPERATIONS
    //Utilizador coloca um item para venda
    public void Inserir_Novo_Item(String name, String category, String country_of_origin, int price, Date published_date){//Falta a fotografia
        Item new_item = new Item(name, category, country_of_origin, price, published_date);
        em.persist(new_item);
    }

    public void Inserir_Novo_Item2(String name, String category, String country_of_origin, int price, Date published_date,String name_utilizador){//Falta a fotografia

        Item new_item = new Item(name, category, country_of_origin, price, published_date);
        Query q = em.createQuery("select id from Utilizador u where u.name = :n");
        q.setParameter("n", name_utilizador);
        new_item.setUtilizador(em.find(Utilizador.class, (int)q.getSingleResult()));
        em.persist(new_item);
    }

    //NOTA: Só o dono do item é que vai poder editar a info do mesmo, logo temos que verificar
    //se o id do user da sessao e igual ao do dono do item
    public void Editar_Item_Info(int id, String name, String category, String country_of_origin, int price){
        Query q = em.createQuery("update Item i set i.name = :n , i.category = :ct,i.country_of_origin = :c, i.price = :p where i.id = :i");
        q.setParameter("n", name);
        q.setParameter("ct", category);
        q.setParameter("c", country_of_origin);
        q.setParameter("p", price);

        q.setParameter("i", id);

        //Ver como é que hei de procurara, i.e., estipular bem as primary keys
    } //TESTAR
    //Falta a fotografia
    //Penso que nao faz sentido alterar a data de publicacao

    public void Apagar_Item(String name, String category, String country){
        //Decidir como é que vamos procurar o item, talvez apresentar uma lista e escolher um deles
        Query q = em.createQuery("delete from Item i where i.name = :n and i.category = :c and i.country_of_origin = :o");
        q.setParameter("n", name);
        q.setParameter("c", category);
        q.setParameter("o", country);
    } //TESTAR




    //USADO APENAS PARA TESTAR
    public Date getData(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);

        Date d = cal.getTime();
        return d;
    }


    public void insert_item_test(){
        Item first_item = new Item("bola de tenis", "desporto", "Tailândia", 2, getData(3,5,2018));
        em.persist(first_item);
    }
}