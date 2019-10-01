import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

public class CarGenerator {
    public static String file="Car";
    public static int repeticoes=1000;
    public static String[] brands = new String[]{"Mercedes-Benz","Toyota","BMW","Volkswagen","Honda","Nissan","Porsche","Ford","Audi","Renault","CHEVROLET","Volvo","Land Rover","Hyundai","Lexus","Subaru","Haval","Ferrari","Geely","Tesla","Kia","BUICK","Suzuki","ISUZU","Daihatsu","Aston Martin","Mazda","MAN","BYD","MINI","Maruti Suzuki","Jaguar","Fiat","Polaris","CADILLAC","Peugeot","Jeep","Harley-Davidson","Bentley","Scania","GMC","Mahindra","Hino","Lincoln","Acura","Tata Motors","Bajaj Auto","JAC Motors","Changan","Hero"};
    public static String[] models = new String[]{"Integra","TL","Eagle","Javelin","3000","Z8","SS","Nova","Volt","812","Coupe DeVille","El Dorado","308 GTS","Del Sol","S2000","H2","XJ220","Countach","RX-7","442","Tatra V8","MR2","Type 2","Gallardo","X5","MX-5","8C Spider","GT","Multipla","F-Type V6S","CLK","GT86","Evo III","5-Series E39","Carrera GT","Enzo","V12 Vantage","12C Spider","Discovery","S-Class","Continental GT","A2","Hilux","Murcielago","Impreza P1","Evo VI","Fiesta ST","Prius","911","Phantom"};
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static SecureRandom rnd = new SecureRandom();

    static String randomString( int len ){

        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        sb.append("-");
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
    public static void main(String [ ] args) throws IOException {
        Random rand = new Random();
        file=file+Integer.toString(repeticoes)+".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for(int i=0;i<repeticoes;i++){
            String brand=brands[rand.nextInt(brands.length)];
            String model=models[rand.nextInt(models.length)];
            writer.write(Integer.toString(i)+"\t"+brand+"\t"+model+"\t"+(rand.nextInt(4000)+750)+"\t"+(rand.nextInt(1250)+50)+"\t"+(rand.nextInt(15)+2)+"\t"+randomString(rand.nextInt(5)+2)+"\t"+rand.nextInt(repeticoes)+"\n");
        }

        writer.close();

    }
}
