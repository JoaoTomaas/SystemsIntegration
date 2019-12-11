package threads;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

public class CustomersThread implements Runnable {
    public static ArrayList<String> Lista_Paises;
    public static  ArrayList<Integer> Lista_Items;
    public void run() {
        Lista_Paises= new ArrayList<String>();
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



        String topicName = "salestopic";

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


        System.out.println("CustomersThread running...");

        while (true) {

            Random random = new Random();
            int price = random.nextInt(1000) + 1; // de 1 a 1000
            int n_units = random.nextInt(100) + 1; //de 1 a 100

            System.out.println("Price generated: " + price);
            System.out.println("Number of units generated: " + n_units);

            JSONObject dados_compra = new JSONObject();
            dados_compra.put("preco",price);
            dados_compra.put("unidades",n_units);

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                update(record.value());
            }



            System.out.println(Arrays.toString(Lista_Items.toArray()));
            System.out.println(Arrays.toString(Lista_Paises.toArray()));


            if(Lista_Items.size()>0&&Lista_Paises.size()>0){
                String item= Integer.toString(Lista_Items.get(random.nextInt(Lista_Items.size())));
                dados_compra.put("pais",Lista_Paises.get(random.nextInt(Lista_Paises.size())));
                String jsonresult = dados_compra.toString();
                System.out.println(item+"--->"+jsonresult);
                producer.send(new ProducerRecord<String, String>(topicName,item, jsonresult));
            }


            try {
                Thread.sleep(15000); //Sleep de 5 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public static void update(String s){
        JSONObject obj = new JSONObject(s);
        JSONObject payload = obj.getJSONObject("payload");
        if(payload.has("country_name")){
            if(!Lista_Paises.contains(payload.getString("country_name"))){
                Lista_Paises.add(payload.getString("country_name"));
            }
        }
        else {
            //System.out.println(payload.keySet());
            if(!Lista_Items.contains(payload.getInt("item_id"))){
                Lista_Items.add((payload.getInt("item_id")));
            }


        }



    }
    public static void main(String args[]) {
        (new Thread(new CustomersThread())).start();
    }


}