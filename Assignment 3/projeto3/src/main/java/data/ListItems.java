package data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name="ListItems")
public class ListItems implements Serializable{
    private static final long serialVersionUID = 1L;

    private List <Item> items;

    public ListItems(){
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    //Falta addItems e get

}
