import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = {"id", "brand", "model", "engine_size", "power", "consumption", "plate", "owner_id"})
public class Car {
    private int id;
    private String brand;
    private String model;
    private int engine_size;
    private int power;
    private int consumption;
    private String plate;
    private int owner_id; //Foreign key

    public Car(){}

    public Car(int id, String brand, String model, int engine_size, int power, int consumption, String plate, int owner_id){
        super();
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.engine_size = engine_size;
        this.power = power;
        this.consumption = consumption;
        this.plate = plate;
        this.owner_id = owner_id;
    }

    @XmlAttribute
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    @XmlElement
    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }

    @XmlElement
    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model = model;
    }

    @XmlElement
    public int getEngine_size() {
        return engine_size;
    }
    public void setEngine_size(int engine_size) {
        this.engine_size = engine_size;
    }

    @XmlElement
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }

    @XmlElement
    public int getConsumption() {
        return consumption;
    }
    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    @XmlElement
    public String getPlate() {
        return plate;
    }
    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }
}
