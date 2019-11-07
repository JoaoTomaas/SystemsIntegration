package ejb;

import data.Item;
import data.Utilizador;
import dto.ItemDTO;
import dto.ItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@LocalBean
@Stateless(name = "ItemEJB")
public class ItemBean {
    @PersistenceContext(name="Users")
    EntityManager em;

    final Logger logger = LoggerFactory.getLogger(ItemBean.class);

    public ItemBean() {}




    public List<ItemDTO> Procurar_Items_Categoria(String categoria) {
        Query q = em.createQuery("from Item i where i.category = :c");
        q.setParameter("c", categoria);

        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        //System.out.println("Lista de items por categoria " + resultado);
        return resultado;
    }

    //Procurar pelo pais do utilizador
    public List<ItemDTO> Procurar_Items_Country(String email) {
        Query q = em.createQuery("from Item i where i.country_of_origin = (select country from Utilizador u where u.email = :e)");
        q.setParameter("e", email);

        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
            logger.debug("ESTA A CRIAR O DTO NO COUNTRY");
        }
        return resultado;
    }

    public List<ItemDTO> Procurar_Items_PriceRange(float lower_bound, float upper_bound) {
        Query q = em.createQuery("from Item i where i.price between :lb and  :ub");
        q.setParameter("lb", lower_bound);
        q.setParameter("ub", upper_bound);

        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }

        return resultado;
    }

    public List<ItemDTO> Procurar_Items_Data(Date data) {
        Query q = em.createQuery("from Item i where i.published_date > :d");
        q.setParameter("d", data);

        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
            logger.info("DTO preparado para ser enviado para a servlet");
        }
        //System.out.println("Lista de items publicados apos a data " + resultado);
        return resultado;
    }


    //LISTAR ITEMS
    //Listar todos os items que tenho para venda, ordenados por data de insercao
    public List<ItemDTO> Listar_Items_aVenda(String email){ //Ver como passar o utilizador
        //System.out.println("olalalalallalala");
        Query q = em.createQuery("from Item i where i.utilizador.email = :e order by published_date");
        q.setParameter("e", email);

        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            //System.out.println("teste ahahahah"+e.getName());
            logger.debug("ESTA A CRIAR O DTO NO LISTAR_ITEMS_AVENDA");
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }

        logger.debug("A sair da funcao");
        return resultado;
    }

    public List<ItemDTO> Procurar_Items(String nome) {
        Query q = em.createQuery("from Item i where i.name like CONCAT('%',:n,'%')");
        q.setParameter("n", nome);
        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        //System.out.println("Lista de items " + resultado);
        return resultado;
    }


    //ITEM OPERATIONS
    //Utilizador coloca um item para venda
    public void Inserir_Novo_Item(String name, String category, String country_of_origin, float price, String email_user, String path){//Falta a fotografia

        //Data do sistema
        Date published_date = new Date();

        Item new_item = new Item(name, category, country_of_origin, price, published_date);
        Query q = em.createQuery("select id from Utilizador u where u.email = :e");
        q.setParameter("e", email_user);
        new_item.setUtilizador(em.find(Utilizador.class, q.getSingleResult()));
        em.persist(new_item);
    }

    //NOTA: Só o dono do item é que vai poder editar a info do mesmo, logo temos que verificar
    //se o id do user da sessao e igual ao do dono do item
    public void Editar_Item_Info(int id, String name, String category, String country_of_origin, float price){
        Query q = em.createQuery("update Item i set i.name = :n , i.category = :ct,i.country_of_origin = :c, i.price = :p where i.id = :i");
        q.setParameter("n", name);
        q.setParameter("ct", category);
        q.setParameter("c", country_of_origin);
        q.setParameter("p", price);

        q.setParameter("i", id);

        q.executeUpdate();

    } //TESTAR
    //Falta a fotografia
    //Penso que nao faz sentido alterar a data de publicacao

    public void Apagar_Item(int id){
        //Decidir como é que vamos procurar o item, talvez apresentar uma lista e escolher um deles
        Query q = em.createQuery("from Item i where i.id = :i");
        q.setParameter("i", id);

        Item result = (Item) q.getSingleResult();
        em.remove(result);

    }

    public List<ItemDTO> Listar_Detalhes_Item (int id){
        Query q = em.createQuery("from Item i where i.id = :i");
        q.setParameter("i", id);
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            logger.debug("A testar o DTO no Listar_Detalhes_Item");
            //System.out.println("teste ahahahah"+e.getName());
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        return resultado;
    }


    public List<ItemDTO> Procurar_Items1(String nome,int flag) {
        Query q;
        if(flag==1){
            q = em.createQuery("from Item i where i.name like CONCAT('%',:n,'%')  order by name asc ");

        }
        else {
            q = em.createQuery("from Item i where i.name like CONCAT('%',:n,'%')  order by name desc ");

        }

        q.setParameter("n", nome);
        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        //System.out.println("Lista de items " + resultado);
        return resultado;
    }

    public List<ItemDTO> Procurar_Items2(String nome,int flag) {
        Query q;
        if(flag==1){
            q = em.createQuery("from Item i where i.name like CONCAT('%',:n,'%')  order by price asc ");

        }
        else {
            q = em.createQuery("from Item i where i.name like CONCAT('%',:n,'%')  order by price desc ");
        }

        q.setParameter("n", nome);
        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        //System.out.println("Lista de items " + resultado);
        return resultado;
    }
    public List<ItemDTO> Procurar_Items3(String nome,int flag) {
        Query q;
        if(flag==1){
            q = em.createQuery("from Item i where i.name like CONCAT('%',:n,'%')  order by published_date ASC ");
        }
        else{
            q = em.createQuery("from Item i where i.name like CONCAT('%',:n,'%')  order by published_date desc ");
        }
        q.setParameter("n", nome);
        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        //System.out.println("Lista de items " + resultado);
        return resultado;
    }


    public List<ItemDTO> Procurar_Items_Categoria1(String categoria,String nome,int flag) {
        Query q;
        if(flag==1){
            q = em.createQuery("from Item i where i.category = :c and name like CONCAT('%',:n,'%') order by name asc ");

        }
        else {
            q = em.createQuery("from Item i where i.category = :c and name like CONCAT('%',:n,'%') order by name desc ");
        }
        q.setParameter("c", categoria);
        q.setParameter("n", nome);
        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        //System.out.println("Lista de items por categoria " + resultado);
        return resultado;
    }
    public List<ItemDTO> Procurar_Items_Categoria2(String categoria,String nome,int flag) {
        Query q;
        if(flag==1){
            q = em.createQuery("from Item i where i.category = :c and name like CONCAT('%',:n,'%') order by price asc ");

        }
        else {
            q = em.createQuery("from Item i where i.category = :c and name like CONCAT('%',:n,'%') order by price desc ");
        }
        q.setParameter("c", categoria);
        q.setParameter("n", nome);
        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        //System.out.println("Lista de items por categoria " + resultado);
        return resultado;
    }
    public List<ItemDTO> Procurar_Items_Categoria3(String categoria,String nome,int flag) {
        Query q;
        if(flag==1){
            q = em.createQuery("from Item i where i.category = :c and name like CONCAT('%',:n,'%') order by published_date asc ");

        }
        else {
            q = em.createQuery("from Item i where i.category = :c and name like CONCAT('%',:n,'%') order by published_date desc ");
        }
        q.setParameter("c", categoria);
        q.setParameter("n", nome);
        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        //System.out.println("Lista de items por categoria " + resultado);
        return resultado;
    }

    public List<ItemDTO> Procurar_Items_Country1(String nome,String email,int flag) {
        Query q;
        if(flag==1){
            q = em.createQuery("from Item i where i.country_of_origin = (select country from Utilizador u where u.email = :e) and i.name like CONCAT('%',:n,'%') order by name asc ");

        }
        else {
            q = em.createQuery("from Item i where i.country_of_origin = (select country from Utilizador u where u.email = :e) and i.name like CONCAT('%',:n,'%') order by name desc ");
        }
        q.setParameter("e", email);
        q.setParameter("n", nome);
        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        return resultado;
    }
    public List<ItemDTO> Procurar_Items_Country2(String nome,String email,int flag) {
        Query q;
        if(flag==1){
            q = em.createQuery("from Item i where i.country_of_origin = (select country from Utilizador u where u.email = :e) and i.name like CONCAT('%',:n,'%')  order by price asc ");
        }
        else {
            q = em.createQuery("from Item i where i.country_of_origin = (select country from Utilizador u where u.email = :e) and i.name like CONCAT('%',:n,'%') order by price desc");

        }
        q.setParameter("e", email);
        q.setParameter("n", nome);
        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        return resultado;
    }
    public List<ItemDTO> Procurar_Items_Country3(String nome,String email,int flag) {
        Query q;
        if(flag==1){
            q = em.createQuery("from Item i where i.country_of_origin = (select country from Utilizador u where u.email = :e) and i.name like CONCAT('%',:n,'%')  order by published_date asc ");
        }
        else {
            q = em.createQuery("from Item i where i.country_of_origin = (select country from Utilizador u where u.email = :e) and i.name like CONCAT('%',:n,'%') order by published_date desc");

        }
        q.setParameter("e", email);
        q.setParameter("n", nome);
        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        return resultado;
    }

    public List<ItemDTO> Procurar_Items_PriceRange1(String nome,int lower_bound, int upper_bound,int flag) {
        Query q;
        if(flag==1){
            q = em.createQuery("from Item i where i.name like CONCAT('%',:n,'%') and i.price between :lb and  :ub  order by name asc ");
        }
        else{
            q = em.createQuery("from Item i where i.name like CONCAT('%',:n,'%') and i.price between :lb and  :ub  order by name desc ");
        }
        q.setParameter("lb", lower_bound);
        q.setParameter("ub", upper_bound);
        q.setParameter("n", nome);

        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }

        return resultado;
    }
    public List<ItemDTO> Procurar_Items_PriceRange2(String nome,int lower_bound, int upper_bound,int flag) {
        Query q;
        if(flag==1){
            q = em.createQuery("from Item i where i.name like CONCAT('%',:n,'%') and i.price between :lb and  :ub  order by price asc ");
        }
        else{
            q = em.createQuery("from Item i where i.name like CONCAT('%',:n,'%') and i.price between :lb and  :ub  order by price desc ");
        }
        q.setParameter("lb", lower_bound);
        q.setParameter("ub", upper_bound);
        q.setParameter("n", nome);

        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }

        return resultado;
    }
    public List<ItemDTO> Procurar_Items_PriceRange3(String nome,int lower_bound, int upper_bound,int flag) {
        Query q;
        if(flag==1){
            q = em.createQuery("from Item i where i.name like CONCAT('%',:n,'%') and i.price between :lb and  :ub  order by published_date asc ");
        }
        else{
            q = em.createQuery("from Item i where i.name like CONCAT('%',:n,'%') and i.price between :lb and  :ub  order by published_date desc ");
        }
        q.setParameter("lb", lower_bound);
        q.setParameter("ub", upper_bound);
        q.setParameter("n", nome);

        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }

        return resultado;
    }

    public List<ItemDTO> Procurar_Items_Data1(String nome,Date data,int flag) {
        Query q;
        if(flag==1){
            q= em.createQuery("from Item i where i.published_date > :d and i.name like CONCAT('%',:n,'%') order by name asc ");
        }
        else{
            q= em.createQuery("from Item i where i.published_date > :d and i.name like CONCAT('%',:n,'%') order by name desc ");
        }
        q.setParameter("d", data);
        q.setParameter("n", nome);

        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        //System.out.println("Lista de items publicados apos a data " + resultado);
        return resultado;
    }

    public List<ItemDTO> Procurar_Items_Data2(String nome,Date data,int flag) {
        Query q;
        if(flag==1){
            q= em.createQuery("from Item i where i.published_date > :d and i.name like CONCAT('%',:n,'%') order by price asc ");
        }
        else{
            q= em.createQuery("from Item i where i.published_date > :d and i.name like CONCAT('%',:n,'%') order by price desc ");
        }
        q.setParameter("d", data);
        q.setParameter("n", nome);

        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        //System.out.println("Lista de items publicados apos a data " + resultado);
        return resultado;
    }


    public List<ItemDTO> Procurar_Items_Data3(String nome,Date data,int flag) {
        Query q;
        if(flag==1){
            q= em.createQuery("from Item i where i.published_date > :d and i.name like CONCAT('%',:n,'%') order by name asc ");
        }
        else{
            q= em.createQuery("from Item i where i.published_date > :d and i.name like CONCAT('%',:n,'%') order by name desc ");
        }

        q.setParameter("d", data);
        q.setParameter("n", nome);

        @SuppressWarnings("unchecked")
        List<Item> resultado_intermedio = q.getResultList();
        //List<ItemDTO> resultado= ItemMapper.INSTANCE.listItemToListItemDto(resultado_intermedio);
        List<ItemDTO> resultado = new ArrayList<ItemDTO>();
        for(Item e:resultado_intermedio){
            resultado.add(ItemMapper.INSTANCE.itemToItemDto(e));
        }
        //System.out.println("Lista de items publicados apos a data " + resultado);
        return resultado;
    }
    public List<String> Procurar_Categoria() {
        Query q = em.createQuery("select distinct category from Item");
        //System.out.println("Lista de items publicados apos a data " + resultado);
        return (List<String>)q.getResultList();
    }



    public ItemDTO get_item_dto (int id){
        Query q = em.createQuery("from Item i where i.id = :i");
        q.setParameter("i", id);
        Item resultado = (Item) q.getSingleResult();
        ItemDTO idto = ItemMapper.INSTANCE.itemToItemDto(resultado);
        return idto;
    }


}