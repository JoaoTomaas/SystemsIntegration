import com.project.protob.CarOwnerProtoB;
import com.project.protob.CarOwnerProtoB.Owner;
import com.project.protob.CarOwnerProtoB.OwnerList;
import com.project.protob.CarOwnerProtoB.CarList;
import com.project.protob.CarOwnerProtoB.Car;
import java.io.*;

public class SendCarList {

    static Car PromptForAddress (BufferedReader stdin, PrintStream stdout) throws IOException{
        Car.Builder car = Car.newBuilder();

        car.setId(242);
        car.setBrand("Porsche");
        car.setModel("Macan");
        car.setEngineSize(3000);
        car.setPower(520);
        car.setConsumption(28);
        car.setPlate("11-CD-46");
        car.setOwnerId(300);

        return car.build();
    }

    public static void main (String [] args) throws Exception {
        CarList.Builder cars  = CarList.newBuilder();

        //Ler a lista de carros existente
        try{
            //cars.mergeFrom(new FileInputStream(args[0]));
            cars.mergeFrom(new FileInputStream("ficheiro1.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("ficheiro.txt" + "Ficheiro nao encontrado, a criar um novo ficheiro...");
        }

        //Adicionar um carro à lista de carros
        cars.addCars(PromptForAddress(new BufferedReader(new InputStreamReader(System.in)), System.out));

        //Escrever a nova lista de carros no ficheiro de texto
        FileOutputStream output = new FileOutputStream("ficheiro1.txt");
        cars.build().writeTo(output);

        output.close();
    }

}