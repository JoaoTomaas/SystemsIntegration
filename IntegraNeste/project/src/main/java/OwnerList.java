import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name="ownerlist")
@XmlAccessorType(XmlAccessType.FIELD)

public class OwnerList {

    @XmlElement(name="owner")
    private ArrayList<Owner> owner_list = null;

    public ArrayList<Owner> getOwner_list() { return owner_list; }

    public void setOwner_list(ArrayList<Owner> owner_list) { this.owner_list = owner_list; }

}
