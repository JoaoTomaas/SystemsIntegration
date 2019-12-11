import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class teste {
    public static  ArrayList<Integer> Lista_Items;
    public static void main(String args[]) {

        //Lista_Paises= new ArrayList<String>();
        Lista_Items= new ArrayList<Integer>();
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test");
        //means that offsets are committed automatically with a frequency controlled by the config auto.commit.interval.ms.
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        //The deserializer settings specify how to turn bytes into objects
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("dbinfotopic"));


        String topicName = "purchasestopic";

        // create instance for properties to access producer configs
        Properties props2 = new Properties();

        //Assign localhost id
        props2.put("bootstrap.servers", "localhost:9092");

        //Set acknowledgements for producer requests.
        props2.put("acks", "all");

        //If the request fails, the producer can automatically retry,
        props2.put("retries", 0);

        //Specify buffer size in config
        props2.put("batch.size", 16384);

        //Reduce the no of requests less than 0
        props2.put("linger.ms", 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props2.put("buffer.memory", 33554432);

        props2.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props2.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props2);



        System.out.println("PurchaseOrdersThread running...");

        JSONObject dados_compra = new JSONObject();
        dados_compra.put("preco","4");
        dados_compra.put("unidades","10");
        String jsonresult = dados_compra.toString();
        producer.send(new ProducerRecord<String, String>(topicName,"1", jsonresult));

        dados_compra = new JSONObject();
        dados_compra.put("preco","3");
        dados_compra.put("unidades","5");
        jsonresult = dados_compra.toString();

        producer.send(new ProducerRecord<String, String>(topicName,"1", jsonresult));
        dados_compra = new JSONObject();
        dados_compra.put("preco","2");
        dados_compra.put("unidades","10");
        jsonresult = dados_compra.toString();

        producer.send(new ProducerRecord<String, String>(topicName,"1", jsonresult));
        dados_compra = new JSONObject();
        dados_compra.put("preco","2");
        dados_compra.put("unidades","5");
        jsonresult = dados_compra.toString();
        producer.send(new ProducerRecord<String, String>(topicName,"1", jsonresult));
        dados_compra = new JSONObject();
        dados_compra.put("preco","3");
        dados_compra.put("unidades","10");
        jsonresult = dados_compra.toString();
        producer.send(new ProducerRecord<String, String>(topicName,"1", jsonresult));
        producer.close();

    }
}