import com.github.javafaker.Faker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class NameGenerator {
    public static String file="Owner";
    public static int repeticoes=1000;


    public static void main(String [ ] args) throws IOException {
        Random rand = new Random();
        file=file+Integer.toString(repeticoes)+".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for(int i=0;i<repeticoes;i++){
            Faker faker = new Faker();

            String name = faker.name().fullName(); // Miss Samanta Schmidt
            String firstName = faker.name().firstName(); // Emory
            String lastName = faker.name().lastName(); // Barton

            String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
            writer.write(Integer.toString(i)+"\t"+name+"\t"+rand.nextInt(1000000000)+"\t"+streetAddress+"\n");

        }
        writer.close();

    }
}
