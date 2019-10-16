import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import javax.xml.*;

public class ObjectToXml {

    static OwnerList ownerList = new OwnerList();

    static{
        ownerList.setOwner_list(new ArrayList<Owner>());

        Owner o1 = new Owner(1234, "Johnny", 918907623, "Moro aqui");
        Owner o2 = new Owner(5678, "Cacholinha", 918765234, "Marrocos");

        ownerList.getOwner_list().add(o1);
        ownerList.getOwner_list().add(o2);
    }

    public static void main(String[] args) throws Exception {
        JAXBContext contextObj = JAXBContext.newInstance(OwnerList.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        marshallerObj.marshal(ownerList, new FileOutputStream("try.xml"));

        //Para testar o XML
        //Owner own = new Owner(1234, "Johnny", 918907623, "Moro aqui");
        //Car c = new Car(123, "Ferrari", "Pista", 4000, 670, 24, "27-49-UC");
    }
}
