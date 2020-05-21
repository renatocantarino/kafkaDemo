package com.cantarino.kafkademo.config;

import com.cantarino.kafkademo.Models.Mensagem;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    ProducerFactory<String, String> producerFactory()
    {
        Map<String, Object> configs = new HashMap<>();

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,  "localhost:9092");
        configs.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG  , StringSerializer.class);
        configs.put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG , StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(configs);
    }

   @Bean
   public KafkaTemplate<String, String> KafkaTemplate()
    {
        return  new KafkaTemplate<>(producerFactory());
    }

   @Bean
   public ConsumerFactory<String, String> consumerFactory()
   {
       Map<String, Object> configs = new HashMap<>();

       configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,  "localhost:9092");
       configs.put( ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG  , StringDeserializer.class);
       configs.put( ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG , StringDeserializer.class);
       configs.put( ConsumerConfig.GROUP_ID_CONFIG , "myGroupId");

       return new DefaultKafkaConsumerFactory<>(configs , new StringDeserializer() , new StringDeserializer());
   }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> KafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());

        return kafkaListenerContainerFactory;
    }

    @Bean
    public Gson jsonConverter()
    {
        return new Gson();
    }
}
