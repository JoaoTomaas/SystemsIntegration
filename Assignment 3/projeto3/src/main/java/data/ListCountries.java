package data;


import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="ListCountries")
public class ListCountries implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Country> countries;

    public ListCountries(){
        this.countries = new ArrayList<>();
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void addCountry(Country ct) {
        this.countries.add(ct);
    }

    //Falta addcountries e get
}
