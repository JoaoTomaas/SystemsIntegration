import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;


public class SimpleStreamsExercises {
    private static final String tablename = "exercises";

    public static void main(String[] args) throws InterruptedException, IOException {

        String topicName = args[0].toString();
        String outtopicname = "resultstopic";

        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Long> lines = builder.stream(topicName);
        /*
        KTable<String, Long> outlines = lines.
                groupByKey().count();
        outlines.toStream().to(outtopicname);
        */
        //usar o map values para caso com stress de tipos
        //para restringir em termos de tempo usar o windowedBy
        KTable<String, Long> outlines = lines.
                groupByKey().reduce((oldval, newval) -> oldval + newval, Materialized.as(tablename));
        outlines.toStream().to(outtopicname);
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    /*
        System.out.println("Reading stream from topic " + topicName);

        System.out.println("Press enter when ready...");
        System.in.read();
        while (true) {
            ReadOnlyKeyValueStore<String, Long> keyValueStore = streams.store(tablename, QueryableStoreTypes.keyValueStore());
            System.out.println("count for 355:" + keyValueStore.get("355"));
            System.out.println();
            // Get the values for a range of keys available in this application instance
            KeyValueIterator<String, Long> range = keyValueStore.range("880", "980");
            while (range.hasNext()) {
                KeyValue<String, Long> next = range.next();
                System.out.println("count for " + next.key + ": " + next.value);
            }
            range.close();
            Thread.sleep(30000);
        }*/
    }


}