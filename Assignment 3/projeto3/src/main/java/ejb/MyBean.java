package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import data.*;


import java.util.List;


@Stateless
@LocalBean
public class MyBean {
    @PersistenceContext(name = "Items")
    EntityManager em;

    //final Logger logger = LoggerFactory.getLogger(MyBean.class);

    public MyBean() {
    }

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

    public List<ResultsTopic> getItemStats(){
        Query q = em.createQuery("from ResultsTopic");
        @SuppressWarnings("unchecked")
        List <ResultsTopic> lista_stats = q.getResultList();

        return lista_stats;
    }

    public List<TotalTopic> getTotalStats(){
        Query q = em.createQuery("from TotalTopic ");
        @SuppressWarnings("unchecked")
        List <TotalTopic> lista_stats = q.getResultList();

        return lista_stats;
    }

    public List<AverageItemTopic> getAvgItem(){
        Query q = em.createQuery("from AverageItemTopic ");
        @SuppressWarnings("unchecked")
        List <AverageItemTopic> lista_stats = q.getResultList();

        return lista_stats;
    }

    public List<AverageTotalTopic> getAvgTotal(){
        Query q = em.createQuery("from AverageTotalTopic ");
        @SuppressWarnings("unchecked")
        List <AverageTotalTopic> lista_stats = q.getResultList();

        return lista_stats;
    }

    public List<HighestProfTopic> getItemHighestProfit(){
        Query q = em.createQuery("from HighestProfTopic ");
        @SuppressWarnings("unchecked")
        List <HighestProfTopic> lista_stats = q.getResultList();

        return lista_stats;
    }

    public List<WindowTopic> getWindowedStats(){
        Query q = em.createQuery("from WindowTopic ");
        @SuppressWarnings("unchecked")
        List <WindowTopic> lista_stats = q.getResultList();

        return lista_stats;
    }

    public List<CountrySalesTopic> getCountryName(){
        Query q = em.createQuery("from CountrySalesTopic");
        @SuppressWarnings("unchecked")
        List <CountrySalesTopic> lista_stats = q.getResultList();

        return lista_stats;
    }
}








    /*public Course getCourse(int id) {
        return this.lc.get(id);
    }*/

    /*public ListCountries getListCountries() {
        return this.lct;
    }*/



//ESTATISTICAS
/*      -> ResultsTopic
        -> TotalTopic
        -> AverageItemTopic
        -> AverageTotalTopic
        -> HighestProfTopic
        -> HourTopic
        -> CountrySalesTopic
 */
