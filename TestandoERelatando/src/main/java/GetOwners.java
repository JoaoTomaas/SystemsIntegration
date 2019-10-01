import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GetOwners {
    public String file;
    BufferedReader br ;

    public GetOwners(String file) {
        this.file = file;
        try {
            br=new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public OwnerList work() throws IOException {
        String st;
        OwnerList devolve=new OwnerList();
        ArrayList<Owner> array=new ArrayList<>();
        String [] aux;
        while ((st = br.readLine()) != null) {
            aux = st.split("\\t+");
            array.add(new Owner(Integer.parseInt(aux[0]),aux[1],Integer.parseInt(aux[2]),aux[3]));
        }
        devolve.setOwner_list(array);
        return devolve;
    }
}

