package com.example.producer;

import com.example.dto.MessageDto;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer {

    public static void main(String[] args) {
        var messageDto = new MessageDto(1, "hello");
        var schemaAsString = getSchemaAsString();
        Schema schema = new Schema.Parser().parse(schemaAsString);
        GenericRecord genericRecord = new GenericData.Record(schema);
        genericRecord.put("id", messageDto.getId());
        genericRecord.put("message", messageDto.getMessage());

        try (KafkaProducer<String, GenericRecord> kafkaProducer = new KafkaProducer<>(getProperties())) {
            kafkaProducer.send(new ProducerRecord<>("mytopic", String.valueOf(messageDto.getId()), genericRecord));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        properties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        return properties;
    }

    private static String getSchemaAsString() {
        return """
                {
                	"type":"record",
                	"name":"MessageDto",
                	"namespace": "com.example.javakafkaproducer",
                	"fields":[
                		{"name":"id","type":"int"},
                		{"name":"message","type":"string"}
                	]
                }
                """;
    }

}
