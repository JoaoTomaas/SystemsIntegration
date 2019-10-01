import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GetCars {
    public String file;
    BufferedReader br ;

    public GetCars(String file) {
        this.file = file;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public CarList work() throws IOException{
        String st;
        CarList devolve=new CarList();
        ArrayList<Car> array=new ArrayList<>();
        String [] aux;
        while ((st = br.readLine()) != null) {
            aux = st.split("\\t+");
            array.add(new Car(Integer.parseInt(aux[0]),aux[1],aux[2],Integer.parseInt(aux[3]),Integer.parseInt(aux[4]),Integer.parseInt(aux[5]),aux[6],Integer.parseInt(aux[7])));
        }
        devolve.setCar_list(array);
        return devolve;
    }
}


