import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name="carlist")
@XmlAccessorType(XmlAccessType.FIELD)

public class CarList {
    @XmlElement(name="car")
    private ArrayList<Car> car_list = null;

    public ArrayList<Car> getCar_list() { return car_list; }

    public void setCar_list(ArrayList<Car> car_list) { this.car_list = car_list; }
}
