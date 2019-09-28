import com.project.protob.CarOwnerProtoB;
import com.project.protob.CarOwnerProtoB.Owner;
import com.project.protob.CarOwnerProtoB.OwnerList;
import com.project.protob.CarOwnerProtoB.CarList;
import com.project.protob.CarOwnerProtoB.Car;
import java.io.*;

public class SendCarList {

    static Car PromptForAddress (BufferedReader stdin, PrintStream stdout) throws IOException{
        Car.Builder car = Car.newBuilder();

        car.setId(123);
        car.setBrand("Ferrari");
        car.setModel("488 Pista");
        car.setEngineSize(4000);
        car.setPower(600);
        car.setConsumption(20);
        car.setPlate("OJ-90-87");
        car.setOwnerId(244);

        return car.build();
    }

    public static void main (String [] args) throws Exception {
        CarList.Builder cars  = CarList.newBuilder();

        //args[0] = "ficheiro.txt";

        //Ler a lista de carros existente
        try{
            //cars.mergeFrom(new FileInputStream(args[0]));
            cars.mergeFrom(new FileInputStream("ficheiro.txt"));
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