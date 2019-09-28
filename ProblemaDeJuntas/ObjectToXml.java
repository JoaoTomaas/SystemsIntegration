import sun.plugin.dom.core.Document;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import javax.xml.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ObjectToXml {
    public String xml_file;
    static OwnerList ownerList = new OwnerList();

    static{
        ownerList.setOwner_list(new ArrayList<Owner>());

        Owner o1 = new Owner(1234, "Johnnyyy", 918907623, "Moro aqui");
        Owner o2 = new Owner(5678, "Cacholinha", 918765234, "Marrocos");

        ownerList.getOwner_list().add(o1);
        ownerList.getOwner_list().add(o2);
    }

    public ObjectToXml(String xml_file) {
        this.xml_file = xml_file;
    }
//<<<<<<<<<<<<<<<<---------------------->>>>>>>>>>>>>>>>>>>
    public String teste() throws Exception {
        JAXBContext contextObj = JAXBContext.newInstance(OwnerList.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        marshallerObj.marshal(ownerList, new FileOutputStream(xml_file));
        //XML to string
        String filename = xml_file;
        BufferedReader str = new BufferedReader(new FileReader(new File(filename)));
        String line;
        String new_str = "";
        while((line=str.readLine())!= null){
            new_str += line.trim();
        }
        return new_str;
        //Para testar o XML
        //Owner own = new Owner(1234, "Johnny", 918907623, "Moro aqui");
        //Car c = new Car(123, "Ferrari", "Pista", 4000, 670, 24, "27-49-UC");
    }


}
