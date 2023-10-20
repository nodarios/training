package com.example.consumer;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) {
        KafkaConsumer<String, GenericRecord> kafkaConsumer = new KafkaConsumer<>(getProperties());
        kafkaConsumer.subscribe(List.of("mytopic"));

        try {
            while (true) {
                ConsumerRecords<String, GenericRecord> records = kafkaConsumer.poll(Duration.ofMillis(10));
                System.out.println("records.count: " + records.count());
                for (ConsumerRecord<String, GenericRecord> record : records) {
                    String key = record.key();
                    System.out.println("key: " + key);
                    GenericRecord genericRecord = record.value();
                    System.out.printf("id: %d, message: %s%n", genericRecord.get("id"), genericRecord.get("message"));
                    Schema schema = genericRecord.getSchema();
                    System.out.println("schema: " + schema);
                }
                Thread.sleep(1500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaConsumer.close();
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        properties.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-a");
        return properties;
    }

}
