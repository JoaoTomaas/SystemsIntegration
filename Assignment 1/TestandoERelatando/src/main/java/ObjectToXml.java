import sun.plugin.dom.core.Document;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javax.xml.*;
import javax.xml.bind.PropertyException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ObjectToXml {
    public String xml_file;
    public  OwnerList ownerList = new OwnerList();
    public  CarList carList = new CarList();

    public ObjectToXml(String xml_file, OwnerList ownerList) {
        this.xml_file = xml_file;
        this.ownerList = ownerList;
        /*for(Owner e:ownerList.getOwner_list()){
            System.out.println(e.getName());
        }*/
    }
    public ObjectToXml(String xml_file, CarList carList) {
        this.xml_file = xml_file;
        this.carList = carList;
        /*for(Owner e:ownerList.getOwner_list()){
            System.out.println(e.getName());
        }*/
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
    public String teste2()  {
        try {
            JAXBContext contextObj = JAXBContext.newInstance(CarList.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


            marshallerObj.marshal(carList, new FileOutputStream(xml_file));
            //XML to string
            String filename = xml_file;
            BufferedReader str = new BufferedReader(new FileReader(new File(filename)));
            String line;
            String new_str = "";
            while ((line = str.readLine()) != null) {
                new_str += line.trim();
            }
            return new_str;
            //Para testar o XML
            //Owner own = new Owner(1234, "Johnny", 918907623, "Moro aqui");
            //Car c = new Car(123, "Ferrari", "Pista", 4000, 670, 24, "27-49-UC");
        }
        catch  (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return "";
    }

}
