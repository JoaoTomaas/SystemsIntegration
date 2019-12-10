package results;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.io.IOException;
import java.util.Properties;

public class ManageResultsApplication {

    public static void main(String[] args) throws InterruptedException, IOException {

        String inputtopic_sales = "salestopic";
        String inputtopic_purchases = "purchasestopic";

        String output_topic = "resultstopic";

        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "manageresults-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        //Input topic que recebe as compras feitas aos fornecedores
        KStream<String, Long> lines_fornece = builder.stream(inputtopic_purchases);

        //Input topic que recebe as compras feitas pelos clientes
        KStream<String, Long> lines_cliente = builder.stream(inputtopic_sales);





    }




    //Metodo para o serializer





    //Metodo para o desirialiazer



}
