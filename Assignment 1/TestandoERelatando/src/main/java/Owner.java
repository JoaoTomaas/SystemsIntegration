import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "name", "telephone", "address"})
public class Owner {
    private int id;
    private String name;
    private int telephone;
    private String address;

    public Owner(){}

    public Owner (int id, String name, int telephone, String address){
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
    }

    @XmlAttribute
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    @XmlElement
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    @XmlElement
    public int getTelephone(){
        return telephone;
    }
    public void setTelephone(int telephone){
        this.telephone = telephone;
    }

    @XmlElement
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

}
