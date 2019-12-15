package results;

import jdk.nashorn.internal.runtime.OptimisticReturnFilters;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import org.apache.kafka.streams.kstream.internals.WindowedSerializer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.security.acl.Group;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ManageResultsApplication {

    private static JsonConverter2 aux=new JsonConverter2();

    public static void main(String[] args) throws InterruptedException, IOException {

        String inputtopic_sales = "salestopic";
        String inputtopic_purchases = "purchasestopic";

        String output_topic = "resultstopic";

        String output_topic_total = "totaltopic";

        String output_topic_avg = "averageitemtopic";

        String output_topic_avg_total = "averagetotaltopic";

        String output_topic_highestprof = "highestproftopic";
        String output_topic_windoh = "windowtopic";

        String output_topic_country = "countrysalestopic";


        java.util.Properties propd = new Properties();
        propd.put(StreamsConfig.APPLICATION_ID_CONFIG, "our-application_FML3332222");
        propd.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        propd.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        propd.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        //Input topic que recebe as compras feitas pelos clientes
        KStream<String, String> stream_cliente = builder.stream(inputtopic_sales);

        //Input topic que recebe as compras feitas aos fornecedores
        KStream<String, String> stream_fornece = builder.stream(inputtopic_purchases);


        //5. Revenue por item (Salestopic)
        KTable<String, Double> trata = stream_cliente.mapValues(v -> transformValue(v)).groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).reduce((v1, v2) -> v1 + v2);
        //trata.toStream().mapValues((k, v) -> "" + k + " -> " + v).to(output_topic, Produced.with(Serdes.String(), Serdes.String()));
        //trata.toStream().foreach((key, value) -> System.out.println("Revenue "+key + ": " + value));
        //6. Expenses por item (Purchasestopic)
        KTable<String, Double> trata_exp_it = stream_fornece.mapValues(v -> transformValue(v)).groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).reduce((v1, v2) -> v1 + v2);
        //trata_exp_it.toStream().mapValues((k, v) -> "" + k + " -> " + v).to(output_topic, Produced.with(Serdes.String(), Serdes.String()));
        //trata_exp_it.toStream().foreach((key, value) -> System.out.println("Expenses "+key + ": " + value));
        //7. Profit por item (Join com os dois tópicos, sales e purchases)
        //esta a ter varios profits iguais i dont know whyyy
        KTable<String, Double> joined = trata.join(trata_exp_it,
                (leftValue, rightValue) -> leftValue-rightValue /* ValueJoiner */
        );
        //joined.toStream().foreach((key, value) -> System.out.println("Profit "+key + ": " + value));
        //juntar o 5 6 7

        KTable<String, String> expenses_revenue = trata.join(trata_exp_it,
                (leftValue, rightValue) -> ""+leftValue+"\t"+rightValue
        );


        KTable<String, String> expenses_revenue_profit = expenses_revenue.join(joined,
                (leftValue, rightValue) ->  ""+leftValue+"\t"+rightValue
        );

        expenses_revenue_profit.toStream().mapValues((k, v) -> aux.converte(v,k)).to(output_topic, Produced.with(Serdes.String(), Serdes.String()));



        //joined.toStream().foreach((key, value) -> System.out.println("Profit "+key + ": " + value));
        //groupBy((key, value) -> "total de receitas", Grouped.with(Serdes.String(), Serdes.Double()))

        //8. Total de revenues (receitas)
        KTable<String, Double> trata_rev_total = stream_cliente.mapValues(v -> transformValue(v)).groupBy((key, value) -> "total", Grouped.with(Serdes.String(), Serdes.Double())).reduce((v1, v2) -> v1 + v2);
        //trata_rev_total.toStream().mapValues((k, v) -> "" + k + " -> " + v).to(output_topic, Produced.with(Serdes.String(), Serdes.String()));
        //trata_rev_total.toStream().foreach((key, value) -> System.out.println("Rev tot:" + value));


        //9. Total de expenses (despesas)
        KTable<String, Double> trata_exp_total = stream_fornece.mapValues(v -> transformValue(v)).groupBy((key, value) -> "total", Grouped.with(Serdes.String(), Serdes.Double())).reduce((v1, v2) -> v1 + v2);
        //trata_exp_total.toStream().mapValues((k, v) -> "" + k + " -> " + v).to(output_topic, Produced.with(Serdes.String(), Serdes.String()));
        //trata_exp_total.toStream().foreach((key, value) -> System.out.println("Exp tot:" + value));


        //10. Total profit
        //to stream ta a complicar as coisas
        KTable<String, Double> trata_profit_total = joined.toStream().groupBy((key, value) -> "total", Grouped.with(Serdes.String(), Serdes.Double())).reduce((v1, v2) -> v1 + v2);
        //trata_profit_total.toStream().mapValues((k, v) -> "" + k + " -> " + v).to(output_topic, Produced.with(Serdes.String(), Serdes.String()));

        //trata_profit_total.toStream().foreach((key, value) -> System.out.println("Profit total: "  + value));
        //juntar o 8 9 10
        KTable<String, String> expenses_revenue_total = trata_rev_total.join(trata_exp_total,
                (leftValue, rightValue) -> ""+leftValue+"\t"+rightValue
        );


        KTable<String, String> expenses_revenue_profit_total = expenses_revenue_total.join(trata_profit_total,
                (leftValue, rightValue) ->  ""+leftValue+"\t"+rightValue
        );

        expenses_revenue_profit_total.toStream().mapValues((k, v) -> aux.converte_total(v,k)).to(output_topic_total, Produced.with(Serdes.String(), Serdes.String()));





        //11. Get the average amount spent in each purchase (separated by item) -> Vai ser a media da revenue per item, sendo feito um reduce com soma e um count
        KTable<String, String> avg_per_item = stream_fornece.mapValues(v -> transformValue(v)).groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).aggregate(
                (Initializer<String>) () -> "0,0", /* initializer */
                (Aggregator<String, Double, String>) (key, value, aggregate) -> agrega(aggregate, value), /* adder */
                Materialized.as("aggregated-avg-item-store")); /* state store name */


        /* serde for aggregate value */
        //avg_per_item.toStream().foreach((key, value) -> System.out.println(key + ": " + value));
        //avg_per_item.mapValues(v -> calcMedia(v)).toStream().foreach((key, value) -> System.out.println(key + " Media: " + value));
        avg_per_item.toStream().mapValues((k, v) -> aux.converte_avg(v,k)).to(output_topic_avg, Produced.with(Serdes.String(), Serdes.String()));



        //12. Get the average amount spent in each purchase (aggregated for all items)
        KTable<String, String> avg_all_items = stream_fornece.mapValues(v -> transformValue(v)).groupBy((key, value) -> "avg total", Grouped.with(Serdes.String(), Serdes.Double())).aggregate(
                (Initializer<String>) () -> "0,0", // initializer
                (Aggregator<String, Double, String>) (key, value, aggregate) -> agrega(aggregate, value), //adder
                Materialized.as("aggregated-avg-all-item-store")); //state store name

        avg_all_items.toStream().mapValues((k, v) -> aux.converte_avg_total(v,k)).to(output_topic_avg_total, Produced.with(Serdes.String(), Serdes.String()));

        // serde for aggregate value

        //avg_all_items.toStream().foreach((key, value) -> System.out.println(key + ": " + value));
        //avg_all_items.mapValues(v -> calcMedia(v)).toStream().foreach((key, value) -> System.out.println(key + " Media total: " + value));

        //13. Item with the highest profit of all (only one if there is a tie)
        KTable<String, String> teste=joined.mapValues((key, value)->key+"\t"+value).toStream().groupBy((key, value) -> "total de profits",Grouped.with(Serdes.String(), Serdes.String())).reduce((v1, v2) -> maximo(v1,v2));
        teste.toStream().mapValues((k, v) -> aux.converte_highestprof(v,k)).to(output_topic_highestprof, Produced.with(Serdes.String(), Serdes.String()));
        //teste.toStream().foreach((key, value) -> System.out.println("Maior profit deste mundo em que vivemos "+key + ": " + value));


        //14. Windowed -> Total de revenues na última hora (Está para 60 segundos) receitas
        KTable<Windowed<String>, Double> window_rev_total = stream_cliente.mapValues(v -> transformValue(v)).groupBy((key, value) -> "total", Grouped.with(Serdes.String(), Serdes.Double())).windowedBy(TimeWindows.of(TimeUnit.MINUTES.toMillis(1))).reduce((v1, v2) -> v1 + v2);
        //window_rev_total.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + k + "-->" + v)).to(output_topic, Produced.with(Serdes.String(), Serdes.String()));
        //window_rev_total.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + k + "-->" + v)).foreach((key, value) -> System.out.println("Window rev:" + key + "  " + value));

        //15. Windowed -> Total de expenses na última hora (gasros)
        KTable<Windowed<String>, Double> window_exp_total = stream_fornece.mapValues(v -> transformValue(v)).groupBy((key, value) -> "total", Grouped.with(Serdes.String(), Serdes.Double())).windowedBy(TimeWindows.of(TimeUnit.MINUTES.toMillis(1))).reduce((v1, v2) -> v1 + v2);
        //window_exp_total.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + k + "-->" + v)).to(output_topic, Produced.with(Serdes.String(), Serdes.String()));
        // window_exp_total.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + k + "-->" + v)).foreach((key, value) -> System.out.println("Windows exp : " + key + "  " + value));

        //16. Windowed -> Total profit na última hora (FALTAAAAAAAAAAAA TESTAAAAAAAAAAAAAAAR)
        //Primeiro tenho que fazer um join com as duas windows do revenue e das expenses




        KTable<Windowed<String>, Double> join_window1 = window_rev_total.join(window_exp_total,
                (leftValue, rightValue) -> leftValue-rightValue // ValueJoiner
        );
        //join_window1.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + v)).foreach((key, value) -> System.out.println("Windows prof: " + value));

        //juntar o 15 16 14

        KTable<Windowed<String>, String> windoh_expenses_revenue = window_rev_total.join(window_exp_total,
                (leftValue, rightValue) -> ""+leftValue+"\t"+rightValue
        );


        KTable<Windowed<String>, String> windoh_expenses_revenue_profit = windoh_expenses_revenue.join(join_window1,
                (leftValue, rightValue) ->  ""+leftValue+"\t"+rightValue
        );
        windoh_expenses_revenue_profit.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + v)).mapValues((k, v) -> aux.converte_windoh(v,k)).to(output_topic_windoh, Produced.with(Serdes.String(), Serdes.String()));




        //16. Windowed -> Total profit na última hora (FALTAAAAAAAAAAAA TESTAAAAAAAAAAAAAAAR)
        //Primeiro tenho que fazer um join com as duas windows do revenue e das expenses




        /*KTable<Windowed<String>, Double> join_window1 = window_rev_total.join(window_exp_total,
                (leftValue, rightValue) -> leftValue-rightValue // ValueJoiner
        );
        join_window1.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + k + "-->" + v)).foreach((key, value) -> System.out.println("Windows prof2:" + value));*/


        /*KStream<String, Double> join_window2 = trata_rev_total.toStream().outerJoin(trata_exp_total.toStream(),
                (leftValue, rightValue) -> leftValue-rightValue,
                JoinWindows.of(TimeUnit.MINUTES.toMillis(5)),Joined.with(Serdes.String(), Serdes.Double(), Serdes.Double())
                );*/


        //Por fim tenho que utilizar o join e a window
        //KTable<Windowed<String>, Double> window_profit_total = join_window.toStream().groupBy((key, value) -> "total profit", Grouped.with(Serdes.String(), Serdes.Double())).windowedBy(TimeWindows.of(TimeUnit.MINUTES.toMillis(1))).reduce((v1, v2) -> v1 + v2);
        //join_window1.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + k + "-->" + v)).foreach((key, value) -> System.out.println("Windows prof1:" + value));
        //join_window2.foreach((key, value) -> System.out.println("Windows prof2:" + value));


        //17. Get the name of the country with the highest sales per item. Include the value of such sales
        KGroupedStream<String, String> groupedStream = stream_cliente.mapValues((v)->produto_aux(v)).groupBy(
                (key, value) -> pais_aux2(key,value),
                Serialized.with(
                        Serdes.String(),
                        Serdes.String())
        );
        groupedStream.reduce((v1, v2) -> help(v1,v2)).toStream().groupBy(
                (key, value) -> help2(key),
                Serialized.with(
                        Serdes.String(),
                        Serdes.String())
        ).reduce((v1, v2) -> help3(v1,v2)).toStream().mapValues((k, v) -> aux.converte_pais(v,k)).to(output_topic_country, Produced.with(Serdes.String(), Serdes.String()));


        KafkaStreams streams = new KafkaStreams(builder.build(), propd);
        streams.start();
    }


    public static Double transformValue(String s){
        JSONObject obj = new JSONObject(s);
        return (obj.getDouble("preco") * obj.getDouble("unidades"));
    }


    public static String agrega(String aggregatings, Double value){
        String[] separa = aggregatings.split(",");
        int contador = Integer.parseInt(separa[0]) + 1;
        Double soma = Double.parseDouble(separa[1]) + value;

        return contador + "," + soma;
    }


    public static String calcMedia(String parse){
        String[] separa = parse.split(",");
        Double media = Double.parseDouble(separa[1]) / Integer.parseInt(separa[0]);

        return media + "";
    }

    public static String maximo(String n1,String n2){
        // System.out.println("DEBUG "+n1+"    "+n2);
        String[] parts1 = n1.split("\t");
        String[] parts2 = n2.split("\t");
        //System.out.println(Arrays.toString(parts1));
        //System.out.println(Arrays.toString(parts2));
        if(Double.parseDouble(parts1[1])>Double.parseDouble(parts2[1])){
            return n1;
        }
        return n2;

    }






    public static String help(String n1,String n2){
        JSONObject result2 = new JSONObject(n2);
        JSONObject result1 = new JSONObject(n1);
        JSONObject result3 = new JSONObject();
        result3.put("pais",result1.getString("pais"));
        result3.put("produto",result1.getDouble("produto")+result2.getDouble("produto"));
        return result3.toString();
    }

    public static String help3(String n1,String n2){
        JSONObject result2 = new JSONObject(n2);
        JSONObject result1 = new JSONObject(n1);
        if(result1.getDouble("produto")>result2.getDouble("produto")){
            return n1;
        }
        return n2;
    }

    public static String help2(String n1){
        String[] parts1 = n1.split("\t");
        System.out.println("help2 "+ Arrays.toString(parts1));
        return parts1[0];
    }

    public static String produto_aux(String n1){
        JSONObject result1 = new JSONObject(n1);
        Double produto1=result1.getDouble("preco") * result1.getDouble("unidades");
        JSONObject re = new JSONObject();
        re.put("produto",produto1);
        re.put("pais",result1.getString("pais"));
        return re.toString();


    }

    public static String pais_aux2(String n1,String n2){
        JSONObject result2 = new JSONObject(n2);
        String r=n1+"\t"+result2.getString("pais");
        return r;
    }

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


//.aggregate(()->0.0,(newvalue, totalval) -> totalval + newvalue);
        /*.aggregate(
                total_rev -> 0, / initializer
                (key, newValue, total_rev) -> total_rev, // adder
                Materialized.as("aggregated-stream-store") // state store name
                        .withValueSerde(Serdes.Integer()); // serde for aggregate value
        */

//final Serde<String> stringSerde = Serdes.String();
//final Serde<Double> doubleSerde = Serdes.Double();

/*KTable<String, Double> trata = stream_cliente.mapValues(v -> transformValue(v)).groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).reduce((v1, v2) -> v1 + v2);*/

//Compute profit //TESTAR

//(leftValue, rightValue) -> "fornece=" + leftValue + ", compra=" + rightValue, // ValueJoiner
        /*KStream<String, String> joined = stream_fornece.join(stream_cliente,
                (leftValue, rightValue) -> String.valueOf(transformValue(leftValue) - transformValue(rightValue)), // ValueJoiner
                JoinWindows.of(TimeUnit.MINUTES.toMillis(5)),
                Joined.with(
                        Serdes.String(),    //key
                        Serdes.String(),    //left value
                        Serdes.String())    //right value
        );

        //joined.groupByKey(Grouped.with(Serdes.String(), Serdes.String())).reduce((v1, v2) -> v1 + v2).toStream().mapValues((k, v) -> "" + k + " --join--> " + v).to(output_topic, Produced.with(Serdes.String(), Serdes.String()));

        //joined.foreach((key, value) -> System.out.println(key + ": " + value));
        joined.groupByKey(Grouped.with(Serdes.String(), Serdes.String())).reduce((v1, v2) -> v1 + v2).toStream().foreach((key, value) -> System.out.println(key + ": " + value));
        */