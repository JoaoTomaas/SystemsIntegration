package results;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ManageResultsApplication {

    public static void main(String[] args) throws InterruptedException, IOException {

        String inputtopic_sales = "salestopic";
        String inputtopic_purchases = "purchasestopic";

        String output_topic = "resultstopic";

        java.util.Properties propd = new Properties();
        propd.put(StreamsConfig.APPLICATION_ID_CONFIG, "manageresults-application");
        propd.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        propd.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        propd.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        //Input topic que recebe as compras feitas pelos clientes
        KStream<String, String> stream_cliente = builder.stream(inputtopic_sales);

        //Input topic que recebe as compras feitas aos fornecedores
        KStream<String, String> stream_fornece = builder.stream(inputtopic_purchases);

        //Aquele join do mal
        KStream<String, String> joined = stream_cliente.leftJoin(stream_fornece,
                (leftValue, rightValue) -> "left=" + leftValue + ", right=" + rightValue, // ValueJoiner
                JoinWindows.of(TimeUnit.MINUTES.toMillis(5)),
                Joined.with(
                        Serdes.String(),    //key
                        Serdes.String(),    //left value
                        Serdes.String())    //right value
        );

        joined.mapValues((k, v) -> "" + k + " -> " + v).to(output_topic, Produced.with(Serdes.String(), Serdes.String()));





        //Preco pago aos fornecedores (Expenses)
        //KTable<String, Double> trata = stream_cliente.mapValues(v -> transformValue(v)).groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).reduce((v1, v2) -> v1 + v2);

        //trata.toStream().mapValues((k, v) -> "" + k + " -> " + v).to(output_topic, Produced.with(Serdes.String(), Serdes.String()));

        KafkaStreams streams = new KafkaStreams(builder.build(), propd);
        streams.start();


    }





    public static Double transformValue(String s){
        JSONObject obj = new JSONObject(s);
        return (obj.getDouble("preco") * obj.getDouble("unidades"));
    }


/*

        JSONObject dados_compra = new JSONObject();
        dados_compra.put("preco", 10);
        dados_compra.put("unidades",5);
        String jsonresult = dados_compra.toString();
        System.out.print(jsonresult);

 */




    //Compute expenses

    //Compute profit



    //Get the average amount spent in each purchase (separated by item)

    //Get the average amount spent in each purchase (aggregated for all items)

    //Get the item with the highest profit of all (only one if there is a tie)

    //Get the total revenue in the last hour1 (use a tumbling time window).

    //Get the total expenses in the last hour (use a tumbling time window)

    //Get the total profits in the last hour (use a tumbling time window).



    //Metodo para o serializer





    //Metodo para o desirialiazer



}


/*Aquele Join do Mal

KeyValue<K, LV> leftRecord = ...;
KeyValue<K, RV> rightRecord = ...;
ValueJoiner<LV, RV, JV> joiner = ...;

KeyValue<K, JV> joinOutputRecord = KeyValue.pair(
    leftRecord.key, // by definition, leftRecord.key == rightRecord.key
        joiner.apply(leftRecord.value, rightRecord.value)
  );
 */