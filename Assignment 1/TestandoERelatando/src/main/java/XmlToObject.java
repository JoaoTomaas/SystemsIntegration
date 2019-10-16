import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;

public class XmlToObject {
    public String xml_file;
    public BufferedWriter writer;
    public XmlToObject(String xml_file)  {
        this.xml_file = xml_file;
        try {
            writer =new BufferedWriter(new FileWriter(xml_file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //<<---------------------------------->>
    public OwnerList work(String input) {
        try {
            writer.write(input);
            writer.close();
            File file = new File(xml_file);
            JAXBContext jaxbContext = JAXBContext.newInstance(OwnerList.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            OwnerList e=(OwnerList) jaxbUnmarshaller.unmarshal(file);
            return e;

        } catch (JAXBException | IOException e) {e.printStackTrace(); }
        return null;
    }


    public CarList work2(String input) {
        try {
            writer.write(input);
            writer.close();
            File file = new File(xml_file);
            JAXBContext jaxbContext = JAXBContext.newInstance(CarList.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            CarList e=(CarList) jaxbUnmarshaller.unmarshal(file);

           /* for (Owner temp : e.getOwner_list()) {
                System.out.println(temp.getName());
            }*/
            return e;

        } catch (JAXBException | IOException e) {e.printStackTrace(); }
        return null;
    }
}
